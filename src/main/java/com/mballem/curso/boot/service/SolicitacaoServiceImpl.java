package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.SolicitacaoDao;
import com.mballem.curso.boot.domain.Solicitacao;

@Service @Transactional(readOnly = false)
public class SolicitacaoServiceImpl implements SolicitacaoService {

    @Autowired
    private SolicitacaoDao dao;

    @Override
    public Solicitacao salvar(Solicitacao solicitacao) {
        dao.save(solicitacao);
		return solicitacao;
    }

    @Override
    public void editar(Solicitacao solicitacao) {
        dao.update(solicitacao);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override @Transactional(readOnly = true)
    public Solicitacao buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Solicitacao> buscarTodos() {
        return dao.findAll();
    }
}
