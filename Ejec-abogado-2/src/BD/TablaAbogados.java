/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Abogado;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class TablaAbogados {
    
    static Connection con;
    
    public static boolean insertAbogado(Abogado a) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("INSERT INTO ABOGADOS VALUES (?, ?, ?, ?);");
        ps.setString(1, a.getDni());
        ps.setString(2, a.getNombre());
        ps.setString(3, a.getApellidos());
        ps.setString(4, a.getDir());
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
        
    }
    
    
    public static ArrayList<Abogado> queryAllAbogados() throws Exception{
            BD.abrirBD();
            con = BD.getCon();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ABOGADOS;");
            
            
            ResultSet r = ps.executeQuery();
            
            
            
            ArrayList<Abogado> abogados= new ArrayList();
            Abogado a = null;
            while(r.next()){
                a = new Abogado();
                a.setDni(r.getString("dni"));
                a.setNombre(r.getString("nombre"));
                a.setApellidos(r.getString("apellidos"));
                a.setDir(r.getString("dir"));
                
                abogados.add(a);
            }
            
            BD.cerrarBD();
            
            if(!abogados.isEmpty()){
                return abogados;
            }
         
            return null;
        
    }
    
    
    public static Abogado queryAbogadoByDni(String dni) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM ABOGADOS WHERE UPPER(DNI)=?;");
        ps.setString(1, dni);
 
        
        ResultSet r = ps.executeQuery();
        
        
        
        Abogado a = null;
        while(r.next()){
            a = new Abogado();
            a.setDni(dni);
            a.setNombre(r.getString("nombre"));
            a.setApellidos(r.getString("apellidos"));
            a.setDir(r.getString("dir"));
        }
        
        BD.cerrarBD();
        
        return a;
        
    }
        
    public static boolean editAbogado(Abogado a) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("UPDATE ABOGADOS SET NOMBRE=?, APELLIDOS=?, DIR=? WHERE UPPER(DNI)=?;");
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getApellidos());
        ps.setString(3, a.getDir());
        ps.setString(4, a.getDni());
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
        
    }
    
    public static boolean removeAbogado(String dni) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("DELETE FROM ABOGADOS WHERE DNI=?;");
        ps.setString(1, dni);
 
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
        
    }

}
