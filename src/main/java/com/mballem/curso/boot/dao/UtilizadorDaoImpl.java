package com.mballem.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Utilizador;

@Repository
public class UtilizadorDaoImpl extends AbstractDao<Utilizador, Long> implements UtilizadorDao {
    // Não há necessidade de implementação adicional, a classe pai já fornece os métodos.
}
