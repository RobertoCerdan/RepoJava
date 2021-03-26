/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import BD.TablaAbogados;
import BD.TablaClientes;
import Modelo.Abogado;
import Modelo.Cliente;
import Modelo.Juicio;
import Modelo.estadoJuicio;
import Vista.V1;
import Vista.VAnadirAbogado;
import Vista.VAnadirJuicio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;

/**
 *
 * @author rober
 * J
 */
public class Controlador {
    
    public static VAnadirJuicio vanadirjuicio;
    public static VAnadirAbogado vanadirabogado;
    public static V1 v1;
    
    
    private static Juicio juicio;
    private static Abogado abogado;
    private static Cliente cliente;
    
    
    public static void main(String[] args) {
        v1 = new V1();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
        
        
        
    }
    
    public static void pedirCliente(String dni) throws Exception{
         cliente = TablaClientes.queryClienteByDni(dni);
    }
    
    
    public static void crearCliente(String dni, String nombre, String apellidos, String email){
         cliente = new Cliente(dni, nombre, apellidos, email);
    }
    
    public static boolean insertarCliente(String dni, String nombre, String apellidos, String email) throws Exception{
        cliente = new Cliente(dni, nombre, apellidos, email);
        if(BD.TablaClientes.insertCliente(cliente)){
            return true;
        }
        return false;
    }
        
    public static boolean editarCliente(String dni, String nombre, String apellidos, String email) throws Exception{
        cliente = new Cliente(dni, nombre, apellidos, email);
        if(BD.TablaClientes.editCliente(cliente)){
            return true;
        }
        return false;      
    }
    
    
    
    public static boolean insertarJuicio(String id, String dni, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado) throws Exception{
        pedirCliente(dni);
        juicio = new Juicio(id, cliente, fechaInicio, fechaFin, estado);
        if(BD.TablaJuicios.insertJuicio(juicio)){
            return true;
        }
        return false;
    }
    
    public static boolean insertarJuicioConAbogado(String id, String dnicliente, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado, String dni) throws Exception{
        pedirCliente(dnicliente);
        pedirAbogado(dni);
        juicio = new Juicio(id, cliente, fechaInicio, fechaFin, estado, abogado);
        if(BD.TablaJuicios.insertJuicio(juicio)){
            return true;
        }
        return false;
    }
    
    public static boolean editarJuicio(String id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado) throws Exception{
        juicio = new Juicio(id, cliente, fechaInicio, fechaFin, estado);
        if(BD.TablaJuicios.editJuicio(juicio)){
            return true;
        }
        return false;      
    }
   
    
    
    
    public static void pedirAbogado(String dni) throws Exception{
        abogado = TablaAbogados.queryAbogadoByDni(dni);
    }
   
    public static boolean insertarAbogado(String dni, String nombre, String apellidos, String dir) throws Exception{
        abogado = new Abogado(dni, nombre, apellidos, dir);
        if(TablaAbogados.insertAbogado(abogado)){
            return true;
        }
        return false;
    }

    public static boolean editarAbogado(String dni, String nombre, String apellidos, String dir) throws Exception{
        abogado = new Abogado(dni, nombre, apellidos, dir);
        if(TablaAbogados.editAbogado(abogado)){
            return true;
        }
        return false;
    }

    
    
    
    public static void abrirVAnadirAbogado() {
        vanadirabogado = new VAnadirAbogado(null, true);
        vanadirabogado.setLocationRelativeTo(null);
        vanadirabogado.setVisible(true);
    }
    
    public static void abrirVAnadirJuicio() {
        try {
            vanadirjuicio = new VAnadirJuicio(null, true);
            vanadirjuicio.setLocationRelativeTo(null);
            vanadirjuicio.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de añadir juicios");
        }
    }

    public static void llenarDesplegableClientes(JComboBox<String> c) throws Exception{
        ArrayList<Cliente> clientes = TablaClientes.queryAllClientes();
        System.out.println("eo");
        for(Cliente cliente : clientes){
            c.addItem(cliente.getDni());
        }
    }
    
    public static void llenarDesplegableAbogados(JComboBox<String> c) throws Exception{
        ArrayList<Abogado> abogados = TablaAbogados.queryAllAbogados();
        for(Abogado abogado : abogados){
            c.addItem(abogado.getDni());
        }
    }




}
