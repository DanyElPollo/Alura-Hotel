package com.arenas.hotelalura.main.domain;

public class Persona {

    private int id;
    private String nombre;
    private String apellido;
    private long telefono;
    
//    listar en BD
    public Persona() {

    }
    
//    eliminar en BD
    public Persona(int id) {
        this.id = id;
    }

//    Crear en BD    
    public Persona(String nombre, String apellido, long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

//    Actualizar en BD
    public Persona(int id, String nombre, String apellido, long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId());
        sb.append(", nombre: ").append(getNombre());
        sb.append(", apellido: ").append(getApellido());
        sb.append(", telefono: ").append(getTelefono());
        return sb.toString();
    }

    
}
