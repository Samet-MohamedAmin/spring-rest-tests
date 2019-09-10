package com.assignment.recipe.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);
    
    Set<T> saveAll(Set<T> objectList);

    void delete(T object);

    void deleteById(ID id);
}

