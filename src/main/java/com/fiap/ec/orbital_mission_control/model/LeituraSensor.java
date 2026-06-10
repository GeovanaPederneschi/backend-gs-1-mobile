package com.fiap.ec.orbital_mission_control.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leituras_sensores")
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorModulo sensor;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String unidade;

    @Column(nullable = false)
    private String statusOperacional;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String observacoes;

}
