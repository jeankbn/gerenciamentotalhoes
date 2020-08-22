package br.com.comic.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.comic.domain.Producao;

public class TalhaoDto {
	
	private String idFazenda;
	private String descricao;
	private BigDecimal area;
	private List<Producao> producoes;
	private String id;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdFazenda() {
		return idFazenda;
	}
	public void setIdFazenda(String idFazenda) {
		this.idFazenda = idFazenda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String desricao) {
		this.descricao = desricao;
	}
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public List<Producao> getProducoes() {
		return producoes;
	}
	public void setProducoes(List<Producao> producoes) {
		this.producoes = producoes;
	}
	
	
}
