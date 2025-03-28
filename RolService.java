package org.example.service;

import org.example.dao.RolDAO;
import org.example.model.Rol;

import java.util.List;

public class RolService {

    private final RolDAO rolDAO = new RolDAO();

    public void crearRol(Rol rol) {
        try {
            rolDAO.create(rol);
            System.out.println("Rol creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el rol: " + e.getMessage());
        }
    }

    public Rol obtenerRolPorId(Long id) {
        try {
            return rolDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener el rol: " + e.getMessage());
            return null;
        }
    }

    public List<Rol> obtenerTodosLosRoles() {
        try {
            return rolDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los roles: " + e.getMessage());
            return null;
        }
    }

    public void actualizarRol(Rol rol) {
        try {
            rolDAO.update(rol);
            System.out.println("Rol actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el rol: " + e.getMessage());
        }
    }

    public void eliminarRol(Long id) {
        try {
            rolDAO.delete(id);
            System.out.println("Rol eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el rol: " + e.getMessage());
        }
    }
}