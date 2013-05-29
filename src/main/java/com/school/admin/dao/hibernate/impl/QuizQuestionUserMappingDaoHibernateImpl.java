package com.school.admin.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.QuizQuestionUserMappingDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.QuizQuestionUserMapping;

@Transactional
@Repository("quizQuestionUserMappingDao")
public class QuizQuestionUserMappingDaoHibernateImpl extends BaseDaoHibernateSupport<QuizQuestionUserMapping, Long> implements QuizQuestionUserMappingDao {

	@Override
	@CacheEvict(value = { "entity.qquMapping", "entity.qquMapping" }, allEntries = true, beforeInvocation = false)
	public QuizQuestionUserMapping create(
			QuizQuestionUserMapping quizQuestionUserMapping) {
		return this.insert(quizQuestionUserMapping, true);
	}

	@Override
	@Cacheable(value="entity.qquMapping", key="#root.methodName")
	public LinkedHashSet<QuizQuestionUserMapping> getAll() {
		String strSQL = "Select c from QuizQuestionUserMapping c";
		@SuppressWarnings("unchecked")
		LinkedHashSet<QuizQuestionUserMapping> set = new LinkedHashSet<QuizQuestionUserMapping>(new LinkedList<QuizQuestionUserMapping>(
				(List<QuizQuestionUserMapping>) (this.executeQuery(strSQL))));
		return set;
	}

	@Override
	@Cacheable(value="entity.qquMapping")
	public QuizQuestionUserMapping getById(Long id) {
		String strSQL = "Select c from QuizQuestionUserMapping c where c.qquId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unchecked")
		List<QuizQuestionUserMapping> list = (List<QuizQuestionUserMapping>) this.executeQuery(strSQL, map);
		if (list != null && list.size() > 0) {
			QuizQuestionUserMapping mapping = list.get(0);
			return mapping;
		}
		return null;
	}
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT, rollbackFor=OQSystemException.class, propagation=Propagation.MANDATORY)
	@CacheEvict(value = { "entity.qquMapping", "entity.qquMapping" }, allEntries = true, beforeInvocation = false)
	public void updateQuizResult(QuizQuestionUserMapping qquMap)
			throws OQSystemException {
		try{
				super.update(qquMap, true);
		}catch(HibernateException e)
		{
			throw new OQSystemException(e);
		}
	}

	@Override
	@Cacheable(value="entity.qquMapping")
	public List<QuizQuestionUserMapping> getByQuestionId(List<Long> idList) {
		String strSQL = "Select c from QuizQuestionUserMapping c WHERE c.question.questionId IN :idList";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(strSQL);
		query.setParameterList("idList", idList);
		@SuppressWarnings("unchecked")
		List<QuizQuestionUserMapping> list = (List<QuizQuestionUserMapping>) query.list();
		return list;
	}
	
	@Override
	@Cacheable(value="entity.qquMapping")
	public List<QuizQuestionUserMapping> getByUserIdAndQuizId(Long userId, Long quizId)
	{
		String strSQL = "Select c from QuizQuestionUserMapping c WHERE c.user.userId = :userId and c.quiz.quizId = :quizId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(strSQL);
		query.setParameter("userId", userId);
		query.setParameter("quizId", quizId);
		@SuppressWarnings("unchecked")
		List<QuizQuestionUserMapping> list = (List<QuizQuestionUserMapping>) query.list();
		return list;
	}
	
	@Override
	@Cacheable(value="entity.qquMapping")
	public List<QuizQuestionUserMapping> getByUserIdAndQuizIdAndQuestionId(Long userId, Long quizId, Long questionId)
	{
		String strSQL = "Select c from QuizQuestionUserMapping c WHERE c.user.userId = :userId and c.quiz.quizId = :quizId and c.question.questionId = :questionId";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(strSQL);
		query.setParameter("userId", userId);
		query.setParameter("quizId", quizId);
		query.setParameter("questionId", questionId);
		@SuppressWarnings("unchecked")
		List<QuizQuestionUserMapping> list = (List<QuizQuestionUserMapping>) query.list();
		return list;
	}

}
