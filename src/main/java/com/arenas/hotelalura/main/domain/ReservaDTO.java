package com.arenas.hotelalura.main.domain;

import java.time.LocalDate;

public class ReservaDTO {

    private int id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int valor;
    private String formaPago;

    public ReservaDTO() {
    }
    

//    Eliminar Reserva
    public ReservaDTO(int id) {
        this.id = id;
    }

//    Crear Reserva
    public ReservaDTO(LocalDate fechaEntrada, LocalDate fechaSalida, int valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

//    Editar/Actualizar Reserva
    public ReservaDTO(int id, LocalDate fechaEntrada, LocalDate fechaSalida, int valor, String formaPago) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva{");
        sb.append("id: ").append(getId());
        sb.append(", fechaEntrada: ").append(getFechaEntrada());
        sb.append(", fechaSalida: ").append(getFechaSalida());
        sb.append(", valor: ").append(getValor());
        sb.append(", formaPago: ").append(getFormaPago());
        sb.append('}');
        return sb.toString();
    }

}
