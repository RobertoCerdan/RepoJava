/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1gdaw06
 */
public class GestionPersonas {
    
    private static Connection con;

    public GestionPersonas(Connection con) {
        this.con = con;
    }
    
    public static boolean existePersona(String nombre){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT 'X' FROM PERSONAS WHERE UPPER(NOMBRE)=UPPER(?);");
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
    
    public static boolean insertarPersona(Persona p){
        
        try {
            String plantilla = "INSERT INTO PERSONAS VALUES (?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDni());
            ps.setString(3, p.getEmpresa().getNif());
      
            int n = ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        
        

    }
    
    public static void borrarPersona(String nombre) {
        

        try {
            String plantilla = "DELETE FROM PERSONAS WHERE nombre=? CASCADE CONSTRAINTS;";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1, nombre);
            int n = ps.executeUpdate();
            System.out.println(n);
            if(n>1)
                System.out.println("Se ha borrado mas de 1 persona");
        } catch (SQLException ex) {
            Logger.getLogger(GestionPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
    
    public static Persona obtenerPersona(String nombre){
        
        try {
            String plantilla = "SELECT * FROM PERSONAS WHERE UPPER(nombre)=UPPER(?)";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1, nombre);
            
            
            ResultSet r = ps.executeQuery();
            
            Persona p = null;  
            while(r.next()){
                p.setNombre(r.getString("nombre"));
                p.setDni(r.getString("dni"));
                p.setEmpresa(GestionEmpresas.obtenerEmpresa(r.getString("empresa")));
            }
            return p;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
