package com.mballem.curso.boot.dao;


import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Solicitacao;
import com.mballem.curso.boot.service.SolicitacaoService;

@Repository
public class SolicitacaoDaoImpl extends AbstractDao<Solicitacao, Long> implements SolicitacaoDao {
    // Não há necessidade de implementação adicional, a classe pai já fornece os métodos.
	
}
