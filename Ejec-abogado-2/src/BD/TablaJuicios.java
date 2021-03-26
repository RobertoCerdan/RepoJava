/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Juicio;
import Modelo.estadoJuicio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rober
 */
public class TablaJuicios {
    
    private static Connection con;
    
    public static boolean insertJuicio(Juicio j) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("INSERT INTO JUICIOS VALUES (?, ?, ? ,?, ?);");
        ps.setString(1, j.getId());
        ps.setString(2, j.getCliente().getDni());
        ps.setDate(3, Date.valueOf(j.getFechaInicio()));
        ps.setDate(4, Date.valueOf(j.getFechaFin()));
        ps.setString(5, String.valueOf(j.getEstado()));
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
    }
    
    public static Juicio queryJuicioById(String id) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM JUICIOS WHERE UPPER(ID)=?;");
        ps.setString(1, id);
       
        
        ResultSet r= ps.executeQuery();
        
        BD.cerrarBD();
        
        Juicio j = null;
        if(r.next()){
            j = new Juicio();
            j.setId(r.getString("id"));
            j.setCliente(TablaClientes.queryClienteByName(r.getNString("cliente")));
            j.setFechaInicio(r.getDate("fechainicio").toLocalDate());
            j.setFechaFin(r.getDate("fechafin").toLocalDate());
            j.setEstado(estadoJuicio.valueOf(r.getString("estado")));
            j.setAbogados(TablaJuiciosAbogados.queryAbogadosByJuicioId(r.getString("id")));
            
            return j;
        }
        return null;
    }
    
    


    public static boolean removeJuicio(String id) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("DELETE FROM JUICIOS WHERE UPPER(ID)=?;");
        ps.setString(1, id);
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
    }
    
    public static boolean  editJuicio(Juicio j) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("UPDATE JUICIOS SET (CLIENTE=?, FECHAINICIO=?, FECHAFIN=?, ESTADO=?)"
                + "WHERE UPPER(ID)=?;");
        ps.setString(1, j.getCliente().getDni());
        ps.setDate(2, Date.valueOf(j.getFechaInicio()));
        ps.setDate(3, Date.valueOf(j.getFechaFin()));
        ps.setString(4, String.valueOf(j.getEstado()));
        ps.setString(5, j.getId());
        
        int n = ps.executeUpdate();
        
        BD.cerrarBD();
        
        if(n!=1){
            return false;
        }
        return true;
    }
}