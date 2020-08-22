package br.com.comic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.comic.controller.dto.ProducaoDto;
import br.com.comic.domain.Fazenda;
import br.com.comic.domain.Producao;
import br.com.comic.domain.Talhao;
import br.com.comic.domain.exception.ApiError;
import br.com.comic.repository.FazendaRepository;

@Service
public class ProducaoService {

	private FazendaRepository repository;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public ProducaoService (FazendaRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	
	
	public void salvar(ProducaoDto dto) {
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
		
		if (fazenda.isPresent()) {
			
			Optional<Talhao> talhao = fazenda.get().getTalhoes().stream().filter(item->item.getId().equals(dto.getIdTalhao())).findFirst();
			
			if(talhao.isPresent()) {

				Producao producao = this.modelMapper.map(dto, Producao.class);
				producao.setId(UUID.randomUUID().toString().replace("-", ""));
				
				if(talhao.get().getProducoes() == null) {
					talhao.get().setProducoes(new ArrayList<Producao>());
				}
				
				talhao.get().getProducoes().add(producao);
				this.repository.save(fazenda.get());
				
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada talhao com código %s na fazenda com código %s ",dto.getIdTalhao(), dto.getIdFazenda() ), "");
			}
			
			
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
		
	}
	
	public List<ProducaoDto> buscarTodos(ProducaoDto dto){
		
		if (dto.getIdTalhao() == null ||
				dto.getIdFazenda() == null) {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Deve ser informado o campo [idTalhao] e [idFazenda] para busca de producoes "), "");
		}
				
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
		List<ProducaoDto> producoes = new ArrayList<ProducaoDto>();
				
		if (fazenda.isPresent()) {
			
			Optional<Talhao> talhao = fazenda.get().getTalhoes().stream().filter(item->item.getId().equals(dto.getIdTalhao())).findFirst();
			
			if(talhao.isPresent()) {

				talhao.get().getProducoes().forEach(item->{
					
					ProducaoDto prod = this.modelMapper.map(item, ProducaoDto.class);
					prod.setIdFazenda(dto.getIdFazenda());
					prod.setIdTalhao(prod.getIdTalhao());
					producoes.add(prod);
				});
				
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada talhao com código %s na fazenda com código %s ",dto.getIdTalhao(), dto.getIdFazenda() ), "");
			}
			
			
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
		
		return producoes;
	}
	
	
	public void alterar(ProducaoDto dto) {

		if (dto.getIdTalhao() == null ||
				dto.getIdFazenda() == null) {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Deve ser informado o campo [idTalhao] e [idFazenda] para alterar produção "), "");
		}
	
		
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
			
		if (fazenda.isPresent()) {
			
			Optional<Talhao> talhao = fazenda.get().getTalhoes().stream().filter(item->item.getId().equals(dto.getIdTalhao())).findFirst();
			
			if(talhao.isPresent()) {
				
				Optional<Producao> producao = talhao.get().getProducoes().stream().filter(item->item.getId().equals(dto.getId())).findFirst();

				if(producao.isPresent()) {

					producao.get().setDescricao(dto.getDescricao());
					producao.get().setQuantidadeProducao(dto.getQuantidadeProducao());
					producao.get().setTipoProducao(dto.getTipoProducao());
					
					this.repository.save(fazenda.get());
					
				}else {
					throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada producao de %s no talhao com código %s na fazenda com código %s ",dto.getDescricao(), dto.getIdTalhao(), dto.getIdFazenda() ), "");
				}
								
				
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada talhao com código %s na fazenda com código %s ",dto.getIdTalhao(), dto.getIdFazenda() ), "");
			}
			
			
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}

	}
	
	public ProducaoDto obterPorId(ProducaoDto dto) {
	
		if (dto.getIdTalhao() == null ||
				dto.getIdFazenda() == null) {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Deve ser informado o campo [idTalhao] e [idFazenda] para obter produção "), "");
		}
	
		
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
			
		if (fazenda.isPresent()) {
			
			Optional<Talhao> talhao = fazenda.get().getTalhoes().stream().filter(item->item.getId().equals(dto.getIdTalhao())).findFirst();
			
			if(talhao.isPresent()) {
				
				Optional<Producao> producao = talhao.get().getProducoes().stream().filter(item->item.getId().equals(dto.getId())).findFirst();

				if(producao.isPresent()) {
					ProducaoDto prod = this.modelMapper.map(producao, ProducaoDto.class);
					prod.setIdFazenda(dto.getIdFazenda());
					prod.setIdTalhao(prod.getIdTalhao());
					
					return prod;
				}else {
					throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada producao de %s no talhao com código %s na fazenda com código %s ",dto.getDescricao(), dto.getIdTalhao(), dto.getIdFazenda() ), "");
				}
								
				
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada talhao com código %s na fazenda com código %s ",dto.getIdTalhao(), dto.getIdFazenda() ), "");
			}
			
			
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}
		
	}
	
	
	public void excluir(ProducaoDto dto) {

		if (dto.getIdTalhao() == null ||
				dto.getIdFazenda() == null) {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Deve ser informado o campo [idTalhao] e [idFazenda] para excluir produção "), "");
		}
	
		
		Optional<Fazenda> fazenda = this.repository.findById(dto.getIdFazenda());
			
		if (fazenda.isPresent()) {
			Optional<Talhao> talhao = fazenda.get().getTalhoes().stream().filter(item->item.getId().equals(dto.getIdTalhao())).findFirst();
			
			if(talhao.isPresent()) {
				talhao.get().getProducoes().removeIf(item->item.equals(dto.getId()));
				this.repository.save(fazenda.get());
				
			}else {
				throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não foi encontrada talhao com código %s na fazenda com código %s ",dto.getIdTalhao(), dto.getIdFazenda() ), "");
			}
			
			
		}else {
			throw new ApiError(HttpStatus.BAD_REQUEST,String.format("Não coi encontrada fazenda com código %s ", dto.getIdFazenda() ), "");
		}

	}
	
	
	
}

