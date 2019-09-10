package com.assignment.recipe.services.map;

import java.util.*;
import com.assignment.recipe.models.BaseEntity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MapService<T extends BaseEntity, ID> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){

        return new HashSet<>(map.values());
    }

    T findById(ID id) {

        return map.get(id);
    }

    T save(T object){

        if(object != null) {
            if(object.getId() == null || object.getId() == 0) {
                object.setId(getNextId());
            }

            map.put(object.getId(), object);
        } else {
            throw new NullPointerException();
        }

        return object;
    }
    
    Set<T> saveAll(Set<T> objectList) {
        
        var theObjectList = new HashSet<T>();
        for (T object : objectList) {
            theObjectList.add(save(object));
        }
    	return theObjectList;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){

        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }
}

