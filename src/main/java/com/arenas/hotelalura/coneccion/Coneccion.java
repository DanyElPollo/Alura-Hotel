package com.arenas.hotelalura.coneccion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Coneccion {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3308/hotel_alura?useTimeZone=true&serverTimeZone=UTC";
    private static final String USER= "root";
    private static final String PASSWORD= "admin";
    
    public void conn(){
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            if(conexion != null){
                System.out.println("Funciona");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }    
}
