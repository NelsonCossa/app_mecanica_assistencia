package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.UtilizadorDao;
import com.mballem.curso.boot.domain.Utilizador;

@Service @Transactional(readOnly = false)
public class UtilizadorServiceImpl implements UtilizadorService {

    @Autowired
    private UtilizadorDao dao;

    @Override
    public void salvar(Utilizador utilizador) {
        dao.save(utilizador);
    }

    @Override
    public void editar(Utilizador utilizador) {
        dao.update(utilizador);
    }
 

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override @Transactional(readOnly = true)
    public Utilizador buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Utilizador> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean utilizadorTemEspecialidades(Long id) {
        if (buscarPorId(id).getEspecialidades().isEmpty()) {
            return false;
        }
        return true;
    }
}
