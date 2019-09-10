package com.assignment.recipe.controllers.rest;

import java.util.Set;

import com.assignment.recipe.models.Potato;
import com.assignment.recipe.services.PotatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/potatos")
public class PotatoController{

    private PotatoService potatoService;

    @Autowired
    PotatoController(PotatoService potatoService) {
        this.potatoService = potatoService;
    }

    @GetMapping
    public Set<Potato> findAll() {

        return potatoService.findAll();
    }

    @GetMapping("{petId}")
    public Potato findById(@PathVariable Long petId) {

        return potatoService.findById(petId);
    }
}

