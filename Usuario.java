package org.example.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios") // Nombre de la tabla en la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    @Column(name = "id_usuario") // Nombre de la columna para el ID del usuario
    private Long id;

    @Column(nullable = false, length = 100) // Columna no puede ser nula. Máx. 100 caracteres
    private String nombre;

    @Column(nullable = false, length = 100) // Columna apellido, mismas restricciones que nombre
    private String apellido;

    @Column(nullable = false, unique = true, length = 150) // Email debe ser único
    private String email;

    @Column(nullable = false, unique = true, length = 50) // Username debe ser único
    private String username;

    @Column(nullable = false, length = 100) // Longitud máxima de la contraseña: 100 caracteres
    private String password;

    private String telefono; // Teléfono opcional

    private String direccion; // Dirección opcional


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<rol_usuario> listaDeRolesUsuarios = new HashSet<>();

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<rol_usuario> getListaDeRolesUsuarios() {
        return listaDeRolesUsuarios;
    }

    public void setListaDeRolesUsuarios(Set<rol_usuario> listaDeRolesUsuarios) {
        this.listaDeRolesUsuarios = listaDeRolesUsuarios;
    }


    public void addRolUsuario(rol_usuario rolUsuario) {
        this.listaDeRolesUsuarios.add(rolUsuario);  // Agrega a la lista local
        rolUsuario.setUsuario(this);               // Actualiza el lado opuesto
    }


    public void removeRolUsuario(rol_usuario rolUsuario) {
        this.listaDeRolesUsuarios.remove(rolUsuario); // Lo elimina de la lista local
        rolUsuario.setUsuario(null);                 // Rompe la relación en el otro lado
    }
}