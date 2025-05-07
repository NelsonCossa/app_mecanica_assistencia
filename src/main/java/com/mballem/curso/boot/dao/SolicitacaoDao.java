package com.mballem.curso.boot.dao;

import java.util.List;

import com.mballem.curso.boot.domain.Solicitacao;

public interface SolicitacaoDao {

    void save(Solicitacao solicitacao);

    void update(Solicitacao solicitacao);

    void delete(Long id);

    Solicitacao findById(Long id);

    List<Solicitacao> findAll();
}
