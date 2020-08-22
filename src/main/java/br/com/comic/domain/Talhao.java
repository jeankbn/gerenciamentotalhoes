package br.com.comic.domain;

import java.math.BigDecimal;
import java.util.List;

public class Talhao {
	
	private String id;
	private String descricao;
	private BigDecimal area;
	private List<Producao> producoes;
			
	
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
