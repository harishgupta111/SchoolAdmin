package com.school.admin.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.UserDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Quiz;
import com.school.admin.model.User;

@Transactional
@Repository("userDao")
public class UserDaoHibernateImpl extends BaseDaoHibernateSupport<User, Long>
		implements UserDao {

	Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.user", "entity.user" }, allEntries = true, beforeInvocation = false)
	public User create(User user) throws OQSystemException {
		User user1 = null;
		try {
			user1 = this.insert(user, true);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}
		return user1;
	}

	@Override
	@Cacheable(value = "entity.user", key = "#root.methodName")
	public LinkedHashSet<User> getAll() {
		String strSQL = "Select c from User c";
		@SuppressWarnings("unchecked")
		LinkedHashSet<User> set = new LinkedHashSet<User>(new LinkedList<User>(
				(List<User>) (this.executeQuery(strSQL))));
		return set;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Cacheable(value = "entity.user")
	public User getById(Long id) throws OQSystemException {
		String strSQL = "Select c from User c where c.userId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<User> list = null;

		try {
			list = (List<User>) this.executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new OQSystemException(e);
		}
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			return user;
		}
		return null;
	}

	@Override
	@Cacheable(value = "entity.quiz")
	public Set<Quiz> getQuizesByUser(Long userId) throws OQSystemException {
		Set<Quiz> set = this.getById(userId).getQuizSet();
		Hibernate.initialize(set);
		return set;
	}

	@Override
	@Cacheable(value = "entity.user")
	public User getUserGraphForResult(Long userId) throws OQSystemException {
		User user = this.getById(userId);
		Hibernate.initialize(user.getQuizQuestionUserMappingSet());
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User loadUserByName(String username) throws OQSystemException {
		String strSQL = "Select c from User c where c.username = :username";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		List<User> list = null;

		try {
			list = (List<User>) this.executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new OQSystemException(e);
		}
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			return user;
		}
		return null;
	}

}

/*
 * @SuppressWarnings({ "unchecked", "unused" }) private Set<Quiz>
 * getQuizesByUserT(Long userId) throws OQSystemException { String strSQL =
 * "Select c.quizSet from User c where c.userId = :id"; Map<String, Object> map
 * = new HashMap<String, Object>(); map.put("id", userId); List<Quiz> list =
 * null; try { list = (List<Quiz>) this.executeQuery(strSQL, map);
 * logger.debug(list.size()); } catch (HibernateException e) { throw new
 * OQSystemException(e); } Set<Quiz> set = new LinkedHashSet<Quiz>(); for (Quiz
 * q : list) set.add(q); return set;
 * 
 * }
 */