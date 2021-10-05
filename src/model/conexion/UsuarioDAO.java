/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conexion;

import java.sql.*;
import model.Usuario;

/**
 *
 * @author jefer
 */
public class UsuarioDAO {

    private final ConexionDB conexionDB;

    /**
     *
     * @param conexion
     */
    public UsuarioDAO(ConexionDB conexion) {
        this.conexionDB = conexion;
    }

    /**
     * Busca el usuario por medio de la llave primaria
     *
     * @param id
     * @return el usuario con el alias si existe, si no, retorna null
     */
    public Usuario obtenerUsuario(String id) {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM Usuario where id = ?";
        Usuario usuarioEncontrado = null;
        try {
            this.conexionDB.EstableciendoConexion();
            ps = this.conexionDB.getConexion().prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString(2);
                int saldo = Integer.parseInt(rs.getString(3));
                String clave = rs.getString(4);

                usuarioEncontrado = new Usuario(id, nombre, saldo, clave);
            }
            this.conexionDB.cerrandoConexion();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Error al obtener usuario...\nError: " + e.getMessage());
            usuarioEncontrado = null;
        }
        return usuarioEncontrado;

    }

    /**
     * Modifica el usuario en la base de datos mediante su llave primaria "id",
     * si existe dicho usuario y es modificado retorna true, si no, retorna
     * false
     *
     * @param usuario
     * @return true si existe dicho cliente y es modificado, si no, retorna
     * false
     */
    public boolean modificarUsuario(Usuario usuario) {
        PreparedStatement ps;
        String sql = "UPDATE Usuario SET saldo = ?, clave = ? where id = ?";
        try {
            this.conexionDB.EstableciendoConexion();
            ps = this.conexionDB.getConexion().prepareStatement(sql);
            ps.setString(1, Integer.toString(usuario.getSaldo()));
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getId());

            int contador = ps.executeUpdate();
            if (contador > 0) {
                this.conexionDB.cerrandoConexion();
                return true;
            } else {
                this.conexionDB.cerrandoConexion();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error en la modificacion...\nError: " + e.getMessage());
            return false;
        }
    }

}
