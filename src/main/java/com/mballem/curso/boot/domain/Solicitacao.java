package com.mballem.curso.boot.domain;

import jakarta.persistence.*;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "SOLICITACOES")
public class Solicitacao extends AbstractEntity<Long> {

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false)
    private TipoServico tipoServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacao status;

    @ManyToMany
    @JoinTable(
        name = "SOLICITACOES_CATEGORIAS",
        joinColumns = @JoinColumn(name = "solicitacao_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_problema_id")
    )
    private List<CategoriaProblema> categorias;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public List<CategoriaProblema> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaProblema> categorias) {
        this.categorias = categorias;
    }
}
