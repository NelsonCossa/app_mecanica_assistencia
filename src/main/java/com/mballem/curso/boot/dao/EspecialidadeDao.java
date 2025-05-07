package com.mballem.curso.boot.dao;



import java.util.List;

import com.mballem.curso.boot.domain.Especialidade;

public interface EspecialidadeDao {

    void save(Especialidade especialidade);

    void update(Especialidade especialidade);

    void delete(Long id);

    Especialidade findById(Long id);

    List<Especialidade> findAll();
}
