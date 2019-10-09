package com.udacityjavand.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacityjavand.graphql.entity.Dog;
import com.udacityjavand.graphql.exception.BreedNotFoundException;
import com.udacityjavand.graphql.exception.DogNotFoundException;
import com.udacityjavand.graphql.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        // Loop through all dogs to check their breed
        for (Dog d : allDogs) {
            if (d.getBreed().equals(breed)) {
                // Delete if the breed is found
                dogRepository.delete(d);
                deleted = true;
            }
        }
        if (!deleted) {
            throw new BreedNotFoundException("Breed not founded", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not founded", id);
        }
    }
}
