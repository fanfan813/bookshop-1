package com.book.dao;

import java.util.List;

import com.book.domain.Orderitem;

public interface OrderitemMapper {
	void insert(Orderitem orderitem);

	List<Orderitem> findByOid(String oid);

	void removeByoId(String oid);
}
