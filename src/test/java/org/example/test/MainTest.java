package org.example.test;

import org.example.Controller.*;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;
import org.example.Model.Deposito;
import org.example.Model.Retiro;
import org.example.Service.Query;
import org.example.Utilities.KeyManager;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MainTest {
    private String tempKey = "1415926535";
    ConexionController conexionController = new ConexionController();
    ClienteController clienteController = new ClienteController();
    CuentaController cuentaController = new CuentaController();
    TransaccionController transaccionController = new TransaccionController();
    Cliente cliente1 = new Cliente("C999","Juan Perez", "+1-555-123-4567","jperez@mail.com");
    Cliente cliente2 = new Cliente("C998","Perez Juan Alfonso", "+1-555-123-4567","fma@gmail.com");
    Cuenta cuenta1 = new Cuenta(cliente1.getIdCliente(), "CC01", 500.0, "99901");
    Cuenta cuenta2 = new Cuenta("C998", "CC01", 500.0,"99801");

    public void setUp() {
        KeyManager.setTempKey(tempKey);
    }
    @Test
    public void testConexion() {
        setUp();
        conexionController.getConnection();
    }
    @Test
    public void testCrearCliente() throws SQLException {
        setUp();
        clienteController.crearCliente(cliente1);
        clienteController.eliminarCliente(cliente1.getIdCliente());
    }
    @Test
    public void testCrearCuenta() throws SQLException {
        setUp();
        clienteController.crearCliente(cliente1);
        cuentaController.crearCuenta(cuenta1);
        cuentaController.eliminarCuenta(cuenta1);
        clienteController.eliminarCliente(cliente1.getIdCliente());
    }
    @Test
    public void testHacerRetiro() throws SQLException {
        setUp();
        clienteController.crearCliente(cliente1);
        cuentaController.crearCuenta(cuenta1);
        Retiro retiro1 = new Retiro(50.00);
        transaccionController.hacerRetiro(retiro1,cuenta1);
        cuentaController.eliminarCuenta(cuenta1);
        clienteController.eliminarCliente(cliente1.getIdCliente());
    }
    @Test
    public void testHacerDeposito() throws SQLException {
        setUp();
        clienteController.crearCliente(cliente1);
        clienteController.crearCliente(cliente2);
        cuentaController.crearCuenta(cuenta1);
        cuentaController.crearCuenta(cuenta2);
        Deposito deposito1 = new Deposito(200.0, cuenta1.getClabe());
        transaccionController.hacerDeposito(deposito1,cuenta2);
        cuentaController.eliminarCuenta(cuenta1);
        cuentaController.eliminarCuenta(cuenta2);
        clienteController.eliminarCliente(cliente1.getIdCliente());
        clienteController.eliminarCliente(cliente2.getIdCliente());
    }
    @Test
    public void testCrearCuentaView() throws SQLException {
        setUp();
        Query query = new Query(new ConexionController());
        clienteController.crearCliente(cliente1);
        double saldo = Double.parseDouble(String.valueOf(500));
        String idCuenta = query.generarNuevoIdCuenta();
        Cuenta cuenta = new Cuenta(cliente1.getIdCliente(), idCuenta,saldo, query.generarCLABE(cliente1.getIdCliente(),idCuenta));
        cuentaController.crearCuenta(cuenta);
        cuentaController.eliminarCuenta(cuenta);
        clienteController.eliminarCliente(cliente1.getIdCliente());
    }
}