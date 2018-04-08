package com.book.util;

import org.apache.shiro.SecurityUtils;

import com.book.domain.Role;
import com.book.domain.User;

public class SecurityUtil {
	public static User getCurrentUser(){
		return (User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
	}
	public static Role getCurrentRole(){
		return (Role)SecurityUtils.getSubject().getSession().getAttribute("currentRole");
	}
}
