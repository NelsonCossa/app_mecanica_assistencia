package com.mballem.curso.boot.dao;



import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Especialidade;

@Repository
public class EspecialidadeDaoImpl extends AbstractDao<Especialidade, Long> implements EspecialidadeDao {
    // Não há necessidade de implementação adicional, a classe pai já fornece os métodos.
}
