package com.mballem.curso.boot.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.CategoriaProblemaDao;
import com.mballem.curso.boot.domain.CategoriaProblema;

@Service
@Transactional(readOnly = false)
public class CategoriaProblemaServiceImpl implements CategoriaProblemaService {

    @Autowired
    private CategoriaProblemaDao dao;

    @Override
    public void salvar(CategoriaProblema categoriaProblema) {
        dao.save(categoriaProblema);
    }

    @Override
    public void editar(CategoriaProblema categoriaProblema) {
        dao.update(categoriaProblema);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaProblema buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProblema> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProblema> buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }
    @Override
    @Transactional(readOnly = true)
  
    public List<CategoriaProblema> buscarTodosPorIds(Set<Long> ids) {
        return dao.findByIds(ids);
    }
}