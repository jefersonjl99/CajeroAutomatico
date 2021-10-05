/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.io.IOException;
import java.util.logging.*;
import javax.swing.*;
import javax.imageio.ImageIO;

/**
 *
 * @author jefer
 */
public class PanelPrincipal extends Paneles {

    private JButton botonTarjeta;
    private JButton[][] botonesAccionesPantalla;
    private JButton[][] botonesNumeros;
    private JButton[] botonesAccionesTeclado;

    /**
     *
     * @param nombreImagen
     */
    public PanelPrincipal(String nombreImagen) {
        super(nombreImagen);
    }

    @Override
    protected void inicializarPaneles() {
    }

    @Override
    protected void inicializarBotones() {

        botonTarjeta = new JButton();
        botonTarjeta.setBounds(432, 287, 75, 80);
        personalizarBotonesTransparentes(botonTarjeta);
        this.add(botonTarjeta);

        botonesAccionesPantalla = new JButton[2][4];
        int botones_ancho = 40, botones_alto = 23;
        for (int i = 0; i < botonesAccionesPantalla.length; i++) {
            for (int j = 0; j < botonesAccionesPantalla[i].length; j++) {
                botonesAccionesPantalla[i][j] = new JButton();
                botonesAccionesPantalla[i][j].setBounds(55 + (i * (botones_ancho + 237)) + i, 215 + (j * (botones_alto + 2)), botones_ancho, botones_alto);
                personalizarBotonesTransparentes(botonesAccionesPantalla[i][j]);
                this.add(botonesAccionesPantalla[i][j]);
            }
        }

        botonesNumeros = new JButton[3][4];
        botones_ancho = 31;
        botones_alto = 35;
        for (int i = 0; i < botonesNumeros.length; i++) {
            for (int j = 0; j < botonesNumeros[i].length; j++) {
                botonesNumeros[i][j] = new JButton();
                botonesNumeros[i][j].setBounds(175 - (j * 2) + (i * (botones_ancho + 5)) + i, 470 + (j * (botones_alto + j)) + (j * 3), botones_ancho + j, botones_alto + j * 3);
                personalizarBotonesTransparentes(botonesNumeros[i][j]);
                this.add(botonesNumeros[i][j]);
            }
        }

        botonesAccionesTeclado = new JButton[3];
        botones_ancho = 55;
        for (int i = 0; i < botonesAccionesTeclado.length; i++) {
            botonesAccionesTeclado[i] = new JButton();
            botonesAccionesTeclado[i].setBounds(290 + (i * 2), 470 + (i * (botones_alto + i)) + (i * 2), botones_ancho + i, botones_alto + i * 2);
            personalizarBotonesTransparentes(botonesAccionesTeclado[i]);
            this.add(botonesAccionesTeclado[i]);
        }
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

    public void setImagenFondo(String nombreImagen) {
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
