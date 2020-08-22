package br.com.comic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.comic.controller.dto.TalhaoDto;
import br.com.comic.controller.dto.TotalProdutividadeDto;
import br.com.comic.service.TalhaoService;

@RestController
@RequestMapping("/talhao")
public class TalhaoController {
	
	private TalhaoService service;

	@Autowired
	public TalhaoController(TalhaoService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void salvar(@RequestBody TalhaoDto talhao) {
		this.service.salvar(talhao);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void alterar(@RequestBody TalhaoDto talhao) {
		this.service.alterarArea(talhao);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void escluir(@RequestBody TalhaoDto talhao) {
		this.service.excluir(talhao);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idFazenda}/{idTalhao}")
	public TalhaoDto obterPorId(@PathVariable("idFazenda") String idFazenda,@PathVariable("idTalhao") String idTalhao) {
		
		 TalhaoDto talhao = new TalhaoDto();
		 talhao.setIdFazenda(idFazenda);
		 talhao.setId(idTalhao);		
		
		return this.service.obterPorId(talhao);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idFazenda}")
	public List<TalhaoDto> obterTodos(@PathVariable("idFazenda") String idFazenda) {
		return this.service.buscarTodos(idFazenda);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "produtividade/{idFazenda}/{idTalhao}")
	public TotalProdutividadeDto obterProdutividade(@PathVariable("idFazenda") String idFazenda,@PathVariable("idTalhao") String idTalhao) {

		 TalhaoDto talhao = new TalhaoDto();
		 talhao.setIdFazenda(idFazenda);
		 talhao.setId(idTalhao);	
		return this.service.obterProdutividade(talhao);
	}
	
}
