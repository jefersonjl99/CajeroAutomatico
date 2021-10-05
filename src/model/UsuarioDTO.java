/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.conexion.*;

/**
 *
 * @author jefer
 */
public class UsuarioDTO {

    private UsuarioDAO usuarioDAO;
    private final ConexionDB conexionDB;

    /**
     * Construtor con la conexion a la base de datos y su respectivo objeto de
     * acceoso a los datos (DAO)
     */
    public UsuarioDTO() {
        this.conexionDB = new ConexionDB();
        this.usuarioDAO = new UsuarioDAO(conexionDB);
    }

    /**
     *
     * @return clienteDao
     */
    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    /**
     *
     * @return conexionDB
     */
    public ConexionDB getConexionDB() {
        return conexionDB;
    }

}
