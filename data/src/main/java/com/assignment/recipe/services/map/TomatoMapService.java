package com.assignment.recipe.services.map;

import java.util.Set;

import com.assignment.recipe.models.Tomato;
import com.assignment.recipe.services.TomatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class TomatoMapService implements TomatoService {

    private MapService<Tomato, Long> mapService;

    @Autowired
    TomatoMapService(MapService<Tomato, Long> mapService) {
        
        this.mapService = mapService;
    }
    
    @Override
    public Set<Tomato> findAll() {
        
        return mapService.findAll();
    }

    @Override
    public Tomato findById(Long id) {
        
        return mapService.findById(id);
    }

    @Override
    public Tomato save(Tomato object) {

        return mapService.save(object);
    }
    
    @Override
    public Set<Tomato> saveAll(Set<Tomato> objectList) {
    	
    	return mapService.saveAll(objectList);
    }
    
    @Override
    public void delete(Tomato object) {
        
        mapService.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        
        mapService.deleteById(id);
    }
}
