package com.book.dao;

import java.util.List;
import java.util.Map;

import com.book.domain.Cart;

public interface CartMapper {
	void insert(Cart cart);
	void deleteById(String id);
	int queryCount(Map<String,Object> params);
	List<Cart> query(Map<String,Object> params);
	void deleteByUid(String uid);
	Map<String, Object> statistics(String username);
	void update(Cart cart);
	void removeByUId(String uid);

}
