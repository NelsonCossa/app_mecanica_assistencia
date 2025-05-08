package com.mballem.curso.boot.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballem.curso.boot.domain.CategoriaProblema;
import com.mballem.curso.boot.domain.Solicitacao;
import com.mballem.curso.boot.domain.StatusSolicitacao;
import com.mballem.curso.boot.domain.TipoServico;
import com.mballem.curso.boot.service.CategoriaProblemaService;
import com.mballem.curso.boot.service.SolicitacaoService;

import java.io.ObjectInputFilter.Status;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private final CategoriaProblemaService categoriaService;
    private final SolicitacaoService solicitacaoService;

    @Autowired
    public SolicitacaoController(CategoriaProblemaService categoriaService, SolicitacaoService solicitacaoService) {
        this.categoriaService = categoriaService;
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping("/pedido")
    public String mostrarFormularioPedido(
            @RequestParam(name = "address", required = false) String endereco,
            @RequestParam(name = "lat", required = false) Double latitude,
            @RequestParam(name = "lng", required = false) Double longitude,
            @RequestParam(name = "serviceType", required = false) String tipoServico,
            Model model) {
        
        // Buscar categorias do banco de dados
        List<CategoriaProblema> categorias = categoriaService.buscarTodos();
        model.addAttribute("categorias", categorias);
        
        // Adicionar parâmetros de localização ao modelo
        if (endereco != null) {
            model.addAttribute("enderecoFormatado", endereco);
        } else if (latitude != null && longitude != null) {
            model.addAttribute("coordenadas", String.format("%f, %f", latitude, longitude));
        }
        
        // Adicionar tipo de serviço se foi especificado
        if (tipoServico != null) {
            model.addAttribute("tipoServicoPreSelecionado", tipoServico);
        }
        
        return "solicitacao/pedido";
    }

    
    
    
    
    
    
    
    
    
    
//    @PostMapping("/processar")
//    public String processarSolicitacao(
//            @RequestParam String nomeCondutor,
//            @RequestParam String telefoneCondutor,
//            @RequestParam String endereco,
//            @RequestParam String descricao,
//            @RequestParam String tipoServico,
//            @RequestParam(value = "categorias", required = false) List<Long> categoriasIds,
//            RedirectAttributes redirectAttributes) {
    
    @PostMapping("/processar")
    public String processarSolicitacao(
            @RequestParam String nomeCondutor,
            @RequestParam String telefoneCondutor,
            @RequestParam String endereco,
            @RequestParam String descricao,
            @RequestParam String tipoServico,
            @RequestParam(value = "categorias", required = false) List<Long> categoriasIds,
            RedirectAttributes redirectAttributes) {

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setNomeCondutor(nomeCondutor);
        solicitacao.setTelefoneCondutor(telefoneCondutor);
        solicitacao.setEndereco(endereco);
        solicitacao.setDescricao(descricao);
        
        // Converter tipoServico para Enum
        solicitacao.setTipoServico(TipoServico.valueOf(tipoServico.toUpperCase()));
        
        // Definir status
        solicitacao.setStatus(StatusSolicitacao.EM_PROGRESO);
        
        // Processar categorias
        if (categoriasIds != null && !categoriasIds.isEmpty()) {
            Set<Long> idsSet = new HashSet<>(categoriasIds);
            List<CategoriaProblema> categorias = categoriaService.buscarTodosPorIds(idsSet);
            solicitacao.setCategorias(new HashSet<>(categorias));
        }

        Solicitacao novaSolicitacao = solicitacaoService.salvar(solicitacao);
        
        redirectAttributes.addFlashAttribute("success", 
                "Solicitação #" + novaSolicitacao.getId() + " enviada com sucesso!");
        
       // return "redirect:/solicitacao/confirmacao";
        
        return "redirect:/solicitacao/acompanhar/" + novaSolicitacao.getId();
    }
    @GetMapping("/acompanhar/{id}")
    public String mostrarAcompanhamento(@PathVariable("id") Long id, Model model) {
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);
        
        if (solicitacao == null) {
            throw new RuntimeException("Solicitação não encontrada com ID: " + id);
        }
        
        model.addAttribute("solicitacao", solicitacao);
        
        // Você pode adicionar mais lógica aqui para buscar informações do prestador, etc.
        
        return "solicitacao/acompanhamento";
    }

//    @GetMapping("/confirmacao")
//    public String mostrarConfirmacao() {
//        return "solicitacao/confirmacao";
//    }
}