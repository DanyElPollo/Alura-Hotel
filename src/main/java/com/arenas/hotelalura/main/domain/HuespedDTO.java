package com.arenas.hotelalura.main.domain;

import java.time.LocalDate;

public class HuespedDTO extends Persona {

    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private ReservaDTO reserva;

//    listar en BD
    public HuespedDTO() {
        super();
    }

//    eliminar en BD
    public HuespedDTO(int id) {
        super(id);
    }

//    crear en BD
    public HuespedDTO(String nombre, String apellido, long numero, LocalDate fechaNacimiento, String nacionalidad, ReservaDTO reserva) {
        super(nombre, apellido, numero);
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.reserva = reserva;
    }

    public HuespedDTO(int id, String nombre, String apellido, long numero, LocalDate fechaNacimiento, String nacionalidad, ReservaDTO reserva) {
        super(id, nombre, apellido, numero);
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.reserva = reserva;
    }

//    Actualizar en la BD
    public HuespedDTO(int id, String nombre, String apellido, long telefono, LocalDate fechaNacimiento, String nacionalidad) {
        super(id, nombre, apellido, telefono);
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public ReservaDTO getReserva() {
        return reserva;
    }

    public void setReserva(ReservaDTO reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Huesped{");
        sb.append(super.toString());
        sb.append(", fechaNacimiento: ").append(getFechaNacimiento());
        sb.append(", nacionalidad: ").append(getNacionalidad());
        sb.append(", Reserva: ").append(getReserva());
        sb.append('}');
        return sb.toString();
    }

}
