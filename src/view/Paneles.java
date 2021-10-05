/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author jefer
 */
public class Paneles extends JPanel {

    public Image imagen;
    private static final int ANCHO = 576, ALTO = 669;
    private static final Border BORDE = BorderFactory.createSoftBevelBorder(1, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE);

    protected static final Color COLOR = new Color(51, 51, 55);
    protected static final Color COLOR1 = new Color(1, 153, 194);

    /**
     *
     * @param nombreImagen
     */
    public Paneles(String nombreImagen) {
        setImagenFondo(nombreImagen);
        definirPropiedades();
        inicializarComponentes();
    }

    private void definirPropiedades() {
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        this.setLayout(null);
        this.requestFocus();
//        this.getRootPane().setBorder(BORDE);
    }

    private void inicializarComponentes() {
        inicializarPaneles();
        inicializarBotones();
        inicializarLabels();
    }

    protected void inicializarPaneles() {
    }

    protected void inicializarBotones() {
    }

    protected void inicializarLabels() {
    }

    protected void personalizarBotonesTransparentes(JButton... botones) {
        for (JButton boton : botones) {
            boton.setOpaque(false);
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            anadirCaracteristicasBotones(boton);
        }
    }

    protected void anadirCaracteristicasBotones(JButton componente) {

        componente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        componente.setForeground(COLOR1);
        componente.setBackground(COLOR);
        componente.setBorder(BORDE);

    }

    protected void anadirCaracteristicasEtiquetas(JLabel etiqueta) {

        etiqueta.setBackground(COLOR1);
        etiqueta.setForeground(Color.BLACK);
//        etiqueta.setBorder(BORDE);
        etiqueta.requestFocus();
        etiqueta.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Object o = e.getSource();
                if (o instanceof JTextField) {
                    JTextField field_in_num_monedas = (JTextField) o;
                    field_in_num_monedas.setSelectionStart(0);
                    field_in_num_monedas.setSelectionEnd(field_in_num_monedas.getText().length());
                    field_in_num_monedas.setBackground(Color.BLACK);
                    field_in_num_monedas.setForeground(COLOR1);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                Object o = e.getSource();
                if (o instanceof JTextField) {
                    JTextField field_in_num_monedas = (JTextField) o;
                    field_in_num_monedas.setBackground(COLOR1);
                    field_in_num_monedas.setForeground(Color.BLACK);
                }
            }
        });
    }

    protected void addPaneles(JPanel... paneles) {
        for (JPanel panel : paneles) {
            this.add(panel);
        }
    }

    private void setImagenFondo(String nombreImagen) {
        try {
            imagen = ImageIO.read(VentanaPrincipal.class.getResource("/view/recursos/" + nombreImagen));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        setOpaque(false);
        super.paint(g);
    }

}

////////////////////////////////////////////////////////
////////////Cambia el fondo//////////////////
//////////////////////////////////////////////////////
class ImagenFondo extends JPanel {

    public Image imagen;

    public ImagenFondo(String nombreImagen) {
        try {
            imagen = ImageIO.read(VentanaPrincipal.class.getResource("/recursos/" + nombreImagen));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        setOpaque(false);
        super.paint(g);
    }

}
///////////////////////////////////////////////////////////////////////
////////////////////////////////////////
