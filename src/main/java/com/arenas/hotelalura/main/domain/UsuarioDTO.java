package com.arenas.hotelalura.main.domain;

public class UsuarioDTO extends Persona {

    private String email;
    private String contra;

//    listar en BD
    public UsuarioDTO() {
        super();
    }

//    eliminar en BD
    public UsuarioDTO(int id) {
        super(id);
    }

//    login en plataforma
    public UsuarioDTO(String email, String contra) {
        this.email = email;
        this.contra = contra;
    }

//    Crear y Actualizar en BD
    public UsuarioDTO(int id, String nombre, String apellido, long telefono, String email, String contra) {
        super(id, nombre, apellido, telefono);
        this.email = email;
        this.contra = contra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("Dni: ").append(super.getId());
        sb.append(", ");
        sb.append(super.toString());
        sb.append(", email: ").append(getEmail());
        sb.append(", contra: ").append(getContra());
        sb.append('}');
        return sb.toString();
    }

}
