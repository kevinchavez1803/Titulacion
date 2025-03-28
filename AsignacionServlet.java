package com.gymapp.util;

import org.example.dao.ClienteEntrenamientoDAO;
import org.example.model.clientes;
import org.example.model.ClienteEntrenamiento;
import org.example.model.entrenadores;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/AsignacionServlet")
public class AsignacionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la conexión al EntityManager desde DatabaseConnection
        EntityManager entityManager = jakarta.persistence.Persistence.createEntityManagerFactory("GymPU").createEntityManager();
        try {
            // Obtener la lista de clientes y entrenadores desde la base de datos
            List<clientes> clientesList = entityManager.createQuery("SELECT c FROM clientes c", clientes.class).getResultList();
            List<entrenadores> entrenadoresList = entityManager.createQuery("SELECT e FROM entrenadores e", entrenadores.class).getResultList();

            // Pasar los clientes y entrenadores al JSP
            request.setAttribute("clientes", clientesList);
            request.setAttribute("entrenadores", entrenadoresList);

            // Redirigir a la página JSP (clienteEntrenamiento.jsp)
            request.getRequestDispatcher("/clienteEntrenamiento.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar los clientes y entrenadores");
        } finally {
            // Asegurarse de cerrar el EntityManager
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la conexión al EntityManager desde DatabaseConnection
        EntityManager entityManager = jakarta.persistence.Persistence.createEntityManagerFactory("GymPU").createEntityManager();
        try {
            // Obtener los parámetros de cliente y entrenador desde el formulario
            Long clienteId = Long.parseLong(request.getParameter("id_cliente"));
            Long entrenadorId = Long.parseLong(request.getParameter("id_entrenador"));

            // Buscar las entidades cliente y entrenador en la base de datos
            clientes cliente = entityManager.find(clientes.class, clienteId);
            entrenadores entrenador = entityManager.find(entrenadores.class, entrenadorId);

            // Verificar que en realidad existan ambas entidades antes de proceder
            if (cliente != null && entrenador != null) {
                ClienteEntrenamiento asignacion = new ClienteEntrenamiento();
                asignacion.setCliente(cliente);
                asignacion.setEntrenador(entrenador);
                asignacion.setFechaAsignacion(new Date());
                asignacion.setEstado("activo");

                // Realizar la asignación llamando al DAO
                ClienteEntrenamientoDAO dao = new ClienteEntrenamientoDAO(entityManager);
                dao.asignarClienteAEntrenador(asignacion);
            }

            // Redireccionar al dashboard una vez procesada la solicitud
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            // Manejar cualquier error y redireccionar con un mensaje de error (opcional)
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la asignación");
        } finally {
            // Asegurarse de cerrar el EntityManager si es necesario
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
