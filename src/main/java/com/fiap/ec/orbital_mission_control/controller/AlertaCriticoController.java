package com.fiap.ec.orbital_mission_control.controller;

import com.fiap.ec.orbital_mission_control.model.AlertaCritico;
import com.fiap.ec.orbital_mission_control.service.AlertaCriticoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@CrossOrigin
public class AlertaCriticoController {

    private final AlertaCriticoService alertaCriticoService;

    public AlertaCriticoController(AlertaCriticoService alertaCriticoService) {
        this.alertaCriticoService = alertaCriticoService;
    }

    @PostMapping
    public AlertaCritico criar(@RequestBody AlertaCritico alertaCritico) {
        return alertaCriticoService.salvar(alertaCritico);
    }

    @GetMapping
    public List<AlertaCritico> listar() {
        return alertaCriticoService.listar();
    }

    @GetMapping("/{id}")
    public AlertaCritico buscarPorId(@PathVariable Long id) {
        return alertaCriticoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlertaCritico atualizar(@PathVariable Long id, @RequestBody AlertaCritico alertaCritico) {
        return alertaCriticoService.atualizar(id, alertaCritico);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alertaCriticoService.deletar(id);
    }

}
