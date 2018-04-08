package com.book.service;

import java.util.List;
import java.util.Map;

import com.book.domain.Cart;

public interface CartService{

	Map<String, Object> statistics(String userId);

	void save(Cart cart);

	List<Cart> query(Cart cart, String entitySQL, String order);

	void remove(String id);

	void removeByUId(String uid);

}
