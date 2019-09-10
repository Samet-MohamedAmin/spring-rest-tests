package com.assignment.recipe.repositories;

import java.util.List;

import com.assignment.recipe.models.Potato;

import org.springframework.data.repository.CrudRepository;

public interface PotatoRepository extends CrudRepository<Potato, Long> {

    @Override
    List<Potato> findAll();

    @Override
    <T extends Potato> List<T> saveAll(Iterable<T> entities);
}

