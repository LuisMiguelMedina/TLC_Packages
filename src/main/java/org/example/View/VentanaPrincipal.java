package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentanaPrincipal extends JFrame implements ActionListener {
    private final JButton btnCrear,btnBuscar;
    private final JButton btnEliminar,btnTransigir;
    public VentanaPrincipal() {
        super("Bank Application");
        setLocationRelativeTo(null);
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(BorderFactory.createTitledBorder("Operaciones Bancarias"));

        btnCrear = new JButton("Crear");
        btnCrear.addActionListener(this);
        panelBotones.add(btnCrear);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        panelBotones.add(btnBuscar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        panelBotones.add(btnEliminar);

        btnTransigir = new JButton("Transigir");
        btnTransigir.addActionListener(this);
        panelBotones.add(btnTransigir);

        // Agregamos los paneles al contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add(panelBotones);
    }
    public void ventanaCrear(JFrame nuevaVentana) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
        nuevaVentana.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCrear) {
            VentanaCrear ventanaCrear = new VentanaCrear();
            ventanaCrear(ventanaCrear);
        } else if (e.getSource() == btnBuscar){
            VentanaBuscar ventanaBuscar = new VentanaBuscar();
            ventanaCrear(ventanaBuscar);
        } else if (e.getSource() == btnEliminar){
            VentanaEliminar ventanaEliminar = new VentanaEliminar();
            ventanaCrear(ventanaEliminar);
        } else if (e.getSource() == btnTransigir){
            VentanaTransacciones ventanaTransacciones = new VentanaTransacciones();
            ventanaCrear(ventanaTransacciones);
        }
    }
}