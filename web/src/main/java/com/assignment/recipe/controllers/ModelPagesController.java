package com.assignment.recipe.controllers;

import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.assignment.recipe.models.*;
import com.assignment.recipe.services.PotatoService;
import com.assignment.recipe.services.TomatoService;

@Controller
public class ModelPagesController {

	private static final String LAYOUT_DEFAULT = "layout/default";
	private PotatoService potatoService;
	private TomatoService tomatoService;

	@Autowired
	ModelPagesController(PotatoService potatoService, TomatoService tomatoService) {

		this.potatoService = potatoService;
		this.tomatoService = tomatoService;
	}

	private <T extends BaseEntity> List<T> getSortedItems(Set<T> objectList) {

		var sortedObjectList =  new ArrayList<T>(objectList);
		sortedObjectList.sort(Comparator.comparing(BaseEntity::getId));
		return sortedObjectList;
	}

	private Map<String, Function<Potato, ?>> getPotatosMapAttr() {

		var mapAttr = new LinkedHashMap<String, Function<Potato, ?>>();

		mapAttr.put("Id", Potato::getId);
		mapAttr.put("Name", Potato::getName);
		mapAttr.put("Color", Potato::getColor);

		return mapAttr;
	}

	private Map<String, Function<Tomato, ?>> getTomatosMapAttr() {

		var mapAttr = new LinkedHashMap<String, Function<Tomato, ?>>();

		mapAttr.put("Id", Tomato::getId);
		mapAttr.put("Name", Tomato::getName);
		mapAttr.put("Color", Tomato::getColor);

		return mapAttr;
	}

	private <T extends BaseEntity> void addModelAttributes(
			Model model,
			String title,
			Set<T> entities,
			Map<String, Function<T, ?>> mapAttr) {

		model.addAttribute("title", title);
		model.addAttribute("entities", getSortedItems(entities));
		model.addAttribute("mapAttr", mapAttr);

		model.addAttribute("fragment", "models/index");
	}

    @GetMapping("/potatos")
    public String showPotatosPage(Model model) {
		
		addModelAttributes(
			model,
			"Potatos Page",
			potatoService.findAll(),
			getPotatosMapAttr());

    	return LAYOUT_DEFAULT;
	}

	@GetMapping("/tomatos")
    public String showTomatosPage(Model model) {

		addModelAttributes(
			model,
			"Tomatos Page",
			tomatoService.findAll(),
			getTomatosMapAttr());

    	return LAYOUT_DEFAULT;
    }
}
