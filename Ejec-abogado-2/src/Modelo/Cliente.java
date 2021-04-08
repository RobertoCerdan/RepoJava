/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class Cliente {
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private ArrayList<Juicio> juicios;

    
    public Cliente() {
        this.juicios = new ArrayList();
    }

    public Cliente(String dni, String nombre, String apellidos, String email, ArrayList<Juicio> casos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.juicios = casos;
    }
    
    public Cliente(String dni, String nombre, String apellidos, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Juicio> getJuicios() {
        return juicios;
    }

    public void setJuicios(ArrayList<Juicio> juicios) {
        this.juicios = juicios;
    }
    
    public void addJuicio(Juicio juicio) {
        this.juicios.add(juicio);
    }
    
    public void borrarJuicio(Juicio j){
        this.juicios.remove(j);
    }

    @Override
    public String toString() {
        return "Cliente con DNI: " + dni + " y Nombre: " + nombre + " " + apellidos + "\n Email: " + email + " Juicios: " + juicios; 
    }
    
    
    
    
}
