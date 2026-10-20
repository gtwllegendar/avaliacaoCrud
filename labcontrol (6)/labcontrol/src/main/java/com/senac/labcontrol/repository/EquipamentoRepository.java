package com.senac.labcontrol.repository;

import com.senac.labcontrol.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Optional<Equipamento> findByNumeroPatrimonio(String numeroPatrimonio);
    boolean existsByNumeroPatrimonio(String numeroPatrimonio);
}
