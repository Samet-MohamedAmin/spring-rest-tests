package com.assignment.recipe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Potato implements BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = 0L;
	
	private String name;

    private String color;

	@Builder
	@lombok.Generated
	public Potato(String name, String color) {
		this.name = name;
		this.color = color;
	}
}

