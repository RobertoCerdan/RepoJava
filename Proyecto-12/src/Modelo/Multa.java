/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1gdaw06
 */
@Entity
@Table(name = "multas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Multa.findAll", query = "SELECT m FROM Multa m")
    , @NamedQuery(name = "Multa.findById", query = "SELECT m FROM Multa m WHERE m.id = :id")
    , @NamedQuery(name = "Multa.findByTipo", query = "SELECT m FROM Multa m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Multa.findByFecha", query = "SELECT m FROM Multa m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Multa.findByLugar", query = "SELECT m FROM Multa m WHERE m.lugar = :lugar")})
public class Multa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @Column(name = "lugar")
    private String lugar;
    @JoinColumn(name = "vehiculo", referencedColumnName = "matricula")
    @ManyToOne(optional = false)
    private Coche vehiculo;

    public Multa() {
    }

    public Multa(String id) {
        this.id = id;
    }

    public Multa(String id, String tipo, String fecha, String lugar) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Coche getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Coche vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multa)) {
            return false;
        }
        Multa other = (Multa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Multa[ id=" + id + " ]";
    }
    
}
