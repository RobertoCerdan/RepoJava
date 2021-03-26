/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2bd;

import BD.GestionEventos;
import static BD.GestionEventos.obtenerEvento;
import Modelo.Evento;
import Vista.V1;
import Vista.VAddinvitados;
import Vista.VAlta;
import Vista.VBorrarevento;
import Vista.VEditar;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 *
 * @author 1gdaw06
 */
public class P2BD {
    
    public static Evento evento;
    
    public static V1 v1;
    public static VAlta valta;
    public static VEditar veditar;
    public static VBorrarevento vborrar;
    public static GestionEventos GE;
    public static VAddinvitados vaddinvitados;//Clase que se relaciona 
    
    private static Connection con;
    
    public static void main(String[] args) {
        BD.BD.conectar();
        con = BD.BD.getConnection();
        
        GE = new GestionEventos(con);
        
        v1 = new V1();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
    }
    
    
    
    public static boolean generarEvento(String nombre, String lugar, LocalDate fecha, LocalTime hInicio, LocalTime hFin, int aforo){
        try{
            evento = new Evento(nombre, lugar, fecha, hInicio, hFin, aforo);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean insertarEvento(){
        try{
            BD.GestionEventos.insertarEvento(evento);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public static boolean editarEvento(){
        try{
            BD.GestionEventos.editarEvento(evento);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
   
   
    
    
     //funciones vista
    
    
    public static ArrayList<String> obtenerDatosEvento(String nombre) throws SQLException{
        Evento e = obtenerEvento(nombre);
        if(e==null){
            System.out.println("mierda");
        }
        ArrayList<String> datos = new ArrayList();
        datos.add(e.getLugar());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        datos.add(e.getFecha().format(formatter));
        datos.add(e.gethInicio().toString());
        datos.add(e.gethFin().toString());
        datos.add(String.valueOf(e.getAforo()));
        
        return datos;
    }
 
    public static void abrirVeditar(){
        veditar = new VEditar();
        veditar.setLocationRelativeTo(null);
        veditar.setVisible(true);
    }
    
    public static void abrirValta(){
        valta = new VAlta();
        valta.setLocationRelativeTo(null);
        valta.setVisible(true);
    }
    
    public static boolean abrirVAddInvitados(){
        try{
            vaddinvitados = new VAddinvitados();
            vaddinvitados.setLocationRelativeTo(v1);
            vaddinvitados.setVisible(true);
            return true;
        }catch(Exception e){
            System.out.println(e.getClass());
            return false;
        }
    }
    
    
    
    public static void abrirVBorrar(){
        vborrar = new VBorrarevento(null, true);
        vborrar.setLocationRelativeTo(null);
        vborrar.setVisible(true);
    }
    
    public static void cerrarVentana(JFrame v){
        v.dispose();
    }
    
    public void salir(){
        System.exit(0);
    }
    
}
