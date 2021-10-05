/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;
import model.*;
import view.*;
import view.pantalla.PanelPantalla;

/**
 * Esta sera la clase que haga referencia y uso de los diferentes entes dentro
 * de la aplicacion, esta implemente ActionListener y KeyListener para escuchar
 * tanto al teclado como al mousse y asignar asi acciones caracteristicas a los
 * diferentes componentes
 *
 * @author jefer
 */
public class Controlador implements ActionListener, KeyListener {

    private boolean tarjetain = false;
    int numeroDigitado, operacion, cantidad;
    String clave = "";
    String claveNueva = "";
    Usuario usuario1;

    private VentanaPrincipal ventanaPrincipal;
    private PanelPrincipal panelPrincipal;
    private final PanelPantalla panelPantallaPrincipal = new PanelPantalla("", "",
            "", " ", "", "", "", "", "Inserte tarjeta", "", "");
    private final PanelPantalla panelPantallaRetiro = new PanelPantalla("$10000", "$20000",
            "$50000", "$100000", "$200000", "$300000", "$500000", "Otro valor", "Seleccione", "un monto", "");
    private final PanelPantalla panelPantallaAcciones = new PanelPantalla("", "Retirar",
            "Cambiar Clave", " ", "", "Depositar", "Consultar", "", "", "", "");
    private final PanelPantalla panelPantallaCambio = new PanelPantalla("", "",
            "", " ", "", "", "", "", "Ingrese", "clave nueva", "");
    private PanelPantalla panelPantallaClave = new PanelPantalla("", "",
            "", " ", "", "", "", "", "Digite su clave", "", "");
    private PanelPantalla panelPantallaValor = new PanelPantalla("", "",
            "Valor min", "Valor max", "", "", "$10000", "$600000", "Ingrese el valor", "", "");
    private PanelPantalla panelSalida;
    private JPanel pantallaActual;
    private UsuarioDTO usuarioDTO;

    /**
     * Constructor que inicializa los componentes que se van a usar a lo largo
     * de la aplicacion y les asigana los respectivos oyentes
     */
    public Controlador() {
        inicializarComponentes();
        asignarOyentes();
    }

    private void inicializarComponentes() {

        ventanaPrincipal = new VentanaPrincipal();
        panelPrincipal = new PanelPrincipal("fondo.jpg");
        usuarioDTO = new UsuarioDTO();
        usuario1 = usuarioDTO.getUsuarioDAO().obtenerUsuario("20191020088");
        panelSalida = new PanelPantalla("Saldo: ", "",
                "", " ", "", "", "", "", "Transaccion exitosa", ("$" + usuario1.getSaldo()), "Retire su tarjeta");

        panelPantallaPrincipal.setBounds(97, 121, 233, 192);
        panelPrincipal.add(panelPantallaPrincipal);
        pantallaActual = panelPantallaPrincipal;

        panelPrincipal.setBounds(0, 0, VentanaPrincipal.ANCHO - 5, VentanaPrincipal.ALTO - 5);
        ventanaPrincipal.addPaneles(panelPrincipal);

        ventanaPrincipal.setVisible(true);

    }

    private void asignarOyentes() {

        ventanaPrincipal.addKeyListener(this);
        panelPrincipal.addKeyListener(this);

        asignarOyentesBotones(panelPrincipal.getBotonTarjeta());
        asignarOyentesBotones(panelPrincipal.getBotonesAccionesTeclado());
        for (JButton[] botones : panelPrincipal.getBotonesAccionesPantalla()) {
            asignarOyentesBotones(botones);
        }
        for (JButton[] botones : panelPrincipal.getBotonesNumeros()) {
            asignarOyentesBotones(botones);
        }
    }

    private void asignarOyentesBotones(JButton... botones) {
        for (JButton boton : botones) {

            boton.addKeyListener(this);
            boton.addActionListener(this);

        }
    }

