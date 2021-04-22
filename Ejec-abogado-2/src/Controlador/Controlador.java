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
import Vista.VAnadirCliente;
import Vista.VAnadirJuicio;
import Vista.VBorrarAbogados;
import Vista.VBorrarCliente;
import Vista.VEditarAbogado;
import Vista.VEditarCliente;
import Vista.VMostraDatosCliente;
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
    
    public static VMostraDatosCliente vmostrardatoscliente;
    public static VAnadirCliente vanadircliente;
    public static VBorrarCliente vborrarcliente;
    public static VEditarCliente veditarcliente;
    public static VAnadirJuicio vanadirjuicio;
    public static VAnadirAbogado vanadirabogado;
    public static VBorrarAbogados vborrarabogado;
    public static VEditarAbogado veditarabogado;
    public static V1 v1;
    
    
    private static Juicio juicio;
    private static Abogado abogado;
    private static Cliente cliente;
    
    
    public static void main(String[] args) {
        v1 = new V1();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
        
        
        
    }
    
    public static void pedirClienteBy(String caso, String clave) throws Exception{
        switch(caso){
            case "DNI":
                cliente = TablaClientes.queryClienteByDni(clave);
                break;
            case "Nombre":
                cliente = BD.TablaClientes.queryClienteByName(clave);
                break;
            case "Apellidos":
                cliente = BD.TablaClientes.queryClienteByApellido(clave);
                break;
            case "Email":
                cliente = BD.TablaClientes.queryClienteByEmail(clave);
                break;
                        
        }
         
    }
    
    public static ArrayList<String> pedirDatosCliente(String dni){
        try {
            ArrayList<String> datos = new ArrayList();
            datos.add(cliente.getDni());
            datos.add(cliente.getNombre());
            datos.add(cliente.getApellidos());
            datos.add(cliente.getEmail());
            
            return datos;
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String pedirStringDatosCliente(){
        if(cliente != null)
            return cliente.toString();
        
        return null;           
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
        pedirClienteBy("DNI", dni);
        juicio = new Juicio(id, cliente, fechaInicio, fechaFin, estado);
        if(BD.TablaJuicios.insertJuicio(juicio)){
            return true;
        }
        return false;
    }
    
    public static boolean insertarJuicioConAbogado(String id, String dnicliente, LocalDate fechaInicio, LocalDate fechaFin, estadoJuicio estado, String dni) throws Exception{
        pedirClienteBy("DNI", dnicliente);
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
    
    public static ArrayList<String> pedirDatosAbogado(String dni){
        try {
            abogado = BD.TablaAbogados.queryAbogadoByDni(dni);
            ArrayList<String> datos = new ArrayList();
            datos.add(abogado.getDni());
            datos.add(abogado.getNombre());
            datos.add(abogado.getApellidos());
            datos.add(abogado.getDir());
            
            return datos;
            
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
    
    
    
    
    public static void abrirVEditarAbogado(){
        try {
            veditarabogado = new VEditarAbogado(null, true);
            veditarabogado.setLocationRelativeTo(null);
            veditarabogado.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de editar abogados");
            System.out.println(ex.getClass() + " " + ex.getMessage());
        }
    }
    
    public static void abrirVBorrarAbogado() {
        try {
            vborrarabogado = new VBorrarAbogados(null, true);
            vborrarabogado.setLocationRelativeTo(null);
            vborrarabogado.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de borrar abogados");
        }
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
            System.out.println(ex.getClass() + " " + ex.getMessage());
            System.out.println("Problemas al abrir la ventana de añadir juicios");
        }
    }
    
    
    
    public static void abrirVAnadirCliente() {
        try {
            vanadircliente = new VAnadirCliente(null, true);
            vanadircliente.setLocationRelativeTo(null);
            vanadircliente.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de añadir clientes");
        }
    }
    
    public static void abrirVEditarCliente(){
         try {
            veditarcliente = new VEditarCliente(null, true);
            veditarcliente.setLocationRelativeTo(null);
            veditarcliente.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de editar clientes");
        }
    }
    
    public static void abrirVBorrarCliente(){
         try {
            vborrarcliente = new VBorrarCliente(null, true);
            vborrarcliente.setLocationRelativeTo(null);
            vborrarcliente.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de borrar clientes");
        }
    }
    
    public static void abrirVMostraDatosCliente(){
        try {
            vmostrardatoscliente = new VMostraDatosCliente(null, true);
            vmostrardatoscliente.setLocationRelativeTo(null);
            vmostrardatoscliente.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Problemas al abrir la ventana de mostrar datos clientes");
        }
    }
    
    
    
    
    public static void llenarDesplegableEstados(JComboBox<String> c) throws Exception{
        for(estadoJuicio estado : Modelo.estadoJuicio.values()){
            c.addItem(estado.toString());
        }
    }
    public static void llenarDesplegableClientes(JComboBox<String> c) throws Exception{
        ArrayList<Cliente> clientes = TablaClientes.queryAllClientes();
        if(clientes == null){
            c.addItem("No hay abogados registrados ebn la BD");
        }
        else{
            for(Cliente cliente : clientes){
                c.addItem(cliente.getDni());
            }
        }
    }
    public static void llenarDesplegableAbogados(JComboBox<String> c) throws Exception{
        ArrayList<Abogado> abogados = TablaAbogados.queryAllAbogados();
        if(abogados==null){
            c.addItem("No hay abogados registrados ebn la BD");
        }
        else{
            for(Abogado abogado : abogados){
                c.addItem(abogado.getDni());
            }
    }
    }




}
