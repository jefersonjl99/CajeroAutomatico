/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jefer
 */
public class Usuario {

    private final String id;
    private final String nombre;
    private int saldo;
    private String clave;

    public Usuario(String id, String nombre, int saldo, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.clave = clave;
    }

    public void retirar(int cantidad) {
        this.saldo -= cantidad;
    }

    public void depositar(int cantidad) {
        this.saldo += cantidad;
    }

    public void cambiarClave(String clave) {
        this.clave = clave;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getClave() {
        return clave;
    }

}
