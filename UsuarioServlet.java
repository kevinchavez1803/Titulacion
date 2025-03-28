package com.gymapp.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UsuarioServlet", urlPatterns = "/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            cerrarSesion(request, response);
            return;
        }

        try (Connection connection = com.gymapp.util.DatabaseConnection.getConnection()) {
            if ("delete".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    eliminarUsuario(connection, id);
                    request.setAttribute("mensaje", "Usuario eliminado correctamente.");
                } else {
                    request.setAttribute("mensaje", "Error: ID no válido para eliminación.");
                }
                response.sendRedirect("dashboard.jsp");
                return;
            }

            if ("edit".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    Usuario usuario = obtenerUsuarioPorId(connection, id);
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                    return;
                }
            }

            List<Usuario> usuarios = listarUsuarios(connection);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al procesar la solicitud", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = com.gymapp.util.DatabaseConnection.getConnection()) {
            String idParam = request.getParameter("id");
            boolean actualizado = false;

            if (idParam != null && !idParam.isEmpty()) {
                actualizado = modificarUsuario(request, connection, Integer.parseInt(idParam));
                request.setAttribute("mensaje", actualizado ? "Usuario actualizado correctamente." : "Error: No se pudo actualizar el usuario.");
            } else {
                agregarUsuario(request, connection);
                request.setAttribute("mensaje", "Usuario agregado correctamente.");
            }
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al procesar la solicitud", e);
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    private List<Usuario> listarUsuarios(Connection connection) throws Exception {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapResultSetToUsuario(rs));
            }
        }
        return usuarios;
    }

    private Usuario obtenerUsuarioPorId(Connection connection, int id) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE id_usuario=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUsuario(rs);
                }
            }
        }
        return null;
    }

    private void agregarUsuario(HttpServletRequest request, Connection connection) throws Exception {
        // IMPORTANTE: No aplicamos hash en la contraseña, ya que el trigger en la base de datos se encarga de cifrarla
        String sql = "INSERT INTO usuarios (nombre, apellido, email, username, password, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, request.getParameter("nombre"));
            stmt.setString(2, request.getParameter("apellido"));
            stmt.setString(3, request.getParameter("email"));
            stmt.setString(4, request.getParameter("username"));
            stmt.setString(5, request.getParameter("password")); // Se envía en texto plano
            stmt.setString(6, request.getParameter("telefono"));
            stmt.setString(7, request.getParameter("direccion"));
            stmt.executeUpdate();
        }
    }

    private boolean modificarUsuario(HttpServletRequest request, Connection connection, int id) throws Exception {
        String sql = "UPDATE usuarios SET nombre=?, apellido=?, email=?, username=?, telefono=?, direccion=? WHERE id_usuario=?";

        // Imprimir todos los parámetros recibidos para depuración
        System.out.println("Parámetros recibidos para actualizar:");
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            System.out.println(entry.getKey() + " = " + String.join(",", entry.getValue()));
        }

        // Obtener parámetros, permitiendo que algunos puedan estar vacíos
        String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
        String apellido = request.getParameter("apellido") != null ? request.getParameter("apellido") : "";
        String email = request.getParameter("email") != null ? request.getParameter("email") : "";
        String username = request.getParameter("username") != null ? request.getParameter("username") : "";
        String telefono = request.getParameter("telefono") != null ? request.getParameter("telefono") : "";
        String direccion = request.getParameter("direccion") != null ? request.getParameter("direccion") : "";

        System.out.println("Modificando usuario con ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, telefono);
            stmt.setString(6, direccion);
            stmt.setInt(7, id);

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas afectadas en la actualización: " + filasAfectadas);
            return filasAfectadas > 0;
        }
    }

    private void eliminarUsuario(Connection connection, int id) throws Exception {
        String sqlDeleteRol = "DELETE FROM rol_usuario WHERE id_usuario=?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlDeleteRol)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        String sqlDeleteUsuario = "DELETE FROM usuarios WHERE id_usuario=?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlDeleteUsuario)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Usuario mapResultSetToUsuario(ResultSet rs) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setEmail(rs.getString("email"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setDireccion(rs.getString("direccion"));
        return usuario;
    }
}
