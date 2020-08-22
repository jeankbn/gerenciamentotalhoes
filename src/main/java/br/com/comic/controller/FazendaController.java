package br.com.comic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.comic.controller.dto.FazendaDto;
import br.com.comic.controller.dto.TotalProdutividadeDto;
import br.com.comic.service.FazendaService;

@RestController
@RequestMapping("/fazenda")
public class FazendaController {

	private FazendaService service;

	@Autowired
	public FazendaController(FazendaService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void salvar(@RequestBody FazendaDto fazenda) {
		this.service.salvar(fazenda);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void alterar(@RequestBody FazendaDto fazenda) {
		this.service.alterarDescricao(fazenda);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void escluir(@RequestBody FazendaDto fazenda) {
		this.service.excluir(fazenda.getId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public FazendaDto obterPorId(@PathVariable("id") String id) {
		return this.service.obterPorId(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<FazendaDto> obterTodos() {
		return this.service.buscarTodos();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "produtividade/{id}")
	public TotalProdutividadeDto obterProdutividade(@PathVariable("id") String id) {
		return this.service.obterProdutividade(id);
	}
	
}
