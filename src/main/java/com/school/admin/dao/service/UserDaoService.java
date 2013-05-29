package com.school.admin.dao.service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Quiz;
import com.school.admin.model.User;

public interface UserDaoService extends UserDetailsService {

	public User create(User user) throws OQSystemException;

	public LinkedHashSet<User> getAll();

	public User getById(Long id) throws OQSystemException;

	public Set<Quiz> getQuizesByUser(Long userId) throws OQSystemException;

	public String getAllJSon() throws JsonParseException, JsonMappingException,
			IOException;
	

}
