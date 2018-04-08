package com.book.dao;

import java.util.List;
import java.util.Map;

import com.book.domain.Category;

public interface CategoryMapper {

	List<Category> query(Map<String, Object> params);

	Category findById(String cid);

}
