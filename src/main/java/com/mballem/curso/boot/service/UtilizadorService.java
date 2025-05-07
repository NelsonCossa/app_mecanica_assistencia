package com.mballem.curso.boot.service;

import java.util.List;

import com.mballem.curso.boot.domain.Utilizador;

public interface UtilizadorService {

    void salvar(Utilizador utilizador);

    void editar(Utilizador utilizador);

    void excluir(Long id);

    Utilizador buscarPorId(Long id);

    List<Utilizador> buscarTodos();

    boolean utilizadorTemEspecialidades(Long id);
}

