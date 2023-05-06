package org.example.View;

import org.example.Controller.HaskellController;
import org.example.Utilities.KeyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentanaConexion extends JFrame implements ActionListener {
    private final JButton btnConexion;
    private final JTextField txtClave;
    public VentanaConexion() {
        super("Bank Application - Conexión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setVisible(true);

        // Panel para clave de conexión
        JPanel panelClaveConexion = new JPanel(new BorderLayout());
        panelClaveConexion.setBorder(BorderFactory.createTitledBorder("Clave de conexión"));

        JLabel lblClave = new JLabel("Clave:");
        txtClave = new JTextField(3);
        panelClaveConexion.add(lblClave, BorderLayout.WEST);
        panelClaveConexion.add(txtClave, BorderLayout.CENTER);

        btnConexion = new JButton("Aceptar");
        btnConexion.addActionListener(this);
        panelClaveConexion.add(btnConexion, BorderLayout.SOUTH);

        // Agregamos los paneles al contenedor principal
        JPanel contentPane = new JPanel(new GridLayout(1, 2, 5, 5));
        contentPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        contentPane.add(panelClaveConexion);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
    }
    public void ventanaPrincipalFrame(JFrame nuevaVentana) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
        nuevaVentana.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConexion) {
            HaskellController haskellController = new HaskellController();
            if (haskellController.validarDatosClave(txtClave.getText())){
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                KeyManager.setTempKey(txtClave.getText());
                ventanaPrincipalFrame(ventanaPrincipal);
            } else {
                JOptionPane.showMessageDialog(null, "Clave con datos invalidos");
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        VentanaConexion ventanaConexion = new VentanaConexion();
        ventanaConexion.setVisible(true);
    }
}