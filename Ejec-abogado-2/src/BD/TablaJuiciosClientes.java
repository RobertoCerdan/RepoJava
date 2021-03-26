/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Cliente;
import Modelo.Juicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class TablaJuiciosClientes {
    
    private static Connection con;
    
    public static ArrayList<Juicio> queryJuicioByDni(String dni) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM JUICIOSCLIENTES WHERE UPPER(dni)=?;");
        ps.setString(1, dni);
       
        
        ResultSet r= ps.executeQuery();
        
        BD.cerrarBD();
        
        Juicio j = null;
        ArrayList<Juicio> juicios = new ArrayList();
        while(r.next()){
            j = new Juicio();
            j.setId(r.getString("id"));
            j.setCliente(TablaClientes.queryClienteByName(r.getNString("cliente")));
            j.setFechaInicio(r.getDate("fechainicio").toLocalDate());
            j.setFechaFin(r.getDate("fechafin").toLocalDate());
            j.setEstado(Modelo.estadoJuicio.valueOf(r.getString("estado")));
            juicios.add(j);
        }
        if(juicios.isEmpty()){
            return null;
        }
        else{
            return juicios;
        }
    }
    
    public static ArrayList<Cliente> queryClienteByJuicioId(String id) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT DNI FROM JUICIOSCLIENTES WHERE UPPER(ID)=?;");
        ps.setString(1, id);
       
        
        ResultSet r= ps.executeQuery();
        
        BD.cerrarBD();
        
        Cliente c = null;
        ArrayList<Cliente> clientes = new ArrayList();
        while(r.next()){
            c = TablaClientes.queryClienteByDni(r.getString("dni"));
            Juicio j = TablaJuicios.queryJuicioById(r.getString("juicio"));
            c.setJuicios(TablaJuiciosClientes.queryJuicioByDni(c.getDni()));
            clientes.add(c);
        }
        if(clientes.isEmpty()){
            return null;
        }
        else{
            return clientes;
        }
    }
    
    
}
