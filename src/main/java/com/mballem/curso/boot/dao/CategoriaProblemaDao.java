package com.mballem.curso.boot.dao;



import java.util.List;
import java.util.Set;

import com.mballem.curso.boot.domain.CategoriaProblema;


public interface CategoriaProblemaDao {

    void save(CategoriaProblema categoriaProblema);
    
    void update(CategoriaProblema categoriaProblema);
    
    void delete(Long id);
    
    CategoriaProblema findById(Long id);
    
    List<CategoriaProblema> findAll();
    
    List<CategoriaProblema> findByNome(String nome);

	List<CategoriaProblema> findByIds(Set<Long> ids);
}
