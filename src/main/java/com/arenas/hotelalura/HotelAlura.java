package com.arenas.hotelalura;

import com.arenas.hotelalura.coneccion.Coneccion;

public class HotelAlura {

    public static void main(String[] args) {
        Coneccion conn = new Coneccion();
        conn.conn();
    }
}
