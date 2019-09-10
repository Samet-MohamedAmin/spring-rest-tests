package com.assignment.recipe.bootstrap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import com.assignment.recipe.models.Potato;
import com.assignment.recipe.models.Tomato;
import com.assignment.recipe.services.PotatoService;
import com.assignment.recipe.services.TomatoService;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

	@Mock
	private PotatoService potatoService;
	
	@Mock
	private TomatoService tomatoService;

	@InjectMocks
	private DataLoader dataLoader;

	@Captor
	ArgumentCaptor<Set<Potato>> potatoArgCaptor;

	@Captor
	ArgumentCaptor<Set<Tomato>> tomatoArgCaptor;

	@BeforeEach
	public void setUp() throws Exception {
	}
	
	final int minSize = 0;
	
	@Test
	public void testRunPotatosEmpty() throws Exception {

		when(potatoService.findAll()).thenReturn(Set.<Potato>of());

		dataLoader.run();

		verify(potatoService, atLeastOnce()).saveAll(potatoArgCaptor.capture());
		
		var entities = potatoArgCaptor.getValue();
		assertThat(entities.size(), greaterThan(minSize));
	}

	@Test
	public void testRunPotatosNotEmpty() throws Exception {

		var initialEntities = Set.<Potato>of(Potato.builder().name("test potato").build());
		when(potatoService.findAll()).thenReturn(initialEntities);

		dataLoader.run();

		verify(potatoService, never()).saveAll(any());
		verify(potatoService, never()).save(any());
	}

	@Test
	public void testRunTomatosEmpty() throws Exception {

		when(tomatoService.findAll()).thenReturn(Set.<Tomato>of());

		dataLoader.run();

		verify(tomatoService, atLeastOnce()).saveAll(tomatoArgCaptor.capture());
		
		var entities = tomatoArgCaptor.getValue();
		assertThat(entities.size(), greaterThan(minSize));
	}

	@Test
	public void testRunTomatosNotEmpty() throws Exception {

		var initialEntities = Set.<Tomato>of(Tomato.builder().name("test tomato").build());
		when(tomatoService.findAll()).thenReturn(initialEntities);

		dataLoader.run();

		verify(tomatoService, never()).saveAll(any());
		verify(tomatoService, never()).save(any());
	}

}
