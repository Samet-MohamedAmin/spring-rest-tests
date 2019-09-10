package com.assignment.recipe.services.map;

import java.util.Set;

import com.assignment.recipe.models.Potato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PotatoMapServiceTest {
	
	@Mock
	private MapService<Potato, Long> mapService;

	@InjectMocks
	private PotatoMapService potatoMapService;

	@InjectMocks
	private ObjectMapServiceHelper<Potato> testService;

	private Set<Potato> entities;

	private final int listSize = 3;

	@BeforeEach
	public void setUp() throws Exception {

		this.entities = testService.generateObjectList(listSize, Potato.class);
	}

	@Test
	public void testFindAll() throws Exception {

		testService.testFindAll(mapService, potatoMapService, entities);
	}

	@Test
	public void testFindById() throws Exception {

		testService.testFindById(mapService, potatoMapService, entities);
	}

	@Test
	public void testSave() throws Exception {

		testService.testSave(mapService, potatoMapService, entities);
	}

	@Test
	public void testSaveAll() throws Exception {

		testService.testSaveAll(mapService, potatoMapService, entities);
	}

	@Test
	public void testDelete() throws Exception {

		testService.testDelete(mapService, potatoMapService, entities);
	}

	@Test
	public void testDeleteById() throws Exception {

		testService.testDeleteById(mapService, potatoMapService, entities);
	}

}
