package com.assignment.recipe.services.map;

import java.util.Set;

import com.assignment.recipe.models.Tomato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TomatoMapServiceTest {
	
	@Mock
	private MapService<Tomato, Long> mapService;

	@InjectMocks
	private TomatoMapService tomatoMapService;
	
	@InjectMocks
	private ObjectMapServiceHelper<Tomato> testService;

	private Set<Tomato> entities;

	private final int listSize = 3;

	@BeforeEach
	public void setUp() throws Exception {

		this.entities = testService.generateObjectList(listSize, Tomato.class);
	}

	@Test
	public void testFindAll() throws Exception {

		testService.testFindAll(mapService, tomatoMapService, entities);
	}

	@Test
	public void testFindById() throws Exception {

		testService.testFindById(mapService, tomatoMapService, entities);
	}

	@Test
	public void testSave() throws Exception {

		testService.testSave(mapService, tomatoMapService, entities);
	}

	@Test
	public void testSaveAll() throws Exception {

		testService.testSaveAll(mapService, tomatoMapService, entities);
	}

	@Test
	public void testDelete() throws Exception {

		testService.testDelete(mapService, tomatoMapService, entities);
	}

	@Test
	public void testDeleteById() throws Exception {

		testService.testDeleteById(mapService, tomatoMapService, entities);
	}

}
