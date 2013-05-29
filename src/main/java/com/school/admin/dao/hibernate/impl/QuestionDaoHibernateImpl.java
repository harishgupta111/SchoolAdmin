package com.school.admin.dao.hibernate.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.QuestionDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Question;
import com.school.admin.model.enums.QuestionDifficultyLevel;
import com.school.admin.model.enums.QuestionSubject;

@Transactional
@Repository("questionDao")
public class QuestionDaoHibernateImpl extends
		BaseDaoHibernateSupport<Question, Long> implements QuestionDao {

	private Logger logger = Logger.getLogger(QuestionDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.question", "entity.question" }, allEntries = true, beforeInvocation = false)
	public Question create(Question question) {
		return this.insert(question, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "entity.question", key = "#root.methodName")
	public LinkedHashSet<Question> getAll() throws OQSystemException {
		String strSQL = "Select c from Question c";
		LinkedHashSet<Question> set = null;
		LinkedList<Question> list = null;
		try {
			list = new LinkedList<Question>(
					(List<Question>) (this.executeQuery(strSQL)));
			set = new LinkedHashSet<Question>(list);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}

		return set;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Cacheable(value = "entity.question")
	public Question getById(Long id) throws OQSystemException {
		String strSQL = "Select c from Question c where c.questionId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Question> list = null;
		try {
			list = (List<Question>) this.executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}
		if (list != null && list.size() > 0) {
			Question question = list.get(0);
			return question;
		}
		return null;
	}

	@Override
	public LinkedHashSet<Question> getBySubjectAndDifficultyLevel(
			QuestionDifficultyLevel qdl, QuestionSubject qs)
			throws OQSystemException {
		String strSQL = "Select c from Question c where c.questionDifficultyLevel = :qdl and c.questionSubject = :qs ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qdl", qdl);
		map.put("qs", qs);
		LinkedHashSet<Question> set = null;
		try {
			@SuppressWarnings("unchecked")
			LinkedList<Question> list = new LinkedList<Question>(
					(List<Question>) this.executeQuery(strSQL, map));
			set = new LinkedHashSet<Question>(list);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}
		return set;
	}

	@Override
	public List<Question> filterRandomQuestions(LinkedHashSet<Question> set,
			int numberOfQuestions) {
		List<Question> questionList = new ArrayList<Question>(set);
		Collections.shuffle(questionList);
		logger.debug("number of questions before random selection "
				+ questionList.size());
		questionList = new ArrayList<Question>(questionList.subList(0,
				numberOfQuestions));
		logger.debug("number of questions after random selection "
				+ questionList.size());
		return questionList;
	}

}
