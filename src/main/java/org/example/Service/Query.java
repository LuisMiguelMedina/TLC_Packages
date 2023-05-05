package org.example.Service;

import org.example.Controller.ConexionController;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
public class Query {
    private final ConexionController conexionController;
    public Query(ConexionController conexionController) {
        this.conexionController = conexionController;
    }
    // Leer registros de la tabla "DatosClientes"
    public List<String[]> obtenerRegistrosClientes() throws SQLException {
        List<String[]> registros = new ArrayList<>();
        try (Connection conn = conexionController.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM DatosClientes")) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnas = metaData.getColumnCount();
            while (resultSet.next()) {
                String[] fila = new String[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = resultSet.getString(i + 1);
                }
                registros.add(fila);
            }
        }
        conexionController.cerrarConexion();
        return registros;
    }
    // Leer registros de la tabla "DatosCuentas"
    public List<String[]> obtenerRegistrosCuentas() throws SQLException {
        List<String[]> registrosCuentas = new ArrayList<>();
        try (Connection conn = conexionController.getConnection()) {
            String query = "SELECT * FROM DatosCuentas";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();
            while (rs.next()) {
                String[] registro = new String[numColumnas];
                for (int i = 1; i <= numColumnas; i++) {
                    registro[i - 1] = rs.getString(i);
                }
                registrosCuentas.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosCuentas;
    }

    // Leer registros de la tabla "DatosTransferencias"
    public List<String[]> obtenerRegistrosTransferencias() throws SQLException {
        List<String[]> registrosTransferencias = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionController.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM DatosTransferencias";
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            while (rs.next()) {
                String[] registro = new String[numColumns];
                for (int i = 0; i < numColumns; i++) {
                    registro[i] = rs.getString(i+1);
                }
                registrosTransferencias.add(registro);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return registrosTransferencias;
    }
    public String obtenerNombreClientePorId(String idCliente) throws SQLException {
        String query = "SELECT nombre FROM DatosClientes WHERE idCliente = ?";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        }
        return null;
    }
    public String obtenerSaldoCuentas(String cuentaClabe) throws SQLException {
        String saldo = null;
        String sql = "SELECT saldo FROM DatosCuentas WHERE clabe = ?";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuentaClabe);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getString("saldo");
                }
            }
        }
        return saldo;
    }
    public Cuenta obtenerCuentaPorClabe(String cuentaClabe) throws SQLException {
        Cuenta cuenta = null;
        String sql = "SELECT * FROM DatosCuentas WHERE clabe = ?";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuentaClabe);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cuenta = new Cuenta(rs.getString("idCliente"), rs.getString("idCuenta"),rs.getDouble("saldo"),rs.getString("clabe"));
                }
            }
        }
        return cuenta;
    }
    public ResultSet obtenerCuentaPorClabeArray(String cuentaClabe) throws SQLException {
        String sql = "SELECT * FROM DatosCuentas WHERE clabe = ?";
        Connection conn = conexionController.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, cuentaClabe);
        return pstmt.executeQuery();
    }
    public ResultSet obtenerCuentasPorIDArray(String idCliente) throws SQLException {
        String sql = "SELECT * FROM DatosCuentas WHERE idCliente = ?";
        Connection conn = conexionController.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, idCliente);
        return pstmt.executeQuery();
    }
    public ResultSet obtenerClientePorIdClienteArray(String idCliente) throws SQLException {
        String sql = "SELECT * FROM DatosClientes WHERE idCliente = ?";
        Connection conn = conexionController.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, idCliente);
        return pstmt.executeQuery();
    }
    public ResultSet obtenerClientePorPorNombreYCorreoArray(String nombre, String correo) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM DatosClientes WHERE nombre = ? AND correo = ?";
        Connection conn = conexionController.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nombre);
        pstmt.setString(2, correo);
        return pstmt.executeQuery();
    }
    // Crear Registros
    public void escribirRegistroCliente(String[] registro) throws SQLException {
        String query = "INSERT INTO DatosClientes (idCliente,nombre,telefono,correo) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = conexionController.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, registro[0]);
            statement.setString(2, registro[1]);
            statement.setString(3, registro[2]);
            statement.setString(4, registro[3]);
            statement.executeUpdate();
        }
    }
    public void escribirRegistroCuenta(String[] registro) throws SQLException {
        String query = "INSERT INTO DatosCuentas (idCliente,idCuenta,saldo,clabe) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = conexionController.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, registro[0]);
            statement.setString(2, registro[1]);
            statement.setDouble(3, Double.parseDouble(registro[2]));
            statement.setString(4, registro[3]);
            statement.executeUpdate();
        }
    }
    public void escribirRegistroTransaccion(String[] registro) throws SQLException {
        String query = "INSERT INTO DatosTransferencias (clabe,monto,fecha) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = conexionController.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, registro[0]);
            statement.setString(2, registro[1]);
            statement.setString(3, registro[2]);
            statement.executeUpdate();
        }
    }
    public String generarNuevoIdCliente() throws SQLException {
        String ultimoId = obtenerUltimoIdCliente();
        String numero = ultimoId.replaceAll("[^0-9]", "");
        int nuevoNumero = Integer.parseInt(numero) + 1;
        return String.format("C%03d", nuevoNumero);
    }
    public String obtenerUltimoIdCliente() throws SQLException {
        String query = "SELECT TOP 1 idCliente FROM DatosClientes ORDER BY idCliente DESC";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("idCliente");
                }
            }
        return "C001";
    }
    public String generarNuevoIdCuenta() throws SQLException {
        String ultimoId = obtenerUltimoIdCuenta();
        String numero = ultimoId.replaceAll("[^0-9]", "");
        int nuevoNumero = Integer.parseInt(numero) + 1;
        return String.format("CC%02d", nuevoNumero);
    }
    public String generarCLABE(String idCliente, String idCuenta) throws SQLException {
        String numeroCliente = idCliente.replaceAll("[^0-9]", "");
        String numeroCuenta = idCuenta.replaceAll("[^0-9]", "");
        return numeroCliente + numeroCuenta;
    }
    public String obtenerUltimoIdCuenta() throws SQLException {
        String query = "SELECT TOP 1 idCuenta FROM DatosCuentas ORDER BY idCuenta DESC";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("idCuenta");
            }
        }
        return "C01";
    }
    // Update
    public void actualizarCeldaSaldo(String clabe, String saldo) {
        try {
            Connection conn = conexionController.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE DatosCuentas SET saldo = '" + saldo + "' WHERE clabe = " + clabe;
            stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Delete
    public void eliminarRegistroCliente(String idCliente) throws SQLException {
        String query = "DELETE FROM DatosClientes WHERE idCliente = ?";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, idCliente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarRegistroCuenta(String clabe) {
        String query = "DELETE FROM DatosCuentas WHERE clabe = ?";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clabe);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarTransferenciasDeCuenta(String clabe) throws SQLException {
        String query = "DELETE FROM DatosTransferencias WHERE clabe = ?";
        try (Connection conn = conexionController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clabe);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}