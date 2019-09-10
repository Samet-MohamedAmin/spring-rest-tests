package com.assignment.recipe.services.map;

import java.util.Set;


import com.assignment.recipe.models.Potato;
import com.assignment.recipe.services.PotatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PotatoMapService implements PotatoService {

    private MapService<Potato, Long> mapService;

    @Autowired
    PotatoMapService(MapService<Potato, Long> mapService) {
        
        this.mapService = mapService;
    }

    @Override
    public Set<Potato> findAll() {
        
        return mapService.findAll();
    }

    @Override
    public Potato findById(Long id) {
        
        return mapService.findById(id);
    }

    @Override
    public Potato save(Potato object) {
        
        return mapService.save(object);
    }
    
    @Override
    public Set<Potato> saveAll(Set<Potato> objectList) {
        
        return mapService.saveAll(objectList);
    }

    @Override
    public void delete(Potato object) {
        
        mapService.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        
        mapService.deleteById(id);
    }
}
