/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author rober
 */
public class Abogado {
    
    private String dni;
    private String nombre;
    private String apellidos;
    private String dir;

    public Abogado(String dni, String nombre, String apellidos, String dir) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dir = dir;
    }

    public Abogado() {

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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
    
    
}
