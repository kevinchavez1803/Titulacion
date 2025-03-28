package org.example.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<rol_usuario> usuariosRoles = new HashSet<>();

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

    public Set<rol_usuario> getUsuariosRoles() {
        return usuariosRoles;
    }

    public void setUsuariosRoles(Set<rol_usuario> usuariosRoles) {
        this.usuariosRoles = usuariosRoles;
    }

    public void addUsuarioRol(rol_usuario usuarioRol) {
        this.usuariosRoles.add(usuarioRol);
        usuarioRol.setRol(this);
    }
}