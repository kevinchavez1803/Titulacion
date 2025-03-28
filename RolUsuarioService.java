package org.example.service;

import org.example.dao.RolUsuarioDAO;
import org.example.model.rol_usuario;

import java.util.List;

public class RolUsuarioService {

    private final RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAO();

    public void crearRolUsuario(rol_usuario rolUsuario) {
        try {
            rolUsuarioDAO.create(rolUsuario);
            System.out.println("RolUsuario creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el RolUsuario: " + e.getMessage());
        }
    }

    public rol_usuario obtenerRolUsuarioPorId(Long id) {
        try {
            return rolUsuarioDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener el RolUsuario: " + e.getMessage());
            return null;
        }
    }

    public List<rol_usuario> obtenerTodosLosRolUsuarios() {
        try {
            return rolUsuarioDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los RolUsuarios: " + e.getMessage());
            return null;
        }
    }

    public void actualizarRolUsuario(rol_usuario rolUsuario) {
        try {
            rolUsuarioDAO.update(rolUsuario);
            System.out.println("RolUsuario actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el RolUsuario: " + e.getMessage());
        }
    }

    public void eliminarRolUsuario(Long id) {
        try {
            rolUsuarioDAO.delete(id);
            System.out.println("RolUsuario eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el RolUsuario: " + e.getMessage());
        }
    }
}