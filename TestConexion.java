package org.example.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConexion {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            System.out.println("✅ Conexión exitosa a la base de datos.");
            em.getTransaction().commit();

            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println("❌ Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
