package com.school.admin.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.CorrectAnswerDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.CorrectAnswer;
import com.school.admin.model.Question;

@Transactional
@Repository("correctAnswerDao")
public class CorrectAnswerDaoHibernateImpl extends
		BaseDaoHibernateSupport<CorrectAnswer, Long> implements
		CorrectAnswerDao {

	@Override
	@CacheEvict(value = { "entity.correctAnswer", "entity.correctAnswer" }, allEntries = true, beforeInvocation = false)
	public CorrectAnswer create(CorrectAnswer correctAnswer)
			throws OQSystemException {
		CorrectAnswer c1 = null;
		try {
			c1 = this.insert(correctAnswer, true);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}

		return c1;
	}

	@Override
	@Cacheable(value = "entity.correctAnswer", key = "#root.methodName")
	public LinkedHashSet<CorrectAnswer> getAll() throws OQSystemException {
		String strSQL = "Select c from CorrectAnswer c";
		LinkedHashSet<CorrectAnswer> set = null;
		try {
			@SuppressWarnings("unchecked")
			LinkedList<CorrectAnswer> list = new LinkedList<CorrectAnswer>(
					(List<CorrectAnswer>) (this.executeQuery(strSQL)));
			set = new LinkedHashSet<CorrectAnswer>(list);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}

		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "entity.correctAnswer")
	public CorrectAnswer getById(Long id) throws OQSystemException {
		String strSQL = "Select c from CorrectAnswer c where c.correctAnswerId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<CorrectAnswer> list = null;
		try {
			list = (List<CorrectAnswer>) this.executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}

		if (list != null && list.size() > 0) {
			CorrectAnswer correctAnswer = list.get(0);
			return correctAnswer;
		}
		return null;
	}

	@Override
	public LinkedHashSet<CorrectAnswer> getCorrectAnswerForQuestion(Question q) {
		Set<CorrectAnswer> set = q.getCorrectAnswerSet();
		Hibernate.initialize(set);
		return new LinkedHashSet<CorrectAnswer>(set);
	}

}
