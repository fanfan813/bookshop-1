package com.book.service;

import com.book.domain.Order;
import com.book.util.Pager;

public interface OrderService {

	void save(Order order);

	Pager query(Order order, String orderBy,String entitySQL, int pageNum, int pageSize);

	Order get(String id);

	void edit(Order order);

	void remove(String oid);

}
