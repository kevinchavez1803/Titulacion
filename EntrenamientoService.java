package org.example.service;

import org.example.dao.EntrenamientoDAO;
import org.example.model.entrenamientos;

import java.util.List;

public class EntrenamientoService {

    private final EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();

    public void crearEntrenamiento(entrenamientos entrenamiento) {
        try {
            entrenamientoDAO.create(entrenamiento);
            System.out.println("Entrenamiento creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el entrenamiento: " + e.getMessage());
        }
    }

    public entrenamientos obtenerEntrenamientoPorId(Long id) {
        try {
            return entrenamientoDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener el entrenamiento: " + e.getMessage());
            return null;
        }
    }

    public List<entrenamientos> obtenerTodosLosEntrenamientos() {
        try {
            return entrenamientoDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los entrenamientos: " + e.getMessage());
            return null;
        }
    }

    public void actualizarEntrenamiento(entrenamientos entrenamiento) {
        try {
            entrenamientoDAO.update(entrenamiento);
            System.out.println("Entrenamiento actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el entrenamiento: " + e.getMessage());
        }
    }

    public void eliminarEntrenamiento(Long id) {
        try {
            entrenamientoDAO.delete(id);
            System.out.println("Entrenamiento eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el entrenamiento: " + e.getMessage());
        }
    }
}