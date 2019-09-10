package com.assignment.recipe.repositories;

import java.util.List;

import com.assignment.recipe.models.Tomato;

import org.springframework.data.repository.CrudRepository;

public interface TomatoRepository extends CrudRepository<Tomato, Long> {

    @Override
    List<Tomato> findAll();

    @Override
    <T extends Tomato> List<T> saveAll(Iterable<T> entities);
}

