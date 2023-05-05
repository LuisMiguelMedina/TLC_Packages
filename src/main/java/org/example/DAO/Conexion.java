package org.example.DAO;
import org.example.Controller.HaskellController;
import java.sql.*;
import java.io.*;
public class Conexion {
    private static final String ConnectionString = "jdbc:sqlserver://luismifmat.database.windows.net:1433;"
            + "database=LuisMFmat;"
            + "encrypt=true;";
    private static final String ConnectionString2 =
            "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
    private Connection connection;
    public Conexion() {
        connection = null;
    }
    public void abrir() {
        try {
            HaskellController controller = new HaskellController();
            String tokens = controller.conexionPorHaskell();
            String user = controller.usuario(tokens);
            String password = controller.contrasena(tokens);
            String connectionStringConCredenciales = ConnectionString + "user=" + user + ";" + "password=" + password + ";" + ConnectionString2;
            connection = DriverManager.getConnection(connectionStringConCredenciales);
        } catch (IOException ex) {
            System.out.println("Error al conectarse a Haskell: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al abrir la conexión: " + ex.getMessage());
        }
    }
    public void cerrar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
    public Connection getConnection() {
        if (connection == null) {
            abrir();
        } else {
            try {
                if (connection.isClosed()) {
                    abrir();
                }
            } catch (SQLException ex) {
                System.out.println("Error al comprobar el estado de la conexión: " + ex.getMessage());
            }
        }
        return connection;
    }
}