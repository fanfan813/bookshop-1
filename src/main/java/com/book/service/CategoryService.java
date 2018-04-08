package com.book.service;

import java.util.List;

import com.book.domain.Category;

public interface CategoryService {

	List<Category> query(Category category);

	Category findById(String cid);
	
}
