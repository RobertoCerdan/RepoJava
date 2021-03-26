/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1gdaw06
 */
public class GestionPersonasEventos {
    
    private static Connection con;

    public GestionPersonasEventos(Connection con) {
        this.con = con;
    }
    
    public static ArrayList<Persona> obtenerPersonasEvento(String nombre){
        try {
            PreparedStatement ps =con.prepareStatement("SELECT * FROM EVENTOSPERSONAS WHERE UPPER(EVENTO)=?;");
            ps.setString(1, nombre);
            
            ResultSet r = ps.executeQuery();
            
            ArrayList<Persona> personas = new ArrayList();
            if(r.next()){
                Persona p = new Persona();
                p = GestionPersonas.obtenerPersona(r.getString("nombre"));
                personas.add(p);
            }
            return personas;
            
            
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
        }
    }
    
}
