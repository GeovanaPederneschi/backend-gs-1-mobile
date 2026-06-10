package com.fiap.ec.orbital_mission_control.service;

import com.fiap.ec.orbital_mission_control.model.SensorModulo;
import com.fiap.ec.orbital_mission_control.repository.SensorModuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorModuloService {

    private final SensorModuloRepository sensorModuloRepository;

    public SensorModuloService(SensorModuloRepository sensorModuloRepository) {
        this.sensorModuloRepository = sensorModuloRepository;
    }

    public SensorModulo salvar(SensorModulo sensorModulo) {
        return sensorModuloRepository.save(sensorModulo);
    }

    public List<SensorModulo> listar() {
        return sensorModuloRepository.findAll();
    }

    public SensorModulo buscarPorId(Long id) {
        return sensorModuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));
    }

    public SensorModulo atualizar(Long id, SensorModulo sensorModulo) {
        SensorModulo existente = buscarPorId(id);
        existente.setNome(sensorModulo.getNome());
        existente.setTipo(sensorModulo.getTipo());
        existente.setLocalizacao(sensorModulo.getLocalizacao());
        existente.setAtivo(sensorModulo.getAtivo());
        return sensorModuloRepository.save(existente);
    }

    public void deletar(Long id) {
        SensorModulo existente = buscarPorId(id);
        sensorModuloRepository.delete(existente);
    }

}
