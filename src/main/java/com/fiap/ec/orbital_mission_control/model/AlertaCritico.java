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
@Table(name = "alertas_criticos")
public class AlertaCritico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorModulo sensor;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String tipoAlerta;

    @Column(nullable = false)
    private String nivelCriticidade;

    private Boolean resolvido;

    @Column(nullable = false)
    private LocalDateTime timestamp;

}
