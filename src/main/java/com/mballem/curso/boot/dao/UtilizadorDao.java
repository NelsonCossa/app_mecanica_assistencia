package com.mballem.curso.boot.dao;



import java.util.List;

import com.mballem.curso.boot.domain.Utilizador;

public interface UtilizadorDao {

    void save(Utilizador utilizador);

    void update(Utilizador utilizador);

    void delete(Long id);

    Utilizador findById(Long id);

    List<Utilizador> findAll();
}
