package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Rol;

import java.util.List;

public class RolDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");

    public void create(Rol rol) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rol);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Rol findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public List<Rol> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Rol r", Rol.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Rol rol) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(rol);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Rol rol = em.find(Rol.class, id);
            if (rol != null) {
                em.remove(rol);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}