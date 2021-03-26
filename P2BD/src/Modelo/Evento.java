/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
/** @author Roberto
 * */
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author 1gdaw06
 */
public class Evento {
    
   private String nombre;
   private String lugar;
   private LocalDate fecha;
   private LocalTime hInicio;
   private LocalTime hFin;
   private int aforo;
   private ArrayList<Persona> invitados;

    public Evento() {
        invitados = new ArrayList();
    }

    public Evento(String nombre, String lugar, LocalDate fecha, LocalTime hInicio, LocalTime hFin, int aforo) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha = fecha;
        this.hInicio = hInicio;
        this.hFin = hFin;
        this.aforo = aforo;
        invitados = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime gethInicio() {
        return hInicio;
    }

    public void sethInicio(LocalTime hInicio) {
        this.hInicio = hInicio;
    }

    public LocalTime gethFin() {
        return hFin;
    }

    public void sethFin(LocalTime hFin) {
        this.hFin = hFin;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public ArrayList<Persona> getInvitados() {
        return invitados;
    }

    public void setInvitados(ArrayList<Persona> invitados) {
        this.invitados = invitados;
    }

    public boolean addInvitado(Persona p){
        if(invitados.size()!=aforo){
            invitados.add(p);
            return true;
        }
        else{
            return false;
        }
    }
    
    public void removeInvitado(Persona p){
        invitados.remove(p);          
    }
   
   
   
   
   
   
}
