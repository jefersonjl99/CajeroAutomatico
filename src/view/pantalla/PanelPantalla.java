/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.pantalla;

import java.awt.*;
import javax.swing.*;
import view.Paneles;

/**
 *
 * @author jefer
 */
public class PanelPantalla extends Paneles {

    private JLabel[][] labelsFunciones;
    private JLabel[] labelsSalida;
    private boolean activa;

//    public PanelPantalla(String nombreImagen) {
//        super(nombreImagen);
//    }
    public PanelPantalla(String label1, String label2,
            String label3, String label4, String label5, String label6,
            String label7, String label8, String label9, String label10, String label11) {
        super("fondopantallainicio.jpg");
        inicializarLabels(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11);
    }

    protected final void inicializarLabels(String label1, String label2,
            String label3, String label4, String label5, String label6,
            String label7, String label8, String label9, String label10, String label11) {

        int labels_ancho = 100, labels_alto = 15;
        labelsSalida = new JLabel[3];

        for (int i = 0; i < labelsSalida.length; i++) {
            labelsSalida[i] = new JLabel();
            labelsSalida[i].setBounds(this.getWidth() / 2 + 55, 83 + (i * (labels_alto)), labels_ancho + 30, labels_alto);
            anadirCaracteristicasEtiquetas(labelsSalida[i]);
            labelsSalida[i].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(labelsSalida[i]);
        }
        labelsSalida[2].setBounds(this.getWidth() / 2 + 55, 83 + (6 * (labels_alto)), labels_ancho + 30, labels_alto);
        labelsSalida[2].setForeground(Color.RED);

        labelsFunciones = new JLabel[2][4];
        labels_ancho = 80;
        labels_alto = 23;

        for (int i = 0; i < labelsFunciones.length; i++) {
            for (int j = 0; j < labelsFunciones[i].length; j++) {

                labelsFunciones[i][j] = new JLabel();
                labelsFunciones[i][j].setBounds(2 + (i * (labels_ancho + 69)) + i, 94 + (j * (labels_alto + 2)), labels_ancho, labels_alto);
                anadirCaracteristicasEtiquetas(labelsFunciones[i][j]);
                if (i == 1) {
                    labelsFunciones[i][j].setHorizontalAlignment(SwingConstants.RIGHT);
                }
                this.add(labelsFunciones[i][j]);
            }
        }
        labelsSalida[0].setText(label9);
        labelsSalida[1].setText(label10);
        labelsSalida[2].setText(label11);

        labelsFunciones[0][0].setText(label1);
        labelsFunciones[0][1].setText(label2);
        labelsFunciones[0][2].setText(label3);
        labelsFunciones[0][3].setText(label4);
        labelsFunciones[1][0].setText(label5);
        labelsFunciones[1][1].setText(label6);
        labelsFunciones[1][2].setText(label7);
        labelsFunciones[1][3].setText(label8);

    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
