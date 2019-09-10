package com.assignment.recipe.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController{

    private static final String API_LINKS = "apiLinks";
    private static final String FRAGMENT = "fragment";
    private static final String PAGE_LINKS = "pageLinks";
    private static final String LAYOUT_DEFAULT = "layout/default";
    private static final String TITLE = "title";

    @GetMapping({"/", "/index", "/home"})
    public String showHome(Model model) {

        model.addAttribute(TITLE, "Home Page");
        model.addAttribute(PAGE_LINKS, generatePageLinks());
        model.addAttribute(API_LINKS, generateAPILinks());
        model.addAttribute(FRAGMENT, "main/home");

        return LAYOUT_DEFAULT;
    }


    @GetMapping("/pages")
    public String showPagesLinks(Model model) {
    	
    	model.addAttribute(TITLE, "Page links");
        model.addAttribute(PAGE_LINKS, generatePageLinks());
        model.addAttribute(FRAGMENT, "main/pages");

        return LAYOUT_DEFAULT;
    }
    
    @GetMapping("/api")
    public String showAPILinks(Model model) {
    	
    	model.addAttribute(TITLE, "API links");
        model.addAttribute(API_LINKS, generateAPILinks());
        model.addAttribute(FRAGMENT, "main/api");

    	return LAYOUT_DEFAULT;
    }
    
    private Map<String, String> generatePageLinks() {
    	
    	Map<String, String> pageLinks = new HashMap<>();
    	pageLinks.put("home page", "/home");
    	pageLinks.put("potatos page", "/potatos");
        pageLinks.put("tomatos page", "/tomatos");
        
    	return pageLinks;
    }
    
    private Map<String, String> generateAPILinks() {
    	
    	Map<String, String> apiLinks = new HashMap<>();
    	String baseAPIUrl = "/api";
    	
    	apiLinks.put("potatos api link", baseAPIUrl + "/potatos");
    	apiLinks.put("tomatos api link", baseAPIUrl + "/tomatos");
    	return apiLinks;
    }
}

