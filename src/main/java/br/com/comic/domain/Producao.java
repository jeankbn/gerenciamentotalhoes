package br.com.comic.domain;

import java.math.BigDecimal;

public class Producao {

	private String descricao;
	private TipoProducao tipoProducao;
	private BigDecimal quantidadeProducao;
	private String id;
	
	
	
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