    private void asignarOyentesCampos(JTextField campo) {
        campo.addKeyListener(this);
        campo.addActionListener(this);
    }

    private void cambiarPaneles(PanelPantalla panel) {

        panelPrincipal.remove(pantallaActual);
        panel.setBounds(97, 121, 233, 192);
        panelPrincipal.add(panel);
        pantallaActual = panel;
        panelPrincipal.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        salida:
        if (e.getSource() instanceof JButton) {

            ReproducirSonido("beep.wav");

            //boton tarjeta
            if (e.getSource() == panelPrincipal.getBotonTarjeta()) {
                IOTarjeta();
            }

            //boton cancelar
            if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[0]) {
                if (tarjetain) {
                    IOTarjeta();
                } else {
                    System.exit(0);
                }
            }

            //boton cambiar clave
            if (pantallaActual.equals(panelPantallaAcciones)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[0][2]) {
                    panelPantallaValor = new PanelPantalla("", "",
                            "", " ", "", "", "", "", "Digite clave nueva", "", "");
                    cambiarPaneles(panelPantallaValor);
                    operacion = 2;
                    break salida;
                }
            }

            //boton retirar
            if (pantallaActual.equals(panelPantallaAcciones)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[0][1]) {
                    operacion = 1;
                    cambiarPaneles(panelPantallaRetiro);
                    break salida;
                }
            }

            //boton 10k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[0][0]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 10000;
                    break salida;
                }
            }

            //boton 20k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[0][1]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 20000;
                    break salida;
                }
            }

            //boton 50k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[0][2]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 50000;
                    break salida;
                }
            }

            //boton 100k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[0][3]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 100000;
                    break salida;
                }
            }

            //boton 200k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[1][0]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 200000;
                    break salida;
                }
            }

            //boton 300k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[1][1]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 300000;
                    break salida;
                }
            }

            //boton 500k
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[1][2]) {
                    cambiarPaneles(panelPantallaClave);
                    cantidad = 500000;
                    break salida;
                }
            }

            //boton otrovalor
            if (pantallaActual.equals(panelPantallaRetiro)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[1][3]) {
                    cambiarPaneles(panelPantallaValor);
                    break salida;
                }
            }

            //consulta
            if (pantallaActual.equals(panelPantallaAcciones)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[1][2]) {

                    cambiarPaneles(panelPantallaClave);
                    operacion = 4;
                    break salida;
                }
            }

            //boton deposito
            if (pantallaActual.equals(panelPantallaAcciones)) {
                if (e.getSource() == panelPrincipal.getBotonesAccionesPantalla()[1][1]) {
                    operacion = 3;
                    cambiarPaneles(panelPantallaValor);
                    break salida;
                }
            }

            //panel valor
            if (pantallaActual.equals(panelPantallaValor)) {
                ingresarValor(e, operacion);
                if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[1] && operacion != 2) {
                    clave = clave.substring(0, clave.length() - 1);
                    if (clave.length() == 0) {
                        clave = "0";
                    }
                    cantidad = Integer.parseInt(clave);
                    panelPantallaValor = new PanelPantalla("", "", "Valor min",
                            "Valor max", "", "", "$10000", "$600000", "Ingrese el valor", ("" + cantidad), "");
                    cambiarPaneles(panelPantallaValor);
                    break salida;
                } else if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[1] && operacion == 2) {
                    clave = clave.substring(0, clave.length() - 1);
                    if (clave.length() == 0) {
                        clave = "";
                    }
                    String password = "";
                    for (int i = 0; i < clave.length(); i++) {
                        password += "*";
                    }
                    panelPantallaValor = new PanelPantalla("", "",
                            "", " ", "", "", "", "", "Digite clave nueva", password, "");
                    cambiarPaneles(panelPantallaValor);
                    break salida;

                }

                if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[2] && operacion == 1) {
                    if (cantidad > 0 && cantidad % 10000 == 0 && cantidad < usuario1.getSaldo()) {
                        panelPantallaClave = new PanelPantalla("", "", "", " ", "", "", "", "", "Digite su clave", "", "");
                        cambiarPaneles(panelPantallaClave);
                    } else {
                        panelSalida = new PanelPantalla("", "",
                                "", " ", "", "", "", "", "Cantidad Incorrecta", "", "Retire su tarjeta");
                        cantidad = 0;
                        cambiarPaneles(panelSalida);
                    }
                    clave = "";
                    break salida;
                } else if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[2] && operacion == 3) {
                    if (cantidad > 0 && cantidad % 10000 == 0 && cantidad < 600000) {
                        panelPantallaClave = new PanelPantalla("", "", "", " ", "", "", "", "", "Digite su clave", "", "");
                        cambiarPaneles(panelPantallaClave);
                    } else {
                        panelSalida = new PanelPantalla("", "",
                                "", " ", "", "", "", "", "Cantidad Incorrecta", "", "Retire su tarjeta");
                        cantidad = 0;
                        cambiarPaneles(panelSalida);
                    }
                    clave = "";
                    break salida;
                } else if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[2] && operacion == 2) {
                    cambiarPaneles(panelPantallaClave);
                    claveNueva = clave;
                    clave = "";
                    break salida;
                }
            }

            //panel clave
            if (pantallaActual.equals(panelPantallaClave)) {
                ingresarClave(e, operacion);
                if (e.getSource() == panelPrincipal.getBotonesAccionesTeclado()[1]) {
                    if (clave.length() > 0) {
                        clave = clave.substring(0, clave.length() - 1);
                    } else if (clave.length() == 0) {
                        clave = "";
                    }
                    String password = "";
                    for (int i = 0; i < clave.length(); i++) {
                        password += "*";
                    }
                    panelPantallaClave = new PanelPantalla("", "",
                            "", " ", "", "", "", "", "Digite su clave", password, "");
                    cambiarPaneles(panelPantallaClave);
                    break salida;
                }
            }

        }

