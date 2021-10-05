/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.pantalla.PanelPantalla;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author jefer
 */
public class VentanaPrincipal extends JFrame {

    public static final int ANCHO = 576, ALTO = 669;
    private static final Border BORDE = BorderFactory.createSoftBevelBorder(1, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE);
    private PanelPantalla panelPantalla;
    private JButton botonTarjeta;
    private JButton[][] botonesAccionesPantalla;
    private JButton[][] botonesNumeros;
    private JButton[] botonesAccionesTeclado;

    /**
     *
     */
    public VentanaPrincipal() {
        definirPropiedades();
//        inicializarComponentes();

    }

    private void definirPropiedades() {
        this.setTitle("Cajero Automatico");
        this.setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/view/recursos/icono.png")).getImage());
        this.setUndecorated(true);
        this.setLayout(null);
        this.setSize(ANCHO, ALTO);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
//        this.setContentPane(imagen_fondo);
//        this.requestFocus();
        this.getRootPane().setBorder(BORDE);
        this.getContentPane().setLayout(null);
    }

    private void inicializarComponentes() {
    }

    /**
     *
     * @param paneles
     */
    public void addPaneles(JPanel... paneles) {
        for (JPanel panel : paneles) {
            this.add(panel);

        }
    }

    public JPanel getPanelPantalla() {
        return panelPantalla;
    }

    public JButton getBotonTarjeta() {
        return botonTarjeta;
    }

    public JButton[][] getBotonesAccionesPantalla() {
        return botonesAccionesPantalla;
    }

    public JButton[][] getBotonesNumeros() {
        return botonesNumeros;
    }

    public JButton[] getBotonesAccionesTeclado() {
        return botonesAccionesTeclado;
    }

}
