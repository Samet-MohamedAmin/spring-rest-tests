package com.assignment.recipe.bootstrap;

import com.assignment.recipe.models.Potato;
import com.assignment.recipe.models.Tomato;
import com.assignment.recipe.services.PotatoService;
import com.assignment.recipe.services.TomatoService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

	private final PotatoService potatoService;
	private final TomatoService tomatoService;

	@Autowired
	public DataLoader(PotatoService potatoService, TomatoService tomatoService) {

		this.potatoService = potatoService;
		this.tomatoService = tomatoService;
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("loading data");

		if (potatoService.findAll().isEmpty()) loadPotatoData();

		if (tomatoService.findAll().isEmpty()) loadTomatoData();

		log.info("data loaded");

	}

	private Set<Potato> generatePotatoList() {

		var potatoList = new HashSet<Potato>();
		potatoList.add(Potato.builder().name("p1").color("yellow").build());
		potatoList.add(Potato.builder().name("p2").color("orange").build());
		potatoList.add(Potato.builder().name("p3").color("brown").build());

		return potatoList;
	}

	private Set<Tomato> generateTomatoList() {

		var tomatoList = new HashSet<Tomato>();
		tomatoList.add(Tomato.builder().name("t1").color("red").build());
		tomatoList.add(Tomato.builder().name("t2").color("red").build());
		tomatoList.add(Tomato.builder().name("t3").color("red").build());
		tomatoList.add(Tomato.builder().name("t4").color("red").build());

		return tomatoList;
	}

	private String prettifyListLog(String name, Set<?> list) {

		String msg = list.stream()
							.map(Object::toString)
							.reduce((a, b) -> a+ "\n\t" + b)
							.orElseGet(() -> "EMPTY LIST");

		return name + "\n\t" + msg;
	}

	private void loadPotatoData() {

		potatoService.saveAll(generatePotatoList());
		
		log.info(prettifyListLog("potato list:", potatoService.findAll()));
		log.info("potatos loaded");
	}

	private void loadTomatoData() {

		tomatoService.saveAll(generateTomatoList());
		
		log.info(prettifyListLog("tomato list:", tomatoService.findAll()));
		log.info("tomatos loaded");
	}
}
