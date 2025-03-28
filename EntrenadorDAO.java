package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.entrenadores;

import java.util.List;

public class EntrenadorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");

    public void create(entrenadores entrenador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entrenador);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public entrenadores findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entrenadores.class, id);
        } finally {
            em.close();
        }
    }

    public List<entrenadores> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM entrenadores e", entrenadores.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(entrenadores entrenador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entrenador);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            entrenadores entrenador = em.find(entrenadores.class, id);
            if (entrenador != null) {
                em.remove(entrenador);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}