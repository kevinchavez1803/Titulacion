package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.rol_usuario;
import org.example.service.RolUsuarioService;

import java.util.List;

@Path("/roles-usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolUsuarioController {

    private final RolUsuarioService rolUsuarioService = new RolUsuarioService();

    @POST
    public String crearRolUsuario(rol_usuario rolUsuario) {
        rolUsuarioService.crearRolUsuario(rolUsuario);
        return "RolUsuario creado correctamente.";
    }

    @GET
    @Path("/{id}")
    public rol_usuario obtenerRolUsuario(@PathParam("id") Long id) {
        return rolUsuarioService.obtenerRolUsuarioPorId(id);
    }

    @GET
    public List<rol_usuario> listarRolUsuarios() {
        return rolUsuarioService.obtenerTodosLosRolUsuarios();
    }

    @PUT
    public String actualizarRolUsuario(rol_usuario rolUsuario) {
        rolUsuarioService.actualizarRolUsuario(rolUsuario);
        return "RolUsuario actualizado correctamente.";
    }

    @DELETE
    @Path("/{id}")
    public String eliminarRolUsuario(@PathParam("id") Long id) {
        rolUsuarioService.eliminarRolUsuario(id);
        return "RolUsuario eliminado correctamente.";
    }
}