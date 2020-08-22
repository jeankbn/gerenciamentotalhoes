package br.com.comic.controller.dto;

import java.math.BigDecimal;

import br.com.comic.domain.TipoProducao;

public class ProducaoDto {

	
	private String descricao;
	private TipoProducao tipoProducao;
	private BigDecimal quantidadeProducao;
	private String idTalhao;
	private String idFazenda;
	private String id;
		
	public String getIdTalhao() {
		return idTalhao;
	}
	public void setIdTalhao(String idTalhao) {
		this.idTalhao = idTalhao;
	}
	public String getIdFazenda() {
		return idFazenda;
	}
	public void setIdFazenda(String idFazenda) {
		this.idFazenda = idFazenda;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoProducao getTipoProducao() {
		return tipoProducao;
	}
	public void setTipoProducao(TipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}
	public BigDecimal getQuantidadeProducao() {
		return quantidadeProducao;
	}
	public void setQuantidadeProducao(BigDecimal quatnidadeProducao) {
		this.quantidadeProducao = quatnidadeProducao;
	}
	
	
}
