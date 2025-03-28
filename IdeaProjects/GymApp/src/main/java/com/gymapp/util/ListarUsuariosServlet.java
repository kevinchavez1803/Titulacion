package com.gymapp.util;

import org.example.model.Usuario;
import org.example.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listarUsuarios") // URL que activará este Servlet
public class ListarUsuariosServlet extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/listaUsuarios.jsp").forward(request, response);
    }
}