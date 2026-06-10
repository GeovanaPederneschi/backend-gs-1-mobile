package com.fiap.ec.orbital_mission_control.controller;

import com.fiap.ec.orbital_mission_control.model.LeituraSensor;
import com.fiap.ec.orbital_mission_control.service.LeituraSensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras")
@CrossOrigin
public class LeituraSensorController {

    private final LeituraSensorService leituraSensorService;

    public LeituraSensorController(LeituraSensorService leituraSensorService) {
        this.leituraSensorService = leituraSensorService;
    }

    @PostMapping
    public LeituraSensor criar(@RequestBody LeituraSensor leituraSensor) {
        return leituraSensorService.salvar(leituraSensor);
    }

    @GetMapping
    public List<LeituraSensor> listar() {
        return leituraSensorService.listar();
    }

    @GetMapping("/{id}")
    public LeituraSensor buscarPorId(@PathVariable Long id) {
        return leituraSensorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LeituraSensor atualizar(@PathVariable Long id, @RequestBody LeituraSensor leituraSensor) {
        return leituraSensorService.atualizar(id, leituraSensor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        leituraSensorService.deletar(id);
    }

}
