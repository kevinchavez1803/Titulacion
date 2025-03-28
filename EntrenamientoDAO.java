package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import org.example.model.entrenamientos;

import java.util.Collections; // Para retornos seguros
import java.util.List;

public class EntrenamientoDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");

    // Crear entrenamiento
    public void create(entrenamientos entrenamiento) {
        EntityManager em = emf.createEntityManager();
        try {
            // Crear entrenamiento en la base de datos
            em.getTransaction().begin();
            em.persist(entrenamiento);
            em.getTransaction().commit();
            System.out.println("[EntrenamientoDAO] Entrenamiento creado correctamente.");
        } catch (PersistenceException e) {
            // Manejo del error si persiste
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            handleException("Error al crear el entrenamiento", e);
        } finally {
            em.close();
        }
    }

    // Buscar por ID
    public entrenamientos findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("[EntrenamientoDAO] Buscando entrenamiento con ID: " + id);
            return em.find(entrenamientos.class, id);
        } catch (IllegalArgumentException e) {
            handleException("Error al buscar entrenamiento por ID: " + id, e);
            return null;
        } finally {
            em.close();
        }
    }

    // Listar todos los entrenamientos
    public List<entrenamientos> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // Consulta para obtener todos los entrenamientos
            System.out.println("[EntrenamientoDAO] Ejecutando consulta: SELECT e FROM entrenamientos e");
            List<entrenamientos> lista = em.createQuery("SELECT e FROM entrenamientos e", entrenamientos.class)
                    .getResultList();
            System.out.println("[EntrenamientoDAO] Total de registros obtenidos: " + lista.size());

            // Debugging para cada registro
            lista.forEach(entrenamiento ->
                    System.out.printf(" - [Registro] ID: %d | Nombre: %s | Descripción: %s | Duración: %d | Nivel: %s%n",
                            entrenamiento.getId(),
                            entrenamiento.getNombre(),
                            entrenamiento.getDescripcion(),
                            entrenamiento.getDuracion(),
                            entrenamiento.getNivel())
            );

            return lista;
        } catch (PersistenceException e) {
            handleException("Error al obtener la lista de entrenamientos", e);
            // Retornamos una lista vacía en caso de error
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    // Actualizar entrenamiento
    public void update(entrenamientos entrenamiento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entrenamiento);
            em.getTransaction().commit();
            System.out.println("[EntrenamientoDAO] Entrenamiento actualizado correctamente. ID: " + entrenamiento.getId());
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            handleException("Error al actualizar el entrenamiento", e);
        } finally {
            em.close();
        }
    }

    // Eliminar por ID
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            entrenamientos entrenamiento = em.find(entrenamientos.class, id);
            if (entrenamiento != null) {
                em.remove(entrenamiento);
                System.out.println("[EntrenamientoDAO] Entrenamiento eliminado correctamente. ID: " + id);
            } else {
                System.out.println("[EntrenamientoDAO] No se encontró el entrenamiento con ID: " + id);
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            handleException("Error al eliminar el entrenamiento con ID: " + id, e);
        } finally {
            em.close();
        }
    }

    // Buscar por nivel
    public List<entrenamientos> findByNivel(String nivel) {
        EntityManager em = emf.createEntityManager();
        try {
            if (nivel == null || nivel.isBlank()) {
                System.out.println("[EntrenamientoDAO] El argumento nivel está vacío o nulo.");
                return Collections.emptyList();
            }
            System.out.println("[EntrenamientoDAO] Buscando entrenamientos con nivel: " + nivel);
            List<entrenamientos> lista = em.createQuery("SELECT e FROM entrenamientos e WHERE e.nivel = :nivel", entrenamientos.class)
                    .setParameter("nivel", nivel)
                    .getResultList();
            System.out.println("[EntrenamientoDAO] Registros obtenidos con nivel \"" + nivel + "\": " + lista.size());
            return lista;
        } catch (PersistenceException e) {
            handleException("Error al buscar entrenamientos por nivel: " + nivel, e);
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    // Manejo de excepciones
    private void handleException(String mensaje, Exception e) {
        System.err.println("[EntrenamientoDAO] " + mensaje + ": " + e.getMessage());
        e.printStackTrace();
    }

    // Cerrar EntityManagerFactory
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("[EntrenamientoDAO] EntityManagerFactory cerrado correctamente.");
        }
    }
}