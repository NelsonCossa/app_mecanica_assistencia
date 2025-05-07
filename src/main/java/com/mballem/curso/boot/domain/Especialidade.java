package com.mballem.curso.boot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "especialidades")
public class Especialidade extends AbstractEntity<Long> {

    // Caso a classe AbstractEntity já tenha o campo 'id', remova a declaração de 'id' daqui

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToMany(mappedBy = "especialidades")
    private Set<Utilizador> utilizadores;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(Set<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }
}
