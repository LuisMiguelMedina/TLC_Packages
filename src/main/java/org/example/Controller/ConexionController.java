package org.example.Controller;

import org.example.DAO.Conexion;
import java.sql.*;
public class ConexionController {
    // Objeto de la clase Conexion para establecer la conexión a la base de datos
    private final Conexion conexion;

    // Constructor de la clase
    public ConexionController() {
        conexion = new Conexion();
    }

    // Método para obtener la conexión a la base de datos
    public Connection getConnection() {
        return conexion.getConnection();
    }

    // Método para cerrar la conexión a la base de datos
    public void cerrarConexion() {
        conexion.cerrar();
    }

    // Método para realizar una consulta a la base de datos
    public ResultSet consultar(String query) {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
        }
        return rs;
    }
    // Método para actualizar la base de datos (INSERT, UPDATE, DELETE)
    public int actualizar(String query) {
        Connection conn = getConnection();
        Statement stmt = null;
        int filasAfectadas = 0;
        try {
            stmt = conn.createStatement();
            filasAfectadas = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la actualización: " + ex.getMessage());
        }
        return filasAfectadas;
    }
}