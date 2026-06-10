package com.fiap.ec.orbital_mission_control.service;

import com.fiap.ec.orbital_mission_control.model.LeituraSensor;
import com.fiap.ec.orbital_mission_control.repository.LeituraSensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraSensorService {

    private final LeituraSensorRepository leituraSensorRepository;

    public LeituraSensorService(LeituraSensorRepository leituraSensorRepository) {
        this.leituraSensorRepository = leituraSensorRepository;
    }

    public LeituraSensor salvar(LeituraSensor leituraSensor) {
        return leituraSensorRepository.save(leituraSensor);
    }

    public List<LeituraSensor> listar() {
        return leituraSensorRepository.findAll();
    }

    public LeituraSensor buscarPorId(Long id) {
        return leituraSensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leitura não encontrada"));
    }

    public LeituraSensor atualizar(Long id, LeituraSensor leituraSensor) {
        LeituraSensor existente = buscarPorId(id);
        existente.setSensor(leituraSensor.getSensor());
        existente.setValor(leituraSensor.getValor());
        existente.setUnidade(leituraSensor.getUnidade());
        existente.setStatusOperacional(leituraSensor.getStatusOperacional());
        existente.setTimestamp(leituraSensor.getTimestamp());
        existente.setObservacoes(leituraSensor.getObservacoes());
        return leituraSensorRepository.save(existente);
    }

    public void deletar(Long id) {
        LeituraSensor existente = buscarPorId(id);
        leituraSensorRepository.delete(existente);
    }

}
