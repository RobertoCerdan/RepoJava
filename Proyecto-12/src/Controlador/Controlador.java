/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import modelo.*;
import BD.*;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import javax.swing.JFrame;
/**
 *
 * @author 1gdaw06
 */
public class Controlador {

    //Incializacion de variables tipo vista...
    private static V1 v1;
    private static VAltaVehiculo valtavehiculo;
    private static VBajaVehiculo vbajavehiculo;
    
    //Incializacion de variables globales de los objetos modelo
    private static Coche coche;
    private static Conductor conductor;
    private static Multa multa;
    
    //Incializacion de los "objeto-tabla-conexion"(los jpa vamos)
    private static CocheJpaController bdcoche;
    private static ConductorJpaController bdconductor;
    private static MultaJpaController bdmulta;
    
    public static void main(String[] args) {
        abrirVentanaPrincipal();
        bdcoche = new CocheJpaController(Persistence.createEntityManagerFactory("Proyecto-12PU"));
        bdconductor = new ConductorJpaController(Persistence.createEntityManagerFactory("Proyecto-12PU"));
        bdmulta = new MultaJpaController(Persistence.createEntityManagerFactory("Proyecto-12PU"));
    }
    
    
    
    
    
    
    //***********+Funciones Vehiculo***************************
    
    public static void crearVehiculo
    
    
    
    
    //*************Funciones gestion ventanas*******************
    
    public static void abrirVentanaPrincipal(){
        v1 = new V1();
        v1.setVisible(true);
    }
    
    private static void abrirVAltaVechiculo(){
        valtavehiculo = new VAltaVechiculo();
        valtavehiculo.setVisible(true);
    }
    
    private static void abrirVBajaVehiculo(){
        vbajavehiculo = new VBajaVehiculo();
        vbajavehiculo.setVisible(true);
    }
    
    private static void salir(){
        v1.dispose();
        System.exit(0);
    }
    
    public static void cerrarVentana(JFrame v){
        v.dispose();
    }
    
    public static void cerrarVentana(JDialog v){
        v.dispose();
    }
}
