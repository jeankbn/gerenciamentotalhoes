package br.com.comic.domain;


import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fazenda")
public class Fazenda {

	@Id
	private String id;
	
	private String descricao;
	
	
	private List<Talhao> talhoes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String desricao) {
		this.descricao = desricao;
	}

	public List<Talhao> getTalhoes() {
		return talhoes;
	}

	public void setTalhoes(List<Talhao> talhoes) {
		this.talhoes = talhoes;
	}
	
	
	
	
	
	
}
