/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Evento;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1gdaw06
 */
public class GestionEventos {
    
    private static Connection con;

    public GestionEventos(Connection con) {
        this.con = con;
    }
    
    public static ArrayList<String> obtenerListaEventos(){
  
        try {
            PreparedStatement ps = con.prepareStatement("SELECT NOMBRE FROM EVENTOS;");
            
            ResultSet r = ps.executeQuery();

            int contador = 0;            
            ArrayList<String> eventos = null;
            if(r.next()){
                eventos = new ArrayList();
                while(r.next()){
                    eventos.add(r.getString(contador));
                }
            }
            return eventos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "fallooooo");
            return null;
        }
    }
    
    
    
    public static boolean existeEvento(String nombre){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT 'X' FROM EVENTOS WHERE UPPER(NOMBRE)=UPPER(?);");
            ps.setString(1, nombre);
            
            ResultSet resultado = ps.executeQuery();
            
            boolean existe = false;
            if(resultado.next())
                existe = true;
            
            return existe;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static void insertarEvento(Evento e) throws SQLException{
        
        String plantilla = "INSERT INTO eventos VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLugar());
        ps.setDate(3, Date.valueOf(e.getFecha()));
        ps.setTime(4, Time.valueOf(e.gethInicio()));
        ps.setTime(5, Time.valueOf(e.gethFin()));
        ps.setInt(6, e.getAforo());
        
        
        int n = ps.executeUpdate();
        
        
        

    }
    
    public static void cancelarEvento(String nombre) throws SQLException{
        
        String plantilla = "DELETE FROM eventos WHERE nombre=?;";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(1, nombre);
        
        
        int n = ps.executeUpdate();
        System.out.println(n);
        if(n>1)
            System.out.println("Se ha cancelado mas de 1 registro");
        
        

    }
    
    public static void editarEvento(Evento e) throws SQLException{
        
        String plantilla = "UPDATE eventos SET lugar=?, fecha=?, hInicio=?, hFin=?, aforo=?"
                + " WHERE nombre=?;";
        PreparedStatement ps = con.prepareStatement(plantilla);
        
        ps.setString(1, e.getLugar());
        ps.setDate(2, Date.valueOf(e.getFecha()));
        ps.setTime(3, Time.valueOf(e.gethInicio()));
        ps.setTime(4, Time.valueOf(e.gethFin()));
        ps.setInt(5, e.getAforo());
        ps.setString(6, e.getNombre());
        
        System.out.println(ps);
        int n = ps.executeUpdate();
        
        if(n>1)
            System.out.println("Se ha editado mas de 1 registro");
        

    }
    
    public static Evento obtenerEvento(String nombre) throws SQLException{
        
        String plantilla = "SELECT * FROM eventos WHERE UPPER(nombre)=UPPER(?)";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(1, nombre);
        
        
        ResultSet r = ps.executeQuery();
        
        Evento e = null;
        
        while(r.next()){
            java.sql.Date f = r.getDate("fecha");
            System.out.println(f);
            //DateFormatter formatter = DateFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = f.toLocalDate();
            Time horaInicio = r.getTime("hInicio");
            LocalTime hInicio = horaInicio.toLocalTime();
            Time horaFin = r.getTime("hFin");
            LocalTime hFin = horaFin.toLocalTime();

            

            
            e = new Evento();
            e.setNombre(r.getString("nombre"));
            e.setLugar(r.getString("lugar"));
            e.setFecha(fecha);
            e.sethInicio(hInicio);
            e.sethFin(hFin);
            e.setAforo(r.getInt("aforo"));
            
  
            
        }
        
        return e;
        

    }
    
    
    
}
