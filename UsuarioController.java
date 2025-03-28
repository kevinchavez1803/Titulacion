package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    @POST
    public String crearUsuario(Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return "Usuario creado correctamente.";
    }

    @GET
    @Path("/{id}")
    public Usuario obtenerUsuario(@PathParam("id") Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GET
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PUT
    public String actualizarUsuario(Usuario usuario) {
        usuarioService.actualizarUsuario(usuario);
        return "Usuario actualizado correctamente.";
    }

    @DELETE
    @Path("/{id}")
    public String eliminarUsuario(@PathParam("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado correctamente.";
    }
}