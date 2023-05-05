package org.example.View;

import org.example.Controller.ClienteController;
import org.example.Controller.ConexionController;
import org.example.Controller.CuentaController;
import org.example.Service.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
public class VentanaEliminar extends JFrame implements ActionListener {
    private final JTextField txtIdCliente,txtCLABE;
    private final JButton btnEliminarCliente,btnEliminarCuenta;
    public VentanaEliminar() {
        super("Bank Application");
        // Window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new VentanaPrincipal().setVisible(true);
            }
        });

        // Panel para eliminar cliente
        JPanel panelCliente = new JPanel(new GridBagLayout());
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        panelCliente.add(new JLabel("idCliente:"), c);
        txtIdCliente = new JTextField(15);
        panelCliente.add(txtIdCliente, c);

        btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.addActionListener(this);
        panelCliente.add(btnEliminarCliente, c);

        // Panel para eliminar Cuenta
        JPanel panelCuenta = new JPanel(new GridBagLayout());
        panelCuenta.setBorder(BorderFactory.createTitledBorder("Datos de la cuenta"));

        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(5, 5, 5, 5);

        panelCuenta.add(new JLabel("CLABE:"), d);
        txtCLABE = new JTextField(15);
        panelCuenta.add(txtCLABE, d);

        btnEliminarCuenta = new JButton("Eliminar Cuenta");
        btnEliminarCuenta.addActionListener(this);
        panelCuenta.add(btnEliminarCuenta, d);

        // Agregamos los paneles al contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(panelCliente);
        contentPane.add(panelCuenta);
        pack();
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Query query = new Query(new ConexionController());
        if (e.getSource() == btnEliminarCliente) {
            try {
                ClienteController clienteController = new ClienteController();
                clienteController.eliminarCliente(txtIdCliente.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnEliminarCuenta) {
            try {
                CuentaController cuentaController = new CuentaController();
                cuentaController.eliminarCuenta(query.obtenerCuentaPorClabe(txtCLABE.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}