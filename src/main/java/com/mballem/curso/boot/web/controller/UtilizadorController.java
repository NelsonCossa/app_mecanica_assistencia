package com.mballem.curso.boot.web.controller;

import com.mballem.curso.boot.domain.Especialidade;
import com.mballem.curso.boot.domain.TipoUtilizador;
import com.mballem.curso.boot.domain.Utilizador;
import com.mballem.curso.boot.service.EspecialidadeService;
import com.mballem.curso.boot.service.UtilizadorService;

import ch.qos.logback.core.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/utilizadores")
public class UtilizadorController {
	
	
	
	

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private EspecialidadeService especialidadeService;

    // Diretório onde as fotos serão armazenadas
    @Value("${upload.dir}")
    private String uploadDir;

    // Exibe o formulário de cadastro
    @GetMapping("/cadastrar")
    public String cadastrar(Utilizador utilizador, ModelMap model) {
    	  model.addAttribute("especialidades", especialidadeService.buscarTodos());
    	    model.addAttribute("especialidadesSelecionadas", Collections.emptySet());
        return "utilizadores/cadastro";
    }

    // Processa o formulário de cadastro
    @PostMapping("/salvar")
    public String salvar(
            Utilizador utilizador,
            @RequestParam(value = "especialidadeIds", required = false) List<Long> especialidadeIds,
            @RequestParam("foto") MultipartFile arquivo,
            @RequestParam("senha") String senha, // Adicionamos este parâmetro
            RedirectAttributes attr) throws IOException {
        
        // Configura o tipo de utilizador
        utilizador.setTipo(TipoUtilizador.PRESTADOR);
        
        // Define a senha apenas se foi fornecida (para criação)
        utilizador.setSenha(senha);
        // Processa a foto se foi enviada
        if (!arquivo.isEmpty()) {
            // Cria o diretório de uploads caso não exista
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            // Gera um nome único para o arquivo
            String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
            Path caminhoArquivo = Paths.get(uploadDir, nomeArquivo);

            // Salva o arquivo no diretório
            arquivo.transferTo(caminhoArquivo.toFile());

            // Armazena o caminho do arquivo no banco de dados (não Base64)
            utilizador.setFotoUrl(nomeArquivo);  // Armazena o nome do arquivo, não o conteúdo
        }
        
        // Associa as especialidades selecionadas
        if (especialidadeIds != null && !especialidadeIds.isEmpty()) {
            Set<Especialidade> especialidades = especialidadeIds.stream()
                    .map(id -> especialidadeService.buscarPorId(id))
                    .collect(Collectors.toSet());
            utilizador.setEspecialidades(especialidades);
        }
        
        utilizadorService.salvar(utilizador);
        attr.addFlashAttribute("mensagem", "Utilizador cadastrado com sucesso!"); 
        
        return "redirect:/utilizadores/listar";
    }
    
    
    
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        Utilizador utilizador = utilizadorService.buscarPorId(id);
        model.addAttribute("utilizador", utilizador);
        model.addAttribute("especialidades", especialidadeService.buscarTodos());
        
        // Carrega as especialidades selecionadas para marcar no formulário
        Set<Long> especialidadesSelecionadas = utilizador.getEspecialidades()
                .stream()
                .map(Especialidade::getId)
                .collect(Collectors.toSet());
        model.addAttribute("especialidadesSelecionadas", especialidadesSelecionadas);
        
        return "utilizadores/cadastro";
    }

    @PostMapping("/editar")
    public String editar(
            Utilizador utilizador,
            @RequestParam(value = "especialidadeIds", required = false) List<Long> especialidadeIds,
            @RequestParam(value = "foto", required = false) MultipartFile arquivo,
            RedirectAttributes attr) throws IOException {
        
        // Busca o utilizador existente para manter os campos que não devem ser alterados
        Utilizador utilizadorExistente = utilizadorService.buscarPorId(utilizador.getId());
        
        // Mantém os campos que não devem ser alterados
        utilizador.setSenha(utilizadorExistente.getSenha());
        utilizador.setTipo(utilizadorExistente.getTipo());
        utilizador.setEstado(utilizadorExistente.getEstado());
        
        // Processa a foto se foi enviada uma nova
        if (arquivo != null && !arquivo.isEmpty()) {
            // Remove a foto antiga se existir
            if (utilizadorExistente.getFotoUrl() != null && !utilizadorExistente.getFotoUrl().isEmpty()) {
                Path caminhoArquivoAntigo = Paths.get(uploadDir, utilizadorExistente.getFotoUrl());
                Files.deleteIfExists(caminhoArquivoAntigo);
            }
            
            // Salva a nova foto
            String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
            Path caminhoArquivo = Paths.get(uploadDir, nomeArquivo);
            arquivo.transferTo(caminhoArquivo.toFile());
            utilizador.setFotoUrl(nomeArquivo);
        } else {
            // Mantém a foto existente se não foi enviada uma nova
            utilizador.setFotoUrl(utilizadorExistente.getFotoUrl());
        }
        
        // Atualiza as especialidades
        if (especialidadeIds != null) {
            Set<Especialidade> especialidades = especialidadeIds.stream()
                    .map(id -> especialidadeService.buscarPorId(id))
                    .collect(Collectors.toSet());
            utilizador.setEspecialidades(especialidades);
        } else {
            utilizador.setEspecialidades(Collections.emptySet());
        }
        
        utilizadorService.editar(utilizador);
        attr.addFlashAttribute("mensagem", "Utilizador editado com sucesso!");
        return "redirect:/utilizadores/listar";
    }
    
    @GetMapping("/excluir")
    public String excluir(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
        // Chama o método excluir do service
        utilizadorService.excluir(id);  // Não há necessidade de capturar o retorno, pois o método é void
        redirectAttributes.addFlashAttribute("mensagem", "Utilizador removido com sucesso");
        // Retorna para a lista de utilizadores ou outra página desejada
        return "redirect:/utilizadores/listar";  // Redireciona para a página onde lista os utilizadores
    }



    
    

    // Lista todos os utilizadores
    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("utilizadores", utilizadorService.buscarTodos());
        return "utilizadores/lista";
    }
}