package com.senac.labcontrol.controller;

import com.senac.labcontrol.model.Equipamento;
import com.senac.labcontrol.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@CrossOrigin(origins = "*")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(@Valid @RequestBody Equipamento e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(e));
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @Valid @RequestBody Equipamento e) {
        return ResponseEntity.ok(service.atualizar(id, e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
