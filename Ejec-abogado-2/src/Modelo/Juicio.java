/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class Juicio {

    private String id;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private estadoJuicio estado; //tramite, archivado o anulado
    
    private ArrayList<Abogado> abogados;

    public Juicio() {
        this.abogados = new ArrayList();
    }

    
    
    public Juicio(String id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado, ArrayList<Abogado> abogados) {
        this.id = id;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.abogados = abogados;
    }
    
    public Juicio(String id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado, Abogado a) {
        this.id = id;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.abogados = new ArrayList();
        this.abogados.add(a);
    }

    public Juicio(String id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado) {
        this.id = id;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.abogados = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public estadoJuicio getEstado() {
        return estado;
    }

    public void setEstado(estadoJuicio estado) {
        this.estado = estado;
    }

    public ArrayList<Abogado> getAbogados() {
        return abogados;
    }

    public void setAbogados(ArrayList<Abogado> abogados) {
        this.abogados = abogados;
    }

    public void addAbogado(Abogado a){
        this.abogados.add(a);
    }


}
