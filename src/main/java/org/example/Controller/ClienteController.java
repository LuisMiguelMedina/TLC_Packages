package org.example.Controller;

import org.example.Model.Cliente;
import org.example.Service.Query;
import java.sql.SQLException;
import java.util.List;
public class ClienteController {
    public boolean validarDatosCliente(Cliente cliente) {
        if (cliente.getIdCliente().isEmpty() || cliente.getNombre().isEmpty() || cliente.getTelefono().isEmpty() || cliente.getCorreo().isEmpty()) {
            return false;
        }
        if (!cliente.getNombre().matches("^[A-Za-z]+(?:\\s[A-Za-z]+){0,3}$")){
            return false;
        }
        if (!cliente.getCorreo().contains("@")) {
            return false;
        }
        if (!cliente.getTelefono().matches("^\\+\\d{1,3}-\\d{1,3}-\\d{3}-\\d{4}$")){
            return false;
        }
        return true;
    }
    public void crearCliente(Cliente cliente) throws SQLException {
        Query query = new Query(new ConexionController());
        String[] datosCliente = {
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getCorreo()
        };
        if (validarDatosCliente(cliente)) {
            query.escribirRegistroCliente(datosCliente);
            System.out.println("Cliente creado exitosamente.");
        } else {
            System.out.println("Datos de cliente no válidos. No se ha creado el cliente.");
        }
    }
    public void eliminarCliente(String idCliente) throws SQLException {
        Query query = new Query(new ConexionController());
        List<String[]> registrosClientes = query.obtenerRegistrosClientes();
        int i = 0;
        for (String[] registro : registrosClientes) {
            if (registro[0].equals(idCliente)) {
                query.eliminarRegistroCliente(idCliente);
                System.out.println("Cliente eliminado exitosamente.");
                return;
            }
            i++;
        }
    }
}