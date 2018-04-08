package com.book.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.AddressMapper;
import com.book.domain.Address;
import com.book.domain.User;
import com.book.service.AddressService;
import com.book.util.UUIDutil;

@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public void save(Address address) {
		if("1".equals(address.getIsDefault())){
			addressMapper.resetAddressDefault();
		}
		if(StringUtils.isEmpty(address.getId())){
			User user=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			address.setId(UUIDutil.getUUID());
			address.setUsername(user.getUsername());
			addressMapper.insert(address);
		}else {
			addressMapper.update(address);
		}
	}

	@Override
	public List<Address> queryByCurrentUser() {
		User user=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Address address=new Address();
		address.setUsername(user.getUsername());
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("dto", address);
		params.put("orderBy", "is_default desc");
		return addressMapper.query(params);
	}

	@Override
	public void remove(String id) {
		addressMapper.deleteById(id);
	}

	@Override
	public Address findById(String id) {
		return addressMapper.findById(id);
	}
}
