package com.mballem.curso.boot.dao;



import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.CategoriaProblema;


@Repository
public class CategoriaProblemaDaoImpl extends AbstractDao<CategoriaProblema, Long> implements CategoriaProblemaDao {

    @Override
    public List<CategoriaProblema> findByNome(String nome) {
        return createQuery("SELECT c FROM CategoriaProblema c WHERE c.nome LIKE CONCAT('%', ?1, '%')", nome);
    }
    @Override
    public List<CategoriaProblema> findByIds(Set<Long> ids) {
        return createQuery("SELECT c FROM CategoriaProblema c WHERE c.id IN ?1", ids);
    }
  
	
}