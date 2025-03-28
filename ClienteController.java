package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.clientes;
import org.example.service.clienteService;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    // Inyección de dependencias
    @Inject
    private clienteService clienteService;

    // Crear un cliente
    @POST
    public String crearCliente(clientes cliente) {
        clienteService.crearCliente(cliente);
        return "Cliente creado correctamente.";
    }

    // Obtener un cliente por ID
    @GET
    @Path("/{id}")
    public clientes obtenerCliente(@PathParam("id") Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    // Listar todos los clientes
    @GET
    public List<clientes> listarClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    // Actualizar un cliente
    @PUT
    public String actualizarCliente(clientes cliente) {
        clienteService.actualizarCliente(cliente);
        return "Cliente actualizado correctamente.";
    }

    // Eliminar un cliente por ID
    @DELETE
    @Path("/{id}")
    public String eliminarCliente(@PathParam("id") Long id) {
        clienteService.eliminarCliente(id);
        return "Cliente eliminado correctamente.";
    }
}