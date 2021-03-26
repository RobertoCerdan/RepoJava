/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rober
 */
public class BD {
   private static Connection con;

   public static void abrirBD() throws SQLException, ClassNotFoundException{
       Class.forName("com.mysql.jdbc.Driver");
       String url = "jdbc:mysql://localhost:3306/"+"bufete";
       con = DriverManager.getConnection(url, "root", "usbw");
   }

    public static Connection getCon() {
        return con;
    }

    public static void cerrarBD() throws Exception {
        con.close();
    }
   
   
}
