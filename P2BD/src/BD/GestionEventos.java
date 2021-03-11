/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Evento;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 *
 * @author 1gdaw06
 */
public class GestionEventos {
    
    private Connection con;

    public GestionEventos(Connection con) {
        this.con = con;
    }
    
    public void insertarEvento(String nombre, String lugar, LocalDate fecha, LocalTime hInicio, LocalTime hFin, int aforo) throws SQLException{
        
        String plantilla = "INSERT INTO eventos VALUES (?, ?, ?, ? , ?, ?);";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(1, nombre);
        ps.setString(2, lugar);
        ps.setString(3, String.valueOf(fecha));
        ps.setString(4, String.valueOf(hInicio));
        ps.setString(5, String.valueOf(hFin));
        ps.setString(6, String.valueOf(aforo));
        
        int n = ps.executeUpdate();
        
        if(n>1)
            System.out.println("Se han borrado mas de 1 registro");
        

    }
    
    
    public void cancelarEvento(String nombre) throws SQLException{
        
        String plantilla = "DELETE FROM eventos WHERE nombre=?;";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(0, nombre);
        
        
        int n = ps.executeUpdate();
        
        if(n>1)
            System.out.println("Se ha cancelado mas de 1 registro");
        
        

    }
    
    public void editarEvento(String nombre, String lugar, LocalDate fecha, LocalTime hInicio, LocalTime hFin, int aforo) throws SQLException{
        
        String plantilla = "UPDATE eventos SET (nombre, lugar, fecha, hInicio, hFin, aforo) = (?, ?, ?, ? , ?, ?);"
                + "WHERE nombre=?";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(1, nombre);
        ps.setString(2, lugar);
        ps.setString(3, String.valueOf(fecha));
        ps.setString(4, String.valueOf(hInicio));
        ps.setString(5, String.valueOf(hFin));
        ps.setString(6, String.valueOf(aforo));
        
        int n = ps.executeUpdate();
        
        if(n>1)
            System.out.println("Se ha editado mas de 1 registro");
        

    }
    
    public Evento mostrarEvento(String nombre) throws SQLException{
        
        String plantilla = "SELECT * FROM eventos WHERE UPPER(nombre)=UPPER(?)";
        PreparedStatement ps = con.prepareStatement(plantilla);
        ps.setString(1, nombre);
        
        
        ResultSet r = ps.executeQuery();
        
        while(r.next()){
            java.util.Date fecha = r.getDate("fecha");

  

            String currentTime = sdf.format(fecha);
            
            Evento e = new Evento();
            e.setNombre(r.getString("nombre"));
            e.setLugar(r.getString("lugar"));
            DateFormat.
            e.setFecha(java.sql.Date.);
            e.setAforo(0);
            
        }
        

    }
    
    
    
}
