/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.Abogado;
import Modelo.Juicio;
import Modelo.estadoJuicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 1gdaw06
 */
public class TablaJuiciosAbogados {
    
    static Connection con;
    
    public static ArrayList<Abogado> queryAbogadosByJuicioId(String id) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM JUICIOSABOGADOS WHERE UPPER(ID)=?;");
        ps.setString(1, id);
        
        ResultSet r = ps.executeQuery();
        
        BD.cerrarBD();
        
        ArrayList<Abogado> abogados = new ArrayList();
        while(r.next()){
            Abogado a = new Abogado();
            a.setDni(r.getString("dni"));
            a.setNombre(r.getString("nombre"));
            a.setApellidos(r.getString("apellidos"));
            a.setDir(r.getString("dir"));
            abogados.add(a);
        }
        if(abogados.isEmpty()){
            return null;
        }
        return abogados;
    }
    
    
    public static ArrayList<Juicio> queryJuiciosByAbogadoDni(String dni) throws Exception{
        BD.abrirBD();
        con = BD.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM JUICIOSABOGADOS WHERE UPPER(DNI)=?;");
        ps.setString(1, dni);
        
        ResultSet r = ps.executeQuery();
        
        BD.cerrarBD();
        
        ArrayList<Juicio> juicios = new ArrayList();
        while(r.next()){
            Juicio j = new Juicio();
            j.setId(r.getString("id"));
            j.setCliente(TablaClientes.queryClienteByDni(r.getString("dnic")));
            j.setFechaInicio(r.getDate("fechainicio").toLocalDate());
            j.setFechaFin(r.getDate("fechafin").toLocalDate());
            j.setEstado(estadoJuicio.valueOf(r.getString("estado")));
            j.setAbogados(TablaJuiciosAbogados.queryAbogadosByJuicioId(r.getString("id")));
            juicios.add(j);
        }
        if(juicios.isEmpty()){
            return null;
        }
        return juicios;
    }
}
