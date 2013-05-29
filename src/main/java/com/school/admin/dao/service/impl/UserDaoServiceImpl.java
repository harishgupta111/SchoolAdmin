package com.school.admin.dao.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.admin.dao.QuizDao;
import com.school.admin.dao.UserDao;
import com.school.admin.dao.service.UserDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.jackson.mapper.HibernateObjectMapper;
import com.school.admin.model.Quiz;
import com.school.admin.model.User;
import com.school.admin.model.UserGrantedAuthority;

@Transactional
@Component("userDaoService")
public class UserDaoServiceImpl implements UserDaoService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	public User create(User user) throws OQSystemException {
		return this.userDao.create(user);
	}

	public LinkedHashSet<User> getAll() {
		return this.userDao.getAll();
	}

	public User getById(Long id) throws OQSystemException {
		return this.userDao.getById(id);
	}

	@Override
	public Set<Quiz> getQuizesByUser(Long userId) throws OQSystemException {
		return this.userDao.getQuizesByUser(userId);
	}

	public User getUserGraphForResult(Long userId, Long quizId)
			throws OQSystemException {
		Quiz quiz = quizDao.getQuizByUserIdAndQuizId(userId, quizId);
		return quiz.getUser();
	}

	@Override
	public String getAllJSon() throws JsonParseException, JsonMappingException,
			IOException {
		LinkedHashSet<User> set = this.getAll();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
					set);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		LinkedHashSet<User> result = mapper
				.readValue(json, LinkedHashSet.class);
		System.out.println(result.size());
		return json;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (username != null && !username.equals("")) {
			User user = null;
			try {
				user = userDao.loadUserByName(username);
			} catch (OQSystemException e) {
				e.printStackTrace();
			}
			if (user == null) {
				return null;
			}

			GrantedAuthority grantedAuth = new UserGrantedAuthority(
					user.getRole());
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			list.add(grantedAuth);
			user.setAuthoritiesList(list);
			return user;
		} else {
			return null;
		}
	}

}
