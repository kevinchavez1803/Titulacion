package com.gymapp.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(" Intento de inicio de sesión - Usuario: " + username);

        try (Connection connection = com.gymapp.util.DatabaseConnection.getConnection()) {
            if (connection == null) {
                System.err.println(" ERROR: No se pudo establecer conexión con la base de datos.");
                throw new ServletException("No se pudo conectar a la base de datos.");
            }

            // Cifrar la contraseña ingresada
            String hashedPassword = hashPassword(password);
            System.out.println(" Contraseña cifrada (MD5): " + hashedPassword);

            // Consulta SQL para verificar usuario y contraseña
            String sql = "SELECT u.id_usuario, r.nombre AS rol " +
                    "FROM usuarios u " +
                    "JOIN rol_usuario ru ON u.id_usuario = ru.id_usuario " +
                    "JOIN roles r ON ru.id_rol = r.id_rol " +
                    "WHERE u.username = ? AND u.password = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            System.out.println(" Ejecutando consulta SQL...");
            System.out.println(" Parámetros: username=" + username + ", password=" + hashedPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id_usuario");
                String rol = rs.getString("rol").toLowerCase(); // Convertimos a minúsculas para evitar problemas

                System.out.println(" Usuario autenticado - ID: " + userId + ", Rol: " + rol);

                // Crear sesión
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("userRole", rol);

                // Redirigir según el rol del usuario
                if ("entrenador".equals(rol)) {
                    System.out.println("🔹 Redirigiendo a vistaEntrenador.jsp...");
                    response.sendRedirect("vistaEntrenador.jsp");
                } else if ("cliente".equals(rol)) {
                    System.out.println("🔹 Redirigiendo a vistaCliente.jsp...");
                    response.sendRedirect("vistaCliente.jsp");
                } else {
                    System.out.println("🔹 Redirigiendo a dashboard.jsp...");
                    response.sendRedirect("dashboard.jsp");
                }
            } else {
                System.out.println(" Usuario o contraseña incorrectos.");
                request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println(" ERROR en el proceso de autenticación:");
            e.printStackTrace(); // Mostrar error detallado
            throw new ServletException("Error al conectar o consultar la base de datos", e);
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
