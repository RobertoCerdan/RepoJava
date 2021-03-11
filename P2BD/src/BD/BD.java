package BD;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1gdaw06
 */
public class BD {
    
    private Connection con;

    public BD(Connection con) {
        this.con = con;
    }
    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String bd = "bd";
            String url = "jdbc:mysql://localhost:3306/"+bd;
            String usuario = "root";
            String clave = "usbw";
            
            con = DriverManager.getConnection(url, usuario, clave);
            
            if(con==null){
                throw new Exception("Problemas con la conexion a la BD");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return con;
    }
    
    public void desconectar(){
       try
       {
            con.close();
       }
       catch(Exception e)
       {
           System.out.println("Problemas cerrando la conexión");
       }
   }
    
    
}
