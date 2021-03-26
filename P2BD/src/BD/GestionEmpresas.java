/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Empresa;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 1gdaw06
 */
public class GestionEmpresas {
    private static Connection con;

    public GestionEmpresas(Connection con) {
        this.con = con;
    }
    
    
    public static boolean existeEmpresa(String nombre){
        
        try {
            PreparedStatement ps = con.prepareStatement("SELECT 'X' FROM EMPRESAS WHERE UPPER(NOMBRE)=UPPER(?);");
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
    
    public static Empresa obtenerEmpresa(String nombre){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPRESAS WHERE UPPER(NOMBRE)=UPPER(?);");
            ps.setString(1, nombre);
            
            ResultSet resultado = ps.executeQuery();
            
            Empresa e = null;
            if(resultado.next()){
                e = new Empresa();
                e.setNif(resultado.getNString("nif"));
                e.setNombre(resultado.getNString("nombre"));   
            }
            
            return e;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public static boolean insertarEmpresa(Empresa e){
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO EMPRESAS VALUES (?, ?)");
            ps.setString(1, e.getNif());
            ps.setString(2, e.getNombre());
            
            ps.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean borrarEmpresa(String nif){
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM EMPRESAS WHERE nif=?;");
            ps.setString(1, nif);
            
            int n = ps.executeUpdate();
            
            if(n>1)
                System.out.println("Se ha borrado mas de una empresa");
            
            return true;    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
}
