package org.example.service;

import org.example.dao.clienteDAO;
import org.example.model.clientes;

import java.util.List;

public class clienteService {

    private final clienteDAO clienteDAO = new clienteDAO();

    public void crearCliente(clientes cliente) {
        try {
            clienteDAO.create(cliente);
            System.out.println("Cliente creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    public clientes obtenerClientePorId(Long id) {
        try {
            return clienteDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener el cliente: " + e.getMessage());
            return null;
        }
    }

    public List<clientes> obtenerTodosLosClientes() {
        try {
            return clienteDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
            return null;
        }
    }

    public void actualizarCliente(clientes cliente) {
        try {
            clienteDAO.update(cliente);
            System.out.println("Cliente actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(Long id) {
        try {
            clienteDAO.delete(id);
            System.out.println("Cliente eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }
}