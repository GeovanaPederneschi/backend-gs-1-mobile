package com.fiap.ec.orbital_mission_control.repository;

import com.fiap.ec.orbital_mission_control.model.AlertaCritico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaCriticoRepository extends JpaRepository<AlertaCritico, Long> {
}
