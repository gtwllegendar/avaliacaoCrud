package com.senac.labcontrol.service;

import com.senac.labcontrol.exception.PatrimonioDuplicadoException;
import com.senac.labcontrol.exception.RecursoNaoEncontradoException;
import com.senac.labcontrol.model.Equipamento;
import com.senac.labcontrol.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public Equipamento cadastrar(Equipamento e) {
        if (repository.existsByNumeroPatrimonio(e.getNumeroPatrimonio())) {
            throw new PatrimonioDuplicadoException(
                "Já existe um equipamento com o patrimônio " + e.getNumeroPatrimonio());
        }
        e.setId(null); // garante INSERT
        return repository.save(e);
    }

    public List<Equipamento> listar() {
        return repository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new RecursoNaoEncontradoException("Equipamento não encontrado com id " + id));
    }

    public Equipamento atualizar(Long id, Equipamento dados) {
        Equipamento existente = buscarPorId(id);

        repository.findByNumeroPatrimonio(dados.getNumeroPatrimonio())
            .filter(outro -> !outro.getId().equals(id))
            .ifPresent(outro -> {
                throw new PatrimonioDuplicadoException(
                    "O patrimônio " + dados.getNumeroPatrimonio() + " já pertence a outro equipamento");
            });

        existente.setNome(dados.getNome());
        existente.setNumeroPatrimonio(dados.getNumeroPatrimonio());
        existente.setCategoria(dados.getCategoria());
        existente.setQuantidade(dados.getQuantidade());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Equipamento existente = buscarPorId(id);
        repository.delete(existente);
    }
}
