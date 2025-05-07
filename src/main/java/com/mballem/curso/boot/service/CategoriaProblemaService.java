package com.mballem.curso.boot.service;



import java.util.List;
import java.util.Set;

import com.mballem.curso.boot.domain.CategoriaProblema;


public interface CategoriaProblemaService {

    void salvar(CategoriaProblema categoriaProblema);
    
    void editar(CategoriaProblema categoriaProblema);
    
    void excluir(Long id);
    
    CategoriaProblema buscarPorId(Long id);
    
    List<CategoriaProblema> buscarTodos();
    
    List<CategoriaProblema> buscarPorNome(String nome);


	//List<CategoriaProblema> buscarTodosPorIds(List<Long> categoriasIds);

	List<CategoriaProblema> buscarTodosPorIds(Set<Long> ids);

}