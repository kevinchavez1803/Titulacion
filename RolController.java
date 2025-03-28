package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.Rol;
import org.example.service.RolService;

import java.util.List;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolController {

    private final RolService rolService = new RolService();

    @POST
    public String crearRol(Rol rol) {
        rolService.crearRol(rol);
        return "Rol creado correctamente.";
    }

    @GET
    @Path("/{id}")
    public Rol obtenerRol(@PathParam("id") Long id) {
        return rolService.obtenerRolPorId(id);
    }

    @GET
    public List<Rol> listarRoles() {
        return rolService.obtenerTodosLosRoles();
    }

    @PUT
    public String actualizarRol(Rol rol) {
        rolService.actualizarRol(rol);
        return "Rol actualizado correctamente.";
    }

    @DELETE
    @Path("/{id}")
    public String eliminarRol(@PathParam("id") Long id) {
        rolService.eliminarRol(id);
        return "Rol eliminado correctamente.";
    }
}