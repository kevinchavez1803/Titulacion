package org.example.service;

import org.example.dao.EntrenadorDAO;
import org.example.model.entrenadores;

import java.util.List;

public class EntrenadorService {

    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    public void crearEntrenador(entrenadores entrenador) {
        try {
            entrenadorDAO.create(entrenador);
            System.out.println("Entrenador creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el entrenador: " + e.getMessage());
        }
    }

    public entrenadores obtenerEntrenadorPorId(Long id) {
        try {
            return entrenadorDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener el entrenador: " + e.getMessage());
            return null;
        }
    }

    public List<entrenadores> obtenerTodosLosEntrenadores() {
        try {
            return entrenadorDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los entrenadores: " + e.getMessage());
            return null;
        }
    }

    public void actualizarEntrenador(entrenadores entrenador) {
        try {
            entrenadorDAO.update(entrenador);
            System.out.println("Entrenador actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el entrenador: " + e.getMessage());
        }
    }

    public void eliminarEntrenador(Long id) {
        try {
            entrenadorDAO.delete(id);
            System.out.println("Entrenador eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el entrenador: " + e.getMessage());
        }
    }
}