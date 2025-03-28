package org.example.service;

import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void crearUsuario(Usuario usuario) {
        try {
            usuarioDAO.create(usuario);
            System.out.println("Usuario creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        try {
            return usuarioDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
            return null;
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        try {
            return usuarioDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
            return null;
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        try {
            usuarioDAO.update(usuario);
            System.out.println("Usuario actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(Long id) {
        try {
            usuarioDAO.delete(id);
            System.out.println("Usuario eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}