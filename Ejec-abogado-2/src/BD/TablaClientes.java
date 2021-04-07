/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class TablaClientes {
    
    private static Connection con;

    public TablaClientes(Connection con) {
        this.con = con;
    }
    
    public static Cliente queryClienteByName(String nombre) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CLIENTES WHERE UPPER(NOMBRE)=?;");
        ps.setString(1, nombre);
        
        ResultSet r = ps.executeQuery();
        
        
        
        Cliente c = null;
        while(r.next()){
            c = new Cliente();
            c.setNombre(r.getString("nombre"));
            c.setApellidos(r.getString("apellidos"));
            c.setEmail(r.getString("email"));
            c.setJuicios(TablaJuiciosClientes.queryJuicioByDni(r.getString("dni")));
        }
        
        BD.cerrarBD();
        
        return c;
    }
    
    public static Cliente queryClienteByDni(String dni) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CLIENTES WHERE UPPER(DNI)=?;");
        ps.setString(1, dni);
        
        ResultSet r = ps.executeQuery();
        
        
        
        Cliente c = null;
        while(r.next()){
            c = new Cliente();
            c.setDni(dni);
            c.setNombre(r.getString("nombre"));
            c.setApellidos(r.getString("apellidos"));
            c.setEmail(r.getString("email"));
            c.setJuicios(TablaJuiciosClientes.queryJuicioByDni(r.getString("dni")));
        }
        
        BD.cerrarBD(); 
        
        return c;
    }
    
    public static ArrayList<Cliente> queryAllClientes() throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CLIENTES;");

        
        ResultSet r = ps.executeQuery();
        
        
        ArrayList<Cliente> clientes= new ArrayList();
        Cliente c = null;
        while(r.next()){
            c = new Cliente();
            c.setDni(r.getString("dni"));
            c.setNombre(r.getString("nombre"));
            c.setApellidos(r.getString("apellidos"));
            c.setEmail(r.getString("email"));
            c.setJuicios(TablaJuiciosClientes.queryJuicioByDni(r.getString("dni")));
            clientes.add(c);
        }
        
        BD.cerrarBD();
        
        
        if(!clientes.isEmpty()){
            return clientes;
        }
        return null;
    }
    
    
    public static boolean insertCliente(Cliente c) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("INSERT INTO CLIENTES VALUES (?, ?, ?, ?);");
        
        ps.setString(1, c.getDni());
        ps.setString(2, c.getNombre());
        ps.setString(3, c.getApellidos());
        ps.setString(4, c.getEmail());
               
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;

    }
    
    public static boolean removeCliente(String dni) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("DELETE FROM CLIENTES WHERE UPPER(dni)=?;");
        ps.setString(1, dni);
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
    }
    
    public static boolean editCliente(Cliente c) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("UPDATE CLIENTES SET NOMBRE=?, APELLIDOS=?, EMAIL=? WHERE UPPER(dni)=?;");
        ps.setString(1, c.getNombre());
        ps.setString(2, c.getApellidos());
        ps.setString(3, c.getEmail());
        ps.setString(4, c.getDni());
    
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
    }
    
}
