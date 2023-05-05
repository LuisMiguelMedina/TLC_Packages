package org.example.View;

import org.example.Controller.ConexionController;
import org.example.Controller.TransaccionController;
import org.example.Model.Cuenta;
import org.example.Model.Deposito;
import org.example.Model.Retiro;
import org.example.Service.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
public class VentanaTransacciones extends JFrame implements ActionListener{
    private final JTextField txtMonto,txtClabe,txtClabeDestino,txtClabeRetiro,txtMontoRetiro;
    private final JButton btnDepositar,btnRetiro;
    public VentanaTransacciones() {
        super("Bank Application - Transacciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        // Window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new VentanaPrincipal().setVisible(true);
            }
        });

        // Panel para deposito
        JPanel panelDeposito = new JPanel(new GridBagLayout());
        panelDeposito.setBorder(BorderFactory.createTitledBorder("Depósito"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        JLabel lblClabe = new JLabel("Cuenta CLABE:");
        lblClabe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelDeposito.add(lblClabe, c);
        txtClabe = new JTextField(20);
        txtClabe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridy = 1;
        panelDeposito.add(txtClabe, c);

        JLabel lblClabeDestino = new JLabel("Cuenta CLABE destino:");
        lblClabeDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridy = 2;
        panelDeposito.add(lblClabeDestino, c);
        txtClabeDestino = new JTextField(20);
        txtClabeDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridy = 3;
        panelDeposito.add(txtClabeDestino, c);

        JLabel lblMonto = new JLabel("Monto a depositar:");
        lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridy = 4;
        panelDeposito.add(lblMonto, c);
        txtMonto = new JTextField(20);
        txtMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridy = 5;
        panelDeposito.add(txtMonto, c);

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(this);
        c.gridy = 6;
        panelDeposito.add(btnDepositar, c);

        // Panel para Retiro
        JPanel panelRetiro = new JPanel(new GridBagLayout());
        panelRetiro.setBorder(BorderFactory.createTitledBorder("Retiro"));
        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(5, 5, 5, 5);

        JLabel lblClabeRetiro = new JLabel("Cuenta CLABE:");
        lblClabeRetiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelRetiro.add(lblClabeRetiro, d);
        txtClabeRetiro = new JTextField(20);
        txtClabeRetiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        d.gridy = 1;
        panelRetiro.add(txtClabeRetiro, d);

        JLabel lblMontoRetiro = new JLabel("Monto a retirar:");
        lblMontoRetiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        d.gridy = 2;
        panelRetiro.add(lblMontoRetiro, d);
        txtMontoRetiro = new JTextField(20);
        txtMontoRetiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        d.gridy = 3;
        panelRetiro.add(txtMontoRetiro, d);

        btnRetiro = new JButton("Retirar");
        btnRetiro.addActionListener(this);
        d.gridy = 4;
        panelRetiro.add(btnRetiro,d);

        // Agregamos los paneles al contenedor principal
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(panelDeposito, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 10, 10, 10);
        contentPane.add(panelRetiro, gbc);

        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed (ActionEvent e){
        TransaccionController transaccionController = new TransaccionController();
        Query query = new Query(new ConexionController());
        if (e.getSource() == btnDepositar) {
            try {
                Cuenta cuentaD = query.obtenerCuentaPorClabe(txtClabe.getText());
                double montoD = Double.parseDouble(txtMonto.getText());
                Deposito deposito = new Deposito(montoD, txtClabeDestino.getText());
                transaccionController.hacerDeposito(deposito,cuentaD);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnRetiro) {
            try {
                Cuenta cuentaR = query.obtenerCuentaPorClabe(txtClabeRetiro.getText());
                double montoR = Double.parseDouble(txtMontoRetiro.getText());
                Retiro retiro = new Retiro(montoR);
                transaccionController.hacerRetiro(retiro,cuentaR);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}