package br.com.vr.miniautorizador.repository;

import org.springframework.stereotype.Repository;

import br.com.vr.miniautorizador.model.Cartao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, String>{

	default <T> Optional<T> findProjectedByNumeroCartao(String id, Class<T> projection) {
        return null;
    }
}
