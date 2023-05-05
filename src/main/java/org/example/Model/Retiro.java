package org.example.Model;

import org.example.Controller.ConexionController;
import org.example.Service.Query;
import java.sql.SQLException;
import java.util.List;
public class Retiro extends Transaccion {
    public Retiro(double monto) {
        super(monto);
    }
    public void hacerRetiro(Cuenta cuenta) throws SQLException {
        Query query = new Query(new ConexionController());
        String saldoCuentaString = query.obtenerSaldoCuentas(cuenta.getClabe());
        double saldoCuenta = Double.parseDouble(saldoCuentaString);
        double fondoTotal = saldoCuenta - monto;
        String fondoTotalString = Double.toString(fondoTotal);
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        int i = 0;
        for (String[] registro : registrosCuentas) {
            if (registro[3].equals(cuenta.getClabe())) {
                query.actualizarCeldaSaldo(cuenta.getClabe(), fondoTotalString);
            }
            i++;
        }
        String[] retiroRegistro = {cuenta.getClabe(), "-" + monto, Transaccion.Fecha.getCurrentTimestamp()};
        query.escribirRegistroTransaccion(retiroRegistro);
        System.out.println("Tu cuenta " + cuenta.getClabe() + " retiró -" + monto + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
    }
}