//        System.out.println(e.getActionCommand());
    }

    public void realizarOperacion(int op) {
        switch (op) {
            case 1:
                retirar();
                break;
            case 2:
                cambiarClave();
                break;
            case 3:
                depositar();
                break;
            case 4:
                consultar();
                break;
            default:
                break;
        }

        clave = "";
    }

    public void retirar() {
        usuario1.retirar(cantidad);
        usuarioDTO.getUsuarioDAO().modificarUsuario(usuario1);
        panelSalida = new PanelPantalla("Saldo: ", "",
                "", " ", "", "", "", "", "Transaccion exitosa", ("$" + usuario1.getSaldo()), "Retire su tarjeta");

        cambiarPaneles(panelSalida);
    }

    public void consultar() {
        panelSalida = new PanelPantalla("Saldo: ", "",
                "", " ", "", "", "", "", "Transaccion exitosa", ("$" + usuario1.getSaldo()), "Retire su tarjeta");
        cambiarPaneles(panelSalida);

    }

    public void depositar() {
        usuario1.depositar(cantidad);
        usuarioDTO.getUsuarioDAO().modificarUsuario(usuario1);
        panelSalida = new PanelPantalla("Saldo: ", "",
                "", " ", "", "", "", "", "Transaccion exitosa", ("$" + usuario1.getSaldo()), "Retire su tarjeta");
        cambiarPaneles(panelSalida);
    }

    public void cambiarClave() {
        usuario1.cambiarClave(claveNueva);
        usuarioDTO.getUsuarioDAO().modificarUsuario(usuario1);
        panelSalida = new PanelPantalla("", "",
                "", " ", "", "", "", "", "Transaccion exitosa", "", "Retire su tarjeta");
        cambiarPaneles(panelSalida);
    }

    public boolean ingresarClave(ActionEvent e, int op) {
        if (digitoNumero(e)) {

            clave += String.valueOf(numeroDigitado);

            String password = "";
            for (int i = 0; i < clave.length(); i++) {
                password += "*";
            }
            panelPantallaClave = new PanelPantalla("", "",
                    "", " ", "", "", "", "", "Digite su clave", password, "");
            cambiarPaneles(panelPantallaClave);

            if (clave.equals(usuario1.getClave())) {
                System.out.println("clave = " + clave);
                realizarOperacion(op);
                return true;
            } else {
                if (clave.length() > 3) {
                    cambiarPaneles(new PanelPantalla("", "",
                            "", " ", "", "", "", "", "Clave Incorrecta", "", "Retire su tarjeta"));
                    clave = "";
                    return false;
                }
            }

        }
        return false;
    }

    public boolean ingresarValor(ActionEvent e, int op) {
        if (digitoNumero(e) && operacion != 2) {
            if (clave.length() < 7) {
                clave += String.valueOf(numeroDigitado);
                if (clave.length() == 0) {
                    clave = "0";
                }
                cantidad = Integer.parseInt(clave);
            }

            panelPantallaValor = new PanelPantalla("", "", "Valor min", "Valor max", "", "", "$10000", "$600000", "Ingrese el valor", ("" + cantidad), "");
            cambiarPaneles(panelPantallaValor);

        } else if (digitoNumero(e)) {
            if (clave.length() < 4) {
                clave += String.valueOf(numeroDigitado);
                if (clave.length() == 0) {
                    clave = "0";
                }
                cantidad = Integer.parseInt(clave);
            }

            String password = "";
            for (int i = 0; i < clave.length(); i++) {
                password += "*";
            }
            panelPantallaValor = new PanelPantalla("", "",
                    "", " ", "", "", "", "", "Digite nueva clave", password, "");
            cambiarPaneles(panelPantallaValor);
        }
        return false;
    }

    public boolean digitoNumero(ActionEvent e) {

        for (int i = 0; i < panelPrincipal.getBotonesNumeros().length; i++) {
            for (int j = 0; j < panelPrincipal.getBotonesNumeros()[i].length - 1; j++) {
                if (e.getSource() == panelPrincipal.getBotonesNumeros()[i][j]) {
                    numeroDigitado = j * 3 + i + 1;
                    return true;
                }
            }
        }

        //digita numero 0
        if (e.getSource() == panelPrincipal.getBotonesNumeros()[1][3]) {
            numeroDigitado = 0;
            return true;
        }

        return false;
    }

    public void IOTarjeta() {
        if (tarjetain) {
            cambiarPaneles(panelPantallaPrincipal);
            panelPrincipal.setImagenFondo("fondo.jpg");
            tarjetain = false;
        } else {
            cambiarPaneles(panelPantallaAcciones);
            panelPrincipal.setImagenFondo("cajerocontarjeta.png");
            tarjetain = true;
        }
        panelPantallaClave = new PanelPantalla("", "", "", " ", "", "", "", "", "Digite su clave", "", "");
        panelPantallaValor = new PanelPantalla("", "", "Valor min", "Valor max", "", "", "$10000", "$600000", "Ingrese el valor", "", "");
    }

    public void ReproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Controlador.class.getResource("/view/recursos/" + nombreSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            clip.stop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido: " + ex.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
            System.exit(0);
        }
        if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
            if (e.getKeyCode() == KeyEvent.VK_0) {
                panelPrincipal.getBotonesNumeros()[1][3].doClick();
            } else {
                for (int i = 0; i < panelPrincipal.getBotonesNumeros().length; i++) {
                    for (int j = 0; j < panelPrincipal.getBotonesNumeros()[i].length; j++) {
                        if (j * 3 + i + 1 == Integer.parseInt(String.valueOf(e.getKeyChar()))) {
                            panelPrincipal.getBotonesNumeros()[i][j].doClick();
                            break;
                        }
                    }
                }
            }
//            System.out.println(e.getKeyChar());

        }

    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        //no pasa nada
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
        //no pasa nada
    }

}
