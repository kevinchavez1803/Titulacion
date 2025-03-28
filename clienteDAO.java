package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.clientes;

import java.util.List;

public class clienteDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");

    public void create(clientes cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public clientes findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(clientes.class, id);
        } finally {
            em.close();
        }
    }

    public List<clientes> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM clientes c", clientes.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(clientes cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            clientes cliente = em.find(clientes.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}