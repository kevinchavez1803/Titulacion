package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.rol_usuario;

import java.util.List;

public class RolUsuarioDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");

    public void create(rol_usuario rolUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rolUsuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public rol_usuario findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(rol_usuario.class, id);
        } finally {
            em.close();
        }
    }

    public List<rol_usuario> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT ru FROM rol_usuario ru", rol_usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(rol_usuario rolUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(rolUsuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            rol_usuario rolUsuario = em.find(rol_usuario.class, id);
            if (rolUsuario != null) {
                em.remove(rolUsuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}