package com.fiap.ec.orbital_mission_control.service;

import com.fiap.ec.orbital_mission_control.model.AlertaCritico;
import com.fiap.ec.orbital_mission_control.repository.AlertaCriticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaCriticoService {

    private final AlertaCriticoRepository alertaCriticoRepository;

    public AlertaCriticoService(AlertaCriticoRepository alertaCriticoRepository) {
        this.alertaCriticoRepository = alertaCriticoRepository;
    }

    public AlertaCritico salvar(AlertaCritico alertaCritico) {
        return alertaCriticoRepository.save(alertaCritico);
    }

    public List<AlertaCritico> listar() {
        return alertaCriticoRepository.findAll();
    }

    public AlertaCritico buscarPorId(Long id) {
        return alertaCriticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
    }

    public AlertaCritico atualizar(Long id, AlertaCritico alertaCritico) {
        AlertaCritico existente = buscarPorId(id);
        existente.setSensor(alertaCritico.getSensor());
        existente.setDescricao(alertaCritico.getDescricao());
        existente.setTipoAlerta(alertaCritico.getTipoAlerta());
        existente.setNivelCriticidade(alertaCritico.getNivelCriticidade());
        existente.setResolvido(alertaCritico.getResolvido());
        existente.setTimestamp(alertaCritico.getTimestamp());
        return alertaCriticoRepository.save(existente);
    }

    public void deletar(Long id) {
        AlertaCritico existente = buscarPorId(id);
        alertaCriticoRepository.delete(existente);
    }

}
