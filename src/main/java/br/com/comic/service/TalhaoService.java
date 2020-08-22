package br.com.comic.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.comic.controller.dto.TalhaoDto;
import br.com.comic.controller.dto.TotalProdutividadeDto;
import br.com.comic.domain.Fazenda;
import br.com.comic.domain.Talhao;
import br.com.comic.domain.exception.ApiError;
import br.com.comic.repository.FazendaRepository;

@Service
public class TalhaoService {

	
	private FazendaRepository repository;
	
	private ModelMapper modelMapper;
	
	
	@Autowired
	public TalhaoService(FazendaRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
		
	}
	
		
	public void salvar(TalhaoDto dto) {
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
		
		if (fazenda.isPresent()) {
			Talhao talhao = this.modelMapper.map(dto,Talhao.class);
			talhao.setId(UUID.randomUUID().toString().replace("-", ""));
			
			if(fazenda.get().getTalhoes() == null) {
				fazenda.get().setTalhoes(new ArrayList<Talhao>());
			}
			
			fazenda.get().getTalhoes().add(talhao);
			this.repository.save(fazenda.get());
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
		
	}
	
	public List<TalhaoDto> buscarTodos(String idFazenda){
		
		Optional<Fazenda> fazenda = this.repository.findById(idFazenda);
		List<TalhaoDto> talhoes = new ArrayList<TalhaoDto>();
		
		
		if (fazenda.isPresent()) {
			fazenda.get().getTalhoes().forEach(talhao->{
				TalhaoDto dto =this.modelMapper.map(talhao,TalhaoDto.class);
				dto.setIdFazenda(idFazenda);
				talhoes.add(dto);
			});
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ",idFazenda ), "");
		}
		
		return talhoes;
	}
	
	
	public void alterarArea(TalhaoDto dto) {

		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
			
		if(fazenda.isPresent()) {			
			Optional<Talhao> talhao= fazenda.get().getTalhoes().stream().filter(item-> item.getId().equals(dto.getId())).findFirst();
			if(talhao.isPresent()) {
				talhao.get().setArea(dto.getArea());
				talhao.get().setDescricao(dto.getDescricao());
				this.repository.save(fazenda.get());
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("O talhão com código %s não foi encontrado para fazenda com código %s", dto.getId(), dto.getIdFazenda() ), "");
			}
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}

	}
	
	public TalhaoDto obterPorId(TalhaoDto dto) {
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
		
		if (fazenda.isPresent()) {
			
			Optional<Talhao> talhao= fazenda.get().getTalhoes().stream().filter(item-> item.getId().equals(dto.getId())).findFirst();
			if(talhao.isPresent()) {
				
				TalhaoDto dtoResponse =this.modelMapper.map(talhao,TalhaoDto.class);
				dtoResponse.setIdFazenda(dto.getIdFazenda());
				
				return dtoResponse;
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("O talhão com código %s ", dto.getId() ), "");
			}

		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
	}
	
	
	public void excluir(TalhaoDto dto) {

		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
		
		if (fazenda.isPresent()) {
			Optional<Talhao> talhao= fazenda.get().getTalhoes().stream().filter(item-> item.getId().equals(dto.getId())).findFirst();
			if(talhao.isPresent()) {
			
				fazenda.get().getTalhoes().removeIf(item -> item.getId().equals(dto.getId()));
				this.repository.save(fazenda.get());
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("O talhão com código %s ", dto.getId() ), "");
			}

		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
	}


	public TotalProdutividadeDto obterProdutividade(TalhaoDto dto) {
		
	Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
	TotalProdutividadeDto produticidadeDto = new TotalProdutividadeDto();
	produticidadeDto.setTotalProtividade(BigDecimal.ZERO);
		if (fazenda.isPresent()) {
			Optional<Talhao> talhao= fazenda.get().getTalhoes().stream().filter(item-> item.getId().equals(dto.getId())).findFirst();
			if(talhao.isPresent()) {
				if(talhao.get().getProducoes() != null) {
				talhao.get().getProducoes().forEach(producao -> {
					
					if(producao.getQuantidadeProducao()!=null) {
						produticidadeDto.setTotalProtividade(produticidadeDto.getTotalProtividade().add(producao.getQuantidadeProducao()));
					}				
				
				});
				}

				produticidadeDto.setTotalProtividade(produticidadeDto.getTotalProtividade().setScale(2,RoundingMode.HALF_UP));
				return produticidadeDto;
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("O talhão com código %s ", dto.getId() ), "");
			}

		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
	}
	
	
	
}
