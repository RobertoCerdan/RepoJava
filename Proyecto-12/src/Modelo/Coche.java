/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1gdaw06
 */
@Entity
@Table(name = "coches")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coche.findAll", query = "SELECT c FROM Coche c")
    , @NamedQuery(name = "Coche.findByMatricula", query = "SELECT c FROM Coche c WHERE c.matricula = :matricula")
    , @NamedQuery(name = "Coche.findByModelo", query = "SELECT c FROM Coche c WHERE c.modelo = :modelo")
    , @NamedQuery(name = "Coche.findByColor", query = "SELECT c FROM Coche c WHERE c.color = :color")})
public class Coche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    private List<Multa> multaList;
    @JoinColumn(name = "propietario", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Conductor propietario;

    public Coche() {
    }

    public Coche(String matricula) {
        this.matricula = matricula;
    }

    public Coche(String matricula, String modelo, String color) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @XmlTransient
    public List<Multa> getMultaList() {
        return multaList;
    }

    public void setMultaList(List<Multa> multaList) {
        this.multaList = multaList;
    }

    public Conductor getPropietario() {
        return propietario;
    }

    public void setPropietario(Conductor propietario) {
        this.propietario = propietario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coche)) {
            return false;
        }
        Coche other = (Coche) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Coche[ matricula=" + matricula + " ]";
    }
    
}
