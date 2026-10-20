package com.senac.labcontrol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O número de patrimônio é obrigatório")
    @Column(name = "numero_patrimonio", nullable = false, unique = true)
    private String numeroPatrimonio;

    @NotBlank(message = "A categoria é obrigatória")
    @Column(nullable = false)
    private String categoria;

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade deve ser maior ou igual a 0")
    @Column(nullable = false)
    private Integer quantidade;

    public Equipamento() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNumeroPatrimonio() { return numeroPatrimonio; }
    public void setNumeroPatrimonio(String numeroPatrimonio) { this.numeroPatrimonio = numeroPatrimonio; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
