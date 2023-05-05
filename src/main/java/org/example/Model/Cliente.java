package org.example.Model;
public class Cliente {
    private final String idCliente;
    private final String nombre;
    private final String telefono;
    private final String correo;
    public Cliente(String idCliente, String nombre, String telefono, String correo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }
    public String getIdCliente() {
        return idCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCorreo() {
        return correo;
    }
    @Override
    public String toString() {
        return String.format("ID: %s%nNombre: %s%nTeléfono: %s%nCorreo: %s", idCliente, nombre, telefono, correo);
    }
}