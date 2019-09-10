package com.assignment.recipe.services.springdatajpa;


import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.assignment.recipe.models.Tomato;
import com.assignment.recipe.repositories.TomatoRepository;
import com.assignment.recipe.services.TomatoService;



@Service
@Profile("springdatajpa")
public class TomatoJPAService implements TomatoService {

    private final TomatoRepository tomatoRepository;

    @Autowired
    public TomatoJPAService(TomatoRepository tomatoRepository) {

        this.tomatoRepository = tomatoRepository;
    }

    @Override
    public Set<Tomato> findAll() {
    	
        return Set.copyOf(tomatoRepository.findAll());
    }

    @Override
    public Tomato findById(Long aLong) {

        return tomatoRepository.findById(aLong).orElse(null);
    }

    @Override
    public Tomato save(Tomato object) {
    	
        return tomatoRepository.save(object);
    }

    @Override
    @Transactional
    public Set<Tomato> saveAll(Set<Tomato> objectList) {
    	
    	return Set.copyOf(tomatoRepository.saveAll(objectList));
    }
    
    @Override
    public void delete(Tomato object) {

        tomatoRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        tomatoRepository.deleteById(aLong);
    }
}

