package com.gymapp.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.EntrenamientoDAO;
import org.example.model.entrenamientos;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarEntrenamientosServlet", urlPatterns = {"/listarEntrenamientos"})
public class ListarEntrenamientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar el tipo de contenido de la respuesta
        response.setContentType("text/html;charset=UTF-8");

        List<entrenamientos> entrenamientos = null;

        try {
            // Obtener los entrenamientos desde el DAO
            entrenamientos = entrenamientoDAO.findAll();
        } catch (Exception e) {
            // Registro de errores en el DAO
            System.err.println("[ListarEntrenamientosServlet] Error al obtener entrenamientos: " + e.getMessage());
            e.printStackTrace();
        }

        // Verificar los datos obtenidos y proceder
        if (entrenamientos == null || entrenamientos.isEmpty()) {
            // Caso: no hay datos disponibles o hay un error
            System.out.println("[ListarEntrenamientosServlet] No se encontraron entrenamientos.");
            request.setAttribute("error", "No se encontraron entrenamientos disponibles.");
        } else {
            // Caso: se encontraron datos
            System.out.println("[ListarEntrenamientosServlet] Total entrenamientos obtenidos: " + entrenamientos.size());
        }

        // Pasar los datos (o el mensaje de error) al JSP
        request.setAttribute("entrenamientos", entrenamientos);

        // Redirigir al JSP correspondiente
        request.getRequestDispatcher("/listaEntrenamientos.jsp").forward(request, response);
    }
}