package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.entrenadores;
import org.example.service.EntrenadorService;

import java.util.List;

@Path("/entrenadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntrenadorController {

    private final EntrenadorService entrenadorService = new EntrenadorService();

    @POST
    public String crearEntrenador(entrenadores entrenador) {
        entrenadorService.crearEntrenador(entrenador);
        return "Entrenador creado correctamente.";
    }

    @GET
    @Path("/{id}")
    public entrenadores obtenerEntrenador(@PathParam("id") Long id) {
        return entrenadorService.obtenerEntrenadorPorId(id);
    }

    @GET
    public List<entrenadores> listarEntrenadores() {
        return entrenadorService.obtenerTodosLosEntrenadores();
    }

    @PUT
    public String actualizarEntrenador(entrenadores entrenador) {
        entrenadorService.actualizarEntrenador(entrenador);
        return "Entrenador actualizado correctamente.";
    }

    @DELETE
    @Path("/{id}")
    public String eliminarEntrenador(@PathParam("id") Long id) {
        entrenadorService.eliminarEntrenador(id);
        return "Entrenador eliminado correctamente.";
    }
}