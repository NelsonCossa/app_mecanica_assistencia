package com.mballem.curso.boot.web.controller;

import com.mballem.curso.boot.domain.Solicitacao;
import com.mballem.curso.boot.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prestadores")
public class PedidoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping("/disponiveis/{id}")
    public String listarDisponiveis( @PathVariable("id") Long id,Model model) {
        List<Solicitacao> solicitacoes = solicitacaoService.buscarTodos(); // Ou criar um método específico
        model.addAttribute("solicitacoes", solicitacoes);
        
        return "prestadores/disponiveis";
    }

    @GetMapping("/detalhes/{id}")
    public String detalharSolicitacao(@PathVariable("id") Long id, Model model) {
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);
        model.addAttribute("solicitacao", solicitacao);
        return "prestadores/detalhes";
    }

    @PostMapping("/aceitar")
    public String aceitarSolicitacao(@RequestParam("id") Long id, 
                                   @RequestParam("prestadorId") Long prestadorId) {
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);
        // Lógica para associar o prestador (você precisará implementar)
        // solicitacao.setPrestador(prestadorService.buscarPorId(prestadorId));
        // solicitacao.setStatus("EM_ANDAMENTO");
        solicitacaoService.editar(solicitacao);
        return "redirect:/prestadores/disponiveis";
    }
}