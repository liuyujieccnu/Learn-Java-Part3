package com.udacityjavand.graphql.repository;

import com.udacityjavand.graphql.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
