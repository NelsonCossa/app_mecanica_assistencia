package com.mballem.curso.boot.service;

import java.util.List;

import com.mballem.curso.boot.domain.Solicitacao;

public interface SolicitacaoService {

    void salvar(Solicitacao solicitacao);

    void editar(Solicitacao solicitacao);

    void excluir(Long id);

    Solicitacao buscarPorId(Long id);

    List<Solicitacao> buscarTodos();
}
