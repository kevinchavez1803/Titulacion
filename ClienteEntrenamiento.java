package org.example.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente_entrenamiento")
public class ClienteEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente_entrenamiento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private clientes cliente;

    @ManyToOne
    @JoinColumn(name = "id_entrenador", nullable = false)
    private entrenadores entrenador;

    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;

    @Column(name = "estado")
    private String estado = "activo";

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para cliente
    public clientes getCliente() {
        return cliente;
    }

    public void setCliente(clientes cliente) {
        this.cliente = cliente;
    }

    // Getter y Setter para entrenador
    public entrenadores getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(entrenadores entrenador) {
        this.entrenador = entrenador;
    }

    // Getter y Setter para fechaAsignacion
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    // Getter y Setter para estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}