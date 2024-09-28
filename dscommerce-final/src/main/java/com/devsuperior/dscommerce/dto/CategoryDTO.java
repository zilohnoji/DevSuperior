package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Category;

import java.util.Collection;
import java.util.List;

public class CategoryDTO  {

	private Long id;
	private String name;
	
	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
