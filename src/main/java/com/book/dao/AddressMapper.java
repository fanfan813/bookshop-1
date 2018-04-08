package com.book.dao;

import java.util.List;
import java.util.Map;

import com.book.domain.Address;

public interface AddressMapper {
	void insert(Address address);
	List<Address> query(Map<String,Object> params);
	Address findById(String id);
	void update(Address address);
	void deleteById(String id);
	void resetAddressDefault();
}
