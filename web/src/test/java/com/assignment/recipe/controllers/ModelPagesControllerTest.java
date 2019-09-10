package com.assignment.recipe.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
import java.util.List;

import com.assignment.recipe.services.PotatoService;
import com.assignment.recipe.services.TomatoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.isIn;


@ExtendWith(MockitoExtension.class)
public class ModelPagesControllerTest {

	private static final String FRAGMENT = "fragment";
	private static final String MAP_ATTR = "mapAttr";
	private static final String ENTITIES = "entities";
	private static final String TITLE = "title";
	private static final String LAYOUT_DEFAULT = "layout/default";

	@Mock
	PotatoService potatoService;

	@Mock
	TomatoService tomatoService;

	@InjectMocks
	private ModelPagesController controller;

	private MockMvc mockMvc;


	@BeforeEach
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private List<String> getExpectedViewNameList(String viewName) {

		var expectedViewNameList = new ArrayList<String>();
		var suffixList = List.of(".html");
		
		
		expectedViewNameList.add(viewName);
		suffixList.forEach(e -> expectedViewNameList.add(viewName + e));

		return expectedViewNameList;
	}


	private void testMockMvcEntityPage(String path) throws Exception {

		String viewName = LAYOUT_DEFAULT;

		mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(view().name(isIn(getExpectedViewNameList(viewName))))

			.andExpect(model().attributeExists(TITLE))
			.andExpect(model().attributeExists(ENTITIES))
			.andExpect(model().attributeExists(MAP_ATTR))
			.andExpect(model().attributeExists(FRAGMENT));
	}

	@Test
	public void testMockMvcPotatosPage() throws Exception {

		String path = "/potatos";
		testMockMvcEntityPage(path);
	}

	@Test
	public void testMockMvcTomatosPage() throws Exception {

		String path = "/tomatos";
		testMockMvcEntityPage(path);
	}
}
