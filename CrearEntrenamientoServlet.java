package com.gymapp.util;

import org.example.dao.EntrenamientoDAO;
import org.example.model.entrenamientos;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CrearEntrenamientoServlet")
public class CrearEntrenamientoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validación y manejo de errores
        try {
            // Validar y obtener los parámetros del formulario
            String nombre = request.getParameter("nombre_entrenamiento");
            String descripcion = request.getParameter("descripcion");
            String duracionStr = request.getParameter("duracion");
            String nivel = request.getParameter("nivel");

            // Validar parámetros requeridos
            if (nombre == null || nombre.isEmpty() || descripcion == null || descripcion.isEmpty()
                    || duracionStr == null || duracionStr.isEmpty() || nivel == null || nivel.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios");
                return;
            }

            int duracion;
            try {
                duracion = Integer.parseInt(duracionStr); // Convertir la duración en un número entero
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La duración debe ser un número válido");
                return;
            }

            // Crear la entidad Entrenamiento
            entrenamientos entrenamiento = new entrenamientos();
            entrenamiento.setNombre(nombre);
            entrenamiento.setDescripcion(descripcion);
            entrenamiento.setDuracion(duracion);
            entrenamiento.setNivel(nivel);

            // Guardar el entrenamiento en la base de datos mediante el DAO
            EntrenamientoDAO dao = new EntrenamientoDAO();
            dao.create(entrenamiento);

            // Redirigir a la página de éxito
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error para depuración
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud");
        }
    }
}