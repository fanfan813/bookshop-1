package com.book.dao;

import java.util.List;
import java.util.Map;

import com.book.domain.Role;

public interface RoleMapper {
	List<Role> findByusername(String username);

	Role findByRoleName(String rolename);

	void addUser(Map<String,Object> params);

	void removeUserFromAdmin(String uid);
}
