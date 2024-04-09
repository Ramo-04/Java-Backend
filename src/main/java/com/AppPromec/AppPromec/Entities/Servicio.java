package com.AppPromec.AppPromec.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SERVICIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @Column(name = "id_servicio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_servicio")
    private String nombre_servicio;

    @Column(name = "descripcion_servicio")
    private String descripcion_servicio;

    @Column(name = "calidad_servicio")
    private String calidad_servicio;

    @Column(name = "tipo_servicio")
    private String tipo_servicio;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
