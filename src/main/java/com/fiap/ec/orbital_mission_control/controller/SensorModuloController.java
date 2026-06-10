package com.fiap.ec.orbital_mission_control.controller;

import com.fiap.ec.orbital_mission_control.model.SensorModulo;
import com.fiap.ec.orbital_mission_control.service.SensorModuloService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
@CrossOrigin
public class SensorModuloController {

    private final SensorModuloService sensorModuloService;

    public SensorModuloController(SensorModuloService sensorModuloService) {
        this.sensorModuloService = sensorModuloService;
    }

    @PostMapping
    public SensorModulo criar(@RequestBody SensorModulo sensorModulo) {
        return sensorModuloService.salvar(sensorModulo);
    }

    @GetMapping
    public List<SensorModulo> listar() {
        return sensorModuloService.listar();
    }

    @GetMapping("/{id}")
    public SensorModulo buscarPorId(@PathVariable Long id) {
        return sensorModuloService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public SensorModulo atualizar(@PathVariable Long id, @RequestBody SensorModulo sensorModulo) {
        return sensorModuloService.atualizar(id, sensorModulo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sensorModuloService.deletar(id);
    }

}
