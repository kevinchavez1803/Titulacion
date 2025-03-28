package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.entrenamientos;
import org.example.service.EntrenamientoService;

import java.util.List;

@Path("/entrenamientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService = new EntrenamientoService();

    @POST
    public String crearEntrenamiento(entrenamientos entrenamiento) {
        entrenamientoService.crearEntrenamiento(entrenamiento);
        return "Entrenamiento creado correctamente.";
    }

    @GET
    @Path("/{id}")
    public entrenamientos obtenerEntrenamiento(@PathParam("id") Long id) {
        return entrenamientoService.obtenerEntrenamientoPorId(id);
    }

    @GET
    public List<entrenamientos> listarEntrenamientos() {
        return entrenamientoService.obtenerTodosLosEntrenamientos();
    }

    @PUT
    public String actualizarEntrenamiento(entrenamientos entrenamiento) {
        entrenamientoService.actualizarEntrenamiento(entrenamiento);
        return "Entrenamiento actualizado correctamente.";
    }

    @DELETE
    @Path("/{id}")
    public String eliminarEntrenamiento(@PathParam("id") Long id) {
        entrenamientoService.eliminarEntrenamiento(id);
        return "Entrenamiento eliminado correctamente.";
    }
}