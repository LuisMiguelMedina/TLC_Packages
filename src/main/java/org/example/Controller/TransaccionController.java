package org.example.Controller;

import org.example.Model.Cuenta;
import org.example.Model.Deposito;
import org.example.Model.Retiro;
import org.example.Service.Query;
import java.sql.SQLException;
import java.util.List;
public class TransaccionController {
    //Deposito
    public boolean validarDatosDeposito(Deposito deposito) throws SQLException {
        if (Double.valueOf(deposito.getMonto()).equals(0.0) || deposito.getClabeDestino().isEmpty()){
            return false;
        }
        if (deposito.getMonto() <= 0){
            System.out.println("Monto debe ser mayor que 0");
            return false;
        }
        if (!deposito.getClabeDestino().matches("^0*[1-9][0-9]*$")) {
            return false;
        }
        if (!validarCuentaDestino(deposito)){
            return false;
        }
        return true;
    }
    public boolean validarSaldo(Deposito deposito, Cuenta cuenta) {
        if (cuenta.getSaldo() < deposito.getMonto()){
            System.out.println("Fondos insuficientes");
            return false;
        }
        return true;
    }
    public boolean validarCuentaDestino(Deposito deposito) throws SQLException {
        Query query = new Query(new ConexionController());
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        for (String[] registro : registrosCuentas) {
            if (registro[3].equals(deposito.getClabeDestino())) {
                return true;
            }
        }
        System.out.println("Cuenta destino no encontrada");
        return false;
    }
    public void hacerDeposito(Deposito deposito, Cuenta cuenta) throws SQLException{
        CuentaController cuentaController = new CuentaController();
        Query query = new Query(new ConexionController());
        if (cuentaController.validarDatosCuenta(cuenta)) {
            if (validarDatosDeposito(deposito) && validarSaldo(deposito, cuenta)) {
                deposito.depositarMonto(cuenta, deposito);
                System.out.println("Deposito realizado! " + deposito.getMonto() + " a la cuenta de " + query.obtenerNombreClientePorId(cuenta.getIdCliente()));
            } else
                System.out.println("Datos para deposito invalidos");
        } else
            System.out.println("Error deposito no realizado");
    }
    //Retiro
    public boolean validarDatosRetiro(Retiro retiro) {
        if (Double.valueOf(retiro.getMonto()).equals(0.0)){
            return false;
        }
        if (!String.valueOf(retiro.getMonto()).matches("^0*[1-9][0-9]*(\\.[0-9]{1,2})?$")) {
            return false;
        }
        return true;
    }
    public boolean validarSaldo(Retiro retiro, Cuenta cuenta) {
        if (cuenta.getSaldo() < retiro.getMonto()){
            System.out.println("Fondos insuficientes");
            return false;
        }
        return true;
    }
    public void hacerRetiro(Retiro retiro, Cuenta cuenta) throws SQLException {
        CuentaController cuentaController = new CuentaController();
        if (cuentaController.validarDatosCuenta(cuenta)) {
            if (validarDatosRetiro(retiro) && validarSaldo(retiro, cuenta)) {
                retiro.hacerRetiro(cuenta);
                System.out.println("Retiro realizado!");
            } else
                System.out.println("Datos para retiro invalidos");
        } else
            System.out.println("Error retiro no realizado");
    }
    public void eliminarHistorialTransferencias(Cuenta cuenta) throws SQLException {
        CuentaController cuentaController = new CuentaController();
        Query query = new Query(new ConexionController());
        if (cuentaController.validarDatosCuenta(cuenta)) {
            query.eliminarTransferenciasDeCuenta(cuenta.getClabe());
        } else
            System.out.println("Error datos incorrectos");
    }
}