package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UsuarioDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymPU");

    // Método para crear un usuario (sin cifrar la contraseña)
    public void create(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para buscar un usuario por ID
    public Usuario findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    // Método para listar todos los usuarios excluyendo los roles
    public List<Usuario> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // Consulta básica sin cargar relaciones de roles
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Implementación del método obtenerUsuarios reutilizando findAll
    public List<Usuario> obtenerUsuarios() {
        return this.findAll(); // Reutiliza findAll para obtener los usuarios
    }

    // Método para actualizar un usuario
    public void update(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un usuario por ID
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para validar al usuario al iniciar sesión
    public Usuario validarUsuario(String username, String passwordIngresada) {
        EntityManager em = emf.createEntityManager();
        try {
            // Busca al usuario en la base de datos por username
            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();

            // Genera el hash de la contraseña ingresada usando MD5
            String hashIngresado = cifrarMD5(passwordIngresada);

            // Compara la contraseña ingresada con la almacenada
            if (usuario.getPassword().equals(hashIngresado)) {
                return usuario; // Contraseña correcta
            } else {
                return null; // Contraseña incorrecta
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    // Método estático para cifrar una contraseña usando MD5
    private static String cifrarMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar con MD5", e);
        }
    }
}