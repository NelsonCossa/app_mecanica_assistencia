package com.mballem.curso.boot.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "SOLICITACOES")
public class Solicitacao extends AbstractEntity<Long> {

    @Column(name = "descricao", nullable = true, length = 200)
    private String descricao;
    @Column(name = "data_solicitacao") // Mapeia para o nome da coluna no banco
    private LocalDateTime data_solicitacao; // Use LocalDateTime em vez de Date

    

	

	public LocalDateTime getDataSolicitacao() {
		return data_solicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.data_solicitacao = dataSolicitacao;
	}

	@Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false)
    private TipoServico tipoServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacao status;
    @Column(name = "nome_condutor", nullable = false, length = 100)
    private String nomeCondutor;

    @Column(name = "telefone_condutor", nullable = false, length = 20)
    private String telefoneCondutor;

    @Column(name = "endereco_completo", nullable = false, length = 255)
    private String endereco;

    public String getNomeCondutor() {
		return nomeCondutor;
	}

	public void setNomeCondutor(String nomeCondutor) {
		this.nomeCondutor = nomeCondutor;
	}

	public String getTelefoneCondutor() {
		return telefoneCondutor;
	}

	public void setTelefoneCondutor(String telefoneCondutor) {
		this.telefoneCondutor = telefoneCondutor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

//	public String getDescricaoProblema() {
//		return descricaoProblema;
//	}
//
//	public void setDescricaoProblema(String descricaoProblema) {
//		this.descricaoProblema = descricaoProblema;
//	}

//	@Column(name = "descricao_problema", nullable = false, columnDefinition = "TEXT")
//    private String descricaoProblema;

    @ManyToMany
    @JoinTable(
        name = "SOLICITACOES_CATEGORIAS",
        joinColumns = @JoinColumn(name = "solicitacao_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_problema_id")
    )
   // private List<CategoriaProblema> categorias;
    private Set<CategoriaProblema> categorias = new HashSet<>();

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

	public Set<CategoriaProblema> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaProblema> categorias) {
		this.categorias = categorias;
	}

  //  public List<CategoriaProblema> getCategorias() {
    //    return categorias;
  //  }

   // public void setCategorias(List<CategoriaProblema> categorias) {
     //   this.categorias = categorias;
   // }
}
