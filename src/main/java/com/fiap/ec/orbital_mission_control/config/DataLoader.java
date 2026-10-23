package com.fiap.ec.orbital_mission_control.config;

import com.fiap.ec.orbital_mission_control.model.AlertaCritico;
import com.fiap.ec.orbital_mission_control.model.LeituraSensor;
import com.fiap.ec.orbital_mission_control.model.SensorModulo;
import com.fiap.ec.orbital_mission_control.repository.AlertaCriticoRepository;
import com.fiap.ec.orbital_mission_control.repository.LeituraSensorRepository;
import com.fiap.ec.orbital_mission_control.repository.SensorModuloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Popula o banco H2 com dados iniciais apenas quando ele está vazio
 * (primeira execução após clonar o repositório). Os dados cadastrados
 * depois pelo app continuam persistidos em ./data/orbital.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final SensorModuloRepository sensorModuloRepository;
    private final LeituraSensorRepository leituraSensorRepository;
    private final AlertaCriticoRepository alertaCriticoRepository;

    public DataLoader(SensorModuloRepository sensorModuloRepository,
                      LeituraSensorRepository leituraSensorRepository,
                      AlertaCriticoRepository alertaCriticoRepository) {
        this.sensorModuloRepository = sensorModuloRepository;
        this.leituraSensorRepository = leituraSensorRepository;
        this.alertaCriticoRepository = alertaCriticoRepository;
    }

    @Override
    public void run(String... args) {
        if (sensorModuloRepository.count() > 0) {
            return;
        }

        SensorModulo temperatura = sensorModuloRepository.save(
                new SensorModulo(null, "Sensor de Temperatura TMP-01", "temperatura", "Módulo A", true));
        SensorModulo pressao = sensorModuloRepository.save(
                new SensorModulo(null, "Sensor de Pressão PRS-03", "pressao", "Compartimento Central", true));
        SensorModulo radiacao = sensorModuloRepository.save(
                new SensorModulo(null, "Sensor de Radiação RAD-07", "radiacao", "Painel Externo Norte", true));

        leituraSensorRepository.save(new LeituraSensor(null, temperatura, 87.5, "°C", "atencao",
                LocalDateTime.of(2026, 6, 1, 14, 30), "Temperatura acima do normal no módulo A"));
        leituraSensorRepository.save(new LeituraSensor(null, pressao, 101.2, "kPa", "normal",
                LocalDateTime.of(2026, 6, 1, 14, 32), null));
        leituraSensorRepository.save(new LeituraSensor(null, radiacao, 0.85, "Sv/h", "critico",
                LocalDateTime.of(2026, 6, 1, 14, 45), "Nível de radiação acima do limite seguro"));

        alertaCriticoRepository.save(new AlertaCritico(null, temperatura, "Temperatura crítica detectada no Módulo A",
                "TEMPERATURA_ALTA", "alto", false, LocalDateTime.of(2026, 6, 1, 14, 35)));
        alertaCriticoRepository.save(new AlertaCritico(null, radiacao, "Nível de radiação ultrapassou limite máximo permitido",
                "RADIACAO_CRITICA", "critico", false, LocalDateTime.of(2026, 6, 1, 14, 46)));
        alertaCriticoRepository.save(new AlertaCritico(null, pressao, "Queda de pressão detectada no compartimento central",
                "PRESSAO_BAIXA", "medio", false, LocalDateTime.of(2026, 6, 1, 15, 10)));
    }

}
