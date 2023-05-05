package org.example.View;

import org.example.Controller.ClienteController;
import org.example.Controller.ConexionController;
import org.example.Controller.CuentaController;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;
import org.example.Service.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
public class VentanaCrear extends JFrame implements ActionListener {
    private final JButton btnCrearCuenta,btnCrearCliente;
    private final JTextField txtNombre,txtTelefono,txtIdClienteCuenta,txtSaldo,txtCorreo;
    public VentanaCrear() {
        super("Bank Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        // Window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new VentanaPrincipal().setVisible(true);
            }
        });
        // Panel para datos del cliente
        JPanel panelCliente = new JPanel(new GridBagLayout());
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        panelCliente.add(new JLabel("Nombre:"), c);
        txtNombre = new JTextField(15);
        c.gridx = 1;
        c.gridy = 0;
        panelCliente.add(txtNombre, c);

        c.gridx = 0;
        c.gridy = 1;
        panelCliente.add(new JLabel("Teléfono:"), c);
        txtTelefono = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        panelCliente.add(txtTelefono, c);

        c.gridx = 0;
        c.gridy = 2;
        panelCliente.add(new JLabel("Correo electrónico:"), c);
        txtCorreo = new JTextField(15);
        c.gridx = 1;
        c.gridy = 2;
        panelCliente.add(txtCorreo, c);

        btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        panelCliente.add(btnCrearCliente, c);

        // Panel para datos de la cuenta
        JPanel panelCuenta = new JPanel(new GridBagLayout());
        panelCuenta.setBorder(BorderFactory.createTitledBorder("Datos de la cuenta"));
        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(5, 5, 5, 5);

        d.gridx = 0;
        d.gridy = 0;
        panelCuenta.add(new JLabel("ID del cliente:"), d);
        txtIdClienteCuenta = new JTextField(15);
        d.gridx = 1;
        d.gridy = 0;
        panelCuenta.add(txtIdClienteCuenta, d);

        d.gridx = 0;
        d.gridy = 1;
        panelCuenta.add(new JLabel("Saldo:"), d);
        txtSaldo = new JTextField(15);
        d.gridx = 1;
        d.gridy = 1;
        panelCuenta.add(txtSaldo, d);

        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.addActionListener(this);
        d.gridx = 1;
        d.gridy = 2;
        panelCuenta.add(btnCrearCuenta, d);

        // Agregamos los paneles al contenedor principal
        JPanel contentPane = new JPanel(new GridLayout(2, 1, 0, 0));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(panelCliente);
        contentPane.add(panelCuenta);
        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Query query = new Query(new ConexionController());
        if (e.getSource() == btnCrearCliente) {
            try {
                ClienteController clienteController = new ClienteController();
                Cliente cliente = new Cliente(query.generarNuevoIdCliente(), txtNombre.getText(),txtTelefono.getText(),txtCorreo.getText());
                clienteController.crearCliente(cliente);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnCrearCuenta) {
            try {
                CuentaController cuentaController = new CuentaController();
                double saldo = Double.parseDouble(txtSaldo.getText());
                String idCuenta = query.generarNuevoIdCuenta();
                Cuenta cuenta = new Cuenta(txtIdClienteCuenta.getText(), idCuenta,saldo, query.generarCLABE(txtIdClienteCuenta.getText(),idCuenta));
                cuentaController.crearCuenta(cuenta);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}