/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author 1gdaw06
 */
public class Persona {
    private String nombre;
    private String dni;
    private Empresa empresa;

    public Persona() {
    }

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Persona(String nombre, String dni, Empresa empresa) {
        this.nombre = nombre;
        this.dni = dni;
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Invitado: " + nombre + " con DNI: " + dni + " de la empresa=" + empresa;
    }
    
    
}
