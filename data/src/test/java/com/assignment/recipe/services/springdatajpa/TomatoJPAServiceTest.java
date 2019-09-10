package com.assignment.recipe.services.springdatajpa;

import java.util.Set;

import com.assignment.recipe.models.Tomato;
import com.assignment.recipe.repositories.TomatoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TomatoJPAServiceTest {

		
	@Mock
	private TomatoRepository repository;

	@InjectMocks
	private TomatoJPAService service;

	@InjectMocks
	private ObjectJPAServiceHelper<Tomato> testService;
	
	private Set<Tomato> entities = null;

	private final int listSize = 3;

	@BeforeEach
	public void setUp() throws Exception {

		this.entities = testService.generateObjectList(listSize, Tomato.class);
	}


	@Test
	public void testFindAll() throws Exception {

		testService.testFindAll(repository, service, entities);
	}

	@Test
	public void testFindById() throws Exception {

		testService.testFindById(repository, service, entities);
	}

	@Test
	public void testSave() throws Exception {

		testService.testSave(repository, service, entities);
	}

	@Test
	public void testSaveAll() throws Exception {

		testService.testSaveAll(repository, service, entities);
	}

	@Test
	public void testDelete() throws Exception {

		testService.testDelete(repository, service, entities);
	}

	@Test
	public void testDeleteById() throws Exception {

		testService.testDeleteById(repository, service, entities);
	}

}
