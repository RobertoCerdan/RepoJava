/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import BD.exceptions.IllegalOrphanException;
import BD.exceptions.NonexistentEntityException;
import BD.exceptions.PreexistingEntityException;
import Modelo.Coche;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Conductor;
import Modelo.Multa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author 1gdaw06
 */
public class CocheJpaController implements Serializable {

    public CocheJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coche coche) throws PreexistingEntityException, Exception {
        if (coche.getMultaList() == null) {
            coche.setMultaList(new ArrayList<Multa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor propietario = coche.getPropietario();
            if (propietario != null) {
                propietario = em.getReference(propietario.getClass(), propietario.getDni());
                coche.setPropietario(propietario);
            }
            List<Multa> attachedMultaList = new ArrayList<Multa>();
            for (Multa multaListMultaToAttach : coche.getMultaList()) {
                multaListMultaToAttach = em.getReference(multaListMultaToAttach.getClass(), multaListMultaToAttach.getId());
                attachedMultaList.add(multaListMultaToAttach);
            }
            coche.setMultaList(attachedMultaList);
            em.persist(coche);
            if (propietario != null) {
                propietario.getCocheList().add(coche);
                propietario = em.merge(propietario);
            }
            for (Multa multaListMulta : coche.getMultaList()) {
                Coche oldVehiculoOfMultaListMulta = multaListMulta.getVehiculo();
                multaListMulta.setVehiculo(coche);
                multaListMulta = em.merge(multaListMulta);
                if (oldVehiculoOfMultaListMulta != null) {
                    oldVehiculoOfMultaListMulta.getMultaList().remove(multaListMulta);
                    oldVehiculoOfMultaListMulta = em.merge(oldVehiculoOfMultaListMulta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoche(coche.getMatricula()) != null) {
                throw new PreexistingEntityException("Coche " + coche + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coche coche) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coche persistentCoche = em.find(Coche.class, coche.getMatricula());
            Conductor propietarioOld = persistentCoche.getPropietario();
            Conductor propietarioNew = coche.getPropietario();
            List<Multa> multaListOld = persistentCoche.getMultaList();
            List<Multa> multaListNew = coche.getMultaList();
            List<String> illegalOrphanMessages = null;
            for (Multa multaListOldMulta : multaListOld) {
                if (!multaListNew.contains(multaListOldMulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Multa " + multaListOldMulta + " since its vehiculo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (propietarioNew != null) {
                propietarioNew = em.getReference(propietarioNew.getClass(), propietarioNew.getDni());
                coche.setPropietario(propietarioNew);
            }
            List<Multa> attachedMultaListNew = new ArrayList<Multa>();
            for (Multa multaListNewMultaToAttach : multaListNew) {
                multaListNewMultaToAttach = em.getReference(multaListNewMultaToAttach.getClass(), multaListNewMultaToAttach.getId());
                attachedMultaListNew.add(multaListNewMultaToAttach);
            }
            multaListNew = attachedMultaListNew;
            coche.setMultaList(multaListNew);
            coche = em.merge(coche);
            if (propietarioOld != null && !propietarioOld.equals(propietarioNew)) {
                propietarioOld.getCocheList().remove(coche);
                propietarioOld = em.merge(propietarioOld);
            }
            if (propietarioNew != null && !propietarioNew.equals(propietarioOld)) {
                propietarioNew.getCocheList().add(coche);
                propietarioNew = em.merge(propietarioNew);
            }
            for (Multa multaListNewMulta : multaListNew) {
                if (!multaListOld.contains(multaListNewMulta)) {
                    Coche oldVehiculoOfMultaListNewMulta = multaListNewMulta.getVehiculo();
                    multaListNewMulta.setVehiculo(coche);
                    multaListNewMulta = em.merge(multaListNewMulta);
                    if (oldVehiculoOfMultaListNewMulta != null && !oldVehiculoOfMultaListNewMulta.equals(coche)) {
                        oldVehiculoOfMultaListNewMulta.getMultaList().remove(multaListNewMulta);
                        oldVehiculoOfMultaListNewMulta = em.merge(oldVehiculoOfMultaListNewMulta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = coche.getMatricula();
                if (findCoche(id) == null) {
                    throw new NonexistentEntityException("The coche with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coche coche;
            try {
                coche = em.getReference(Coche.class, id);
                coche.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coche with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Multa> multaListOrphanCheck = coche.getMultaList();
            for (Multa multaListOrphanCheckMulta : multaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coche (" + coche + ") cannot be destroyed since the Multa " + multaListOrphanCheckMulta + " in its multaList field has a non-nullable vehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Conductor propietario = coche.getPropietario();
            if (propietario != null) {
                propietario.getCocheList().remove(coche);
                propietario = em.merge(propietario);
            }
            em.remove(coche);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coche> findCocheEntities() {
        return findCocheEntities(true, -1, -1);
    }

    public List<Coche> findCocheEntities(int maxResults, int firstResult) {
        return findCocheEntities(false, maxResults, firstResult);
    }

    private List<Coche> findCocheEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coche.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Coche findCoche(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coche.class, id);
        } finally {
            em.close();
        }
    }

    public int getCocheCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Coche> rt = cq.from(Coche.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
