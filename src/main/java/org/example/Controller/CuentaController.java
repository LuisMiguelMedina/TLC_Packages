package org.example.Controller;

import org.example.Model.Cuenta;
import org.example.Service.Query;
import java.sql.SQLException;
import java.util.List;
public class CuentaController {
    public boolean validarDatosCuenta(Cuenta cuenta) {
        if (cuenta.getIdCliente().isEmpty() || cuenta.getIdCuenta().isEmpty() || Double.valueOf(cuenta.getSaldo()).equals(0.0) || cuenta.getClabe().isEmpty())  {
            return false;
        }
        if (!cuenta.getIdCliente().matches("^C\\d{3}$")){
            return false;
        }
        if (!cuenta.getIdCuenta().matches("^CC\\d{2}$")) {
            return false;
        }
        if (!String.valueOf(cuenta.getSaldo()).matches("^(?:0|[1-9]\\d*)(?:\\.\\d+|\\.0+)?$")) {
            return false;
        }
        if (!cuenta.getClabe().matches("^0*[1-9][0-9]*$")){
            return false;
        }
        return true;
    }
    public void crearCuenta(Cuenta cuenta) throws SQLException {
        Query query = new Query(new ConexionController());
        String saldoCuenta = String.valueOf(cuenta.getSaldo());
        String[] datosCuentas = {
                cuenta.getIdCliente(),
                cuenta.getIdCuenta(),
                saldoCuenta,
                cuenta.getClabe()
        };
        if (validarDatosCuenta(cuenta)) {
            query.escribirRegistroCuenta(datosCuentas);
            System.out.println("Cuenta creada exitosamente.");
        }
        else {
            System.out.println("Datos de cuenta no válidos. No se ha creado la cuenta.");
        }
    }
    public void eliminarCuenta(Cuenta cuenta) throws SQLException {
        TransaccionController transaccionController = new TransaccionController();
        Query query = new Query(new ConexionController());
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        int i = 0;
        for (String[] registro : registrosCuentas) {
            if (registro[0].equals(cuenta.getIdCliente()) && registro[1].equals(cuenta.getIdCuenta())) {
                transaccionController.eliminarHistorialTransferencias(cuenta);
                query.eliminarRegistroCuenta(cuenta.getClabe());
                System.out.println("Cuenta eliminada exitosamente.");
                return;
            }
            i++;
        }
    }
    public double validarSaldoCuenta(String cuentaClabe) {
        Query query = new Query(new ConexionController());
        try {
            String saldo = query.obtenerSaldoCuentas(cuentaClabe);
            return Double.parseDouble(saldo);
        }
        catch (NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}