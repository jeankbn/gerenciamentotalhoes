package br.com.comic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.comic.controller.dto.ProducaoDto;
import br.com.comic.controller.dto.TalhaoDto;
import br.com.comic.service.ProducaoService;
import br.com.comic.service.TalhaoService;

@RestController
@RequestMapping("/producao")
public class ProducaoController {

	private ProducaoService service;

	@Autowired
	public ProducaoController(ProducaoService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void salvar(@RequestBody ProducaoDto producao) {
		this.service.salvar(producao);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void alterar(@RequestBody ProducaoDto producao) {
		this.service.alterar(producao);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void escluir(@RequestBody ProducaoDto producao) {
		this.service.excluir(producao);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idFazenda}/{idTalhao}/{idProducao}")
	public ProducaoDto obterPorId(@PathVariable("idFazenda") String idFazenda,@PathVariable("idTalhao") String idTalhao,@PathVariable("idProducao") String idProducao) {
		
		 ProducaoDto producao = new ProducaoDto();
		 producao.setIdFazenda(idFazenda);
		 producao.setId(idTalhao);		
		 producao.setId(idProducao);
		 
		return this.service.obterPorId(producao);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idFazenda}/{idTalhao}")
	public List<ProducaoDto> obterTodos(@PathVariable("idFazenda") String idFazenda,@PathVariable("idTalhao") String idTalhao) {
		 ProducaoDto producao = new ProducaoDto();
		 producao.setIdFazenda(idFazenda);
		 producao.setIdTalhao(idTalhao);		
		return this.service.buscarTodos(producao);
	}
	
	
}
