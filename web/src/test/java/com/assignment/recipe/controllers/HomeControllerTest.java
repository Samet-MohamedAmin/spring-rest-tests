package com.assignment.recipe.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.isIn;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

	private static final String FRAGMENT = "fragment";
	private static final String API_LINKS = "apiLinks";
	private static final String PAGE_LINKS = "pageLinks";
	private static final String TITLE = "title";
	private static final String LAYOUT_DEFAULT = "layout/default";

	@InjectMocks
	private HomeController controller;

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

	@Test
	public void testMockMvcHome() throws Exception {

		String path = "/";
		String viewName = LAYOUT_DEFAULT;

		mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(view().name(isIn(getExpectedViewNameList(viewName))))

			.andExpect(model().attributeExists(TITLE))
			.andExpect(model().attributeExists(PAGE_LINKS))
			.andExpect(model().attributeExists(API_LINKS))
			.andExpect(model().attributeExists(FRAGMENT));
	}

	@Test
	public void testMockMvcPages() throws Exception {

		String path = "/pages";
		String viewName = LAYOUT_DEFAULT;

		mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(view().name(isIn(getExpectedViewNameList(viewName))))

			.andExpect(model().attributeExists(TITLE))
			.andExpect(model().attributeExists(PAGE_LINKS))
			.andExpect(model().attributeExists(FRAGMENT));
	}

	@Test
	public void testMockMvcApi() throws Exception {
		
		String path = "/api";
		String viewName = LAYOUT_DEFAULT;

		mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(view().name(isIn(getExpectedViewNameList(viewName))))

			.andExpect(model().attributeExists(TITLE))
			.andExpect(model().attributeExists(API_LINKS))
			.andExpect(model().attributeExists(FRAGMENT));
	}

}
