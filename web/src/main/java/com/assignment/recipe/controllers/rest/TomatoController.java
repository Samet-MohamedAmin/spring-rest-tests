package com.assignment.recipe.controllers.rest;

import java.util.Set;

import com.assignment.recipe.models.Tomato;
import com.assignment.recipe.services.TomatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tomatos")
public class TomatoController{

    private TomatoService tomatoService;

    @Autowired
    TomatoController(TomatoService tomatoService) {
        this.tomatoService = tomatoService;
    }

    @GetMapping
    public Set<Tomato> findAll() {

        return tomatoService.findAll();
    }

    @GetMapping("/{petId}")
    public Tomato findById(@PathVariable Long petId) {

        return tomatoService.findById(petId);
    }
}

