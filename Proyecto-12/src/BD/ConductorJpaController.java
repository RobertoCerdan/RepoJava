/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import BD.exceptions.IllegalOrphanException;
import BD.exceptions.NonexistentEntityException;
import BD.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Coche;
import Modelo.Conductor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author 1gdaw06
 */
public class ConductorJpaController implements Serializable {

    public ConductorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conductor conductor) throws PreexistingEntityException, Exception {
        if (conductor.getCocheList() == null) {
            conductor.setCocheList(new ArrayList<Coche>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Coche> attachedCocheList = new ArrayList<Coche>();
            for (Coche cocheListCocheToAttach : conductor.getCocheList()) {
                cocheListCocheToAttach = em.getReference(cocheListCocheToAttach.getClass(), cocheListCocheToAttach.getMatricula());
                attachedCocheList.add(cocheListCocheToAttach);
            }
            conductor.setCocheList(attachedCocheList);
            em.persist(conductor);
            for (Coche cocheListCoche : conductor.getCocheList()) {
                Conductor oldPropietarioOfCocheListCoche = cocheListCoche.getPropietario();
                cocheListCoche.setPropietario(conductor);
                cocheListCoche = em.merge(cocheListCoche);
                if (oldPropietarioOfCocheListCoche != null) {
                    oldPropietarioOfCocheListCoche.getCocheList().remove(cocheListCoche);
                    oldPropietarioOfCocheListCoche = em.merge(oldPropietarioOfCocheListCoche);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConductor(conductor.getDni()) != null) {
                throw new PreexistingEntityException("Conductor " + conductor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conductor conductor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor persistentConductor = em.find(Conductor.class, conductor.getDni());
            List<Coche> cocheListOld = persistentConductor.getCocheList();
            List<Coche> cocheListNew = conductor.getCocheList();
            List<String> illegalOrphanMessages = null;
            for (Coche cocheListOldCoche : cocheListOld) {
                if (!cocheListNew.contains(cocheListOldCoche)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coche " + cocheListOldCoche + " since its propietario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Coche> attachedCocheListNew = new ArrayList<Coche>();
            for (Coche cocheListNewCocheToAttach : cocheListNew) {
                cocheListNewCocheToAttach = em.getReference(cocheListNewCocheToAttach.getClass(), cocheListNewCocheToAttach.getMatricula());
                attachedCocheListNew.add(cocheListNewCocheToAttach);
            }
            cocheListNew = attachedCocheListNew;
            conductor.setCocheList(cocheListNew);
            conductor = em.merge(conductor);
            for (Coche cocheListNewCoche : cocheListNew) {
                if (!cocheListOld.contains(cocheListNewCoche)) {
                    Conductor oldPropietarioOfCocheListNewCoche = cocheListNewCoche.getPropietario();
                    cocheListNewCoche.setPropietario(conductor);
                    cocheListNewCoche = em.merge(cocheListNewCoche);
                    if (oldPropietarioOfCocheListNewCoche != null && !oldPropietarioOfCocheListNewCoche.equals(conductor)) {
                        oldPropietarioOfCocheListNewCoche.getCocheList().remove(cocheListNewCoche);
                        oldPropietarioOfCocheListNewCoche = em.merge(oldPropietarioOfCocheListNewCoche);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = conductor.getDni();
                if (findConductor(id) == null) {
                    throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.");
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
            Conductor conductor;
            try {
                conductor = em.getReference(Conductor.class, id);
                conductor.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Coche> cocheListOrphanCheck = conductor.getCocheList();
            for (Coche cocheListOrphanCheckCoche : cocheListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conductor (" + conductor + ") cannot be destroyed since the Coche " + cocheListOrphanCheckCoche + " in its cocheList field has a non-nullable propietario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(conductor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conductor> findConductorEntities() {
        return findConductorEntities(true, -1, -1);
    }

    public List<Conductor> findConductorEntities(int maxResults, int firstResult) {
        return findConductorEntities(false, maxResults, firstResult);
    }

    private List<Conductor> findConductorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conductor.class));
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

    public Conductor findConductor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conductor.class, id);
        } finally {
            em.close();
        }
    }

    public int getConductorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conductor> rt = cq.from(Conductor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
