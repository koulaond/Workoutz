package com.ondrejkoula.repository;

import com.ondrejkoula.domain.SetsAndRepetitions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SetsAndRepetitionsRepository extends MongoRepository<SetsAndRepetitions, UUID> {
}
