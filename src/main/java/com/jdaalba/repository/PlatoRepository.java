package com.jdaalba.repository;

import com.jdaalba.entity.Plato;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlatoRepository extends MongoRepository<Plato, String> {

}
