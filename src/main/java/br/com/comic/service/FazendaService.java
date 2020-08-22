package br.com.comic.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.comic.controller.dto.FazendaDto;
import br.com.comic.controller.dto.TotalProdutividadeDto;
import br.com.comic.domain.Fazenda;
import br.com.comic.domain.Producao;
import br.com.comic.domain.exception.ApiError;
import br.com.comic.repository.FazendaRepository;

@Service
public class FazendaService {

	private FazendaRepository repository;
	
	private ModelMapper modelMapper;
	
	
	@Autowired
	public FazendaService (FazendaRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	
	
	public void salvar(FazendaDto dto) {
		Fazenda fazenda = this.modelMapper.map(dto, Fazenda.class);
		this.repository.save(fazenda);
	}
	
	public  List<FazendaDto> buscarTodos(){
		List<FazendaDto> lista = new ArrayList<FazendaDto>();
		this.repository.findAll().forEach(fazenda -> {
			lista.add(this.modelMapper.map(fazenda, FazendaDto.class));
		});
		return lista;
	}
	
	
	public void alterarDescricao(FazendaDto dto) {

		Optional<Fazenda> fazenda = this.repository.findById(dto.getId());
		if(fazenda.isPresent()) {
			fazenda.get().setDescricao(dto.getDescricao());
			this.repository.save(fazenda.get());
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("A fazenda %s não foi encontrada", dto.getId() ), "");
		}

	}
	
	
	public FazendaDto obterPorId(String id) {
		Optional<Fazenda> fazenda = this.repository.findById(id);
		
		if (fazenda.isPresent()) {
			return this.modelMapper.map(fazenda.get(), FazendaDto.class);
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", id ), "");
		}
	}
	
	
	public  void excluir(String id) {
		
		Optional<Fazenda> fazenda = this.repository.findById(id);
		
		if (fazenda.isPresent()) {
			this.repository.delete(fazenda.get());
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", id ), "");
		}
	}


	public TotalProdutividadeDto obterProdutividade(String id) {
	Optional<Fazenda> fazenda = this.repository.findById(id);
	TotalProdutividadeDto dto = new TotalProdutividadeDto();
	dto.setTotalProtividade(BigDecimal.ZERO);
		if (fazenda.isPresent()) {
			
			
			fazenda.get().getTalhoes().forEach(talhao->{
				
				if(talhao.getProducoes() != null) {
				
				talhao.getProducoes().forEach(producao -> {
					
					if(producao.getQuantidadeProducao()!=null) {
						dto.setTotalProtividade(dto.getTotalProtividade().add(producao.getQuantidadeProducao()));
					}
					
				});
				
				}
			});
						
			dto.setTotalProtividade(dto.getTotalProtividade().setScale(2,RoundingMode.HALF_UP));
			return dto;
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", id ), "");
		}
	}
	
	
}

