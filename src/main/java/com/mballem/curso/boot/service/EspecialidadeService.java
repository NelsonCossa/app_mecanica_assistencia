package com.mballem.curso.boot.service;

import java.util.List;

import com.mballem.curso.boot.domain.Especialidade;

public interface EspecialidadeService {

    void salvar(Especialidade especialidade);

    void editar(Especialidade especialidade);

    void excluir(Long id);

    Especialidade buscarPorId(Long id);

    List<Especialidade> buscarTodos();
}
