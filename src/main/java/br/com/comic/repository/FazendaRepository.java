package br.com.comic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.comic.domain.Fazenda;

@Repository
public interface FazendaRepository extends MongoRepository<Fazenda, String>{
	
}
