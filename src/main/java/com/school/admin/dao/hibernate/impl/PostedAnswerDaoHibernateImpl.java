package com.school.admin.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.PostedAnswerDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.PostedAnswer;

@Transactional
@Repository("postedAnswerDao")
public class PostedAnswerDaoHibernateImpl extends
		BaseDaoHibernateSupport<PostedAnswer, Long> implements PostedAnswerDao {

	@Override
	@CacheEvict(value = { "entity.postedAnswer", "entity.postedAnswer" }, allEntries = true, beforeInvocation = false)
	public PostedAnswer create(PostedAnswer postedAnswer)
			throws OQSystemException {
		PostedAnswer p1 = null;
		try {
			p1 = this.insert(postedAnswer, true);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}
		return p1;
	}

	@Override
	@Cacheable(value = "entity.postedAnswer")
	public LinkedHashSet<PostedAnswer> getAll() throws OQSystemException {
		String strSQL = "Select c from PostedAnswer c";
		LinkedHashSet<PostedAnswer> set = null;
		try {
			@SuppressWarnings("unchecked")
			LinkedList<PostedAnswer> list = new LinkedList<PostedAnswer>(
					(List<PostedAnswer>) (this.executeQuery(strSQL)));
			set = new LinkedHashSet<PostedAnswer>(list);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}
		return set;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "entity.postedAnswer")
	public PostedAnswer getById(Long id) throws OQSystemException {
		String strSQL = "Select c from PostedAnswer c where c.quizId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		
		List<PostedAnswer> list = null;
		try{
			
			list = (List<PostedAnswer>) this.executeQuery(
				strSQL, map);
		}catch(HibernateException e)
		{
			throw new OQSystemException(e.getMessage());
		}
		if (list != null && list.size() > 0) {
			PostedAnswer postedAnswer = list.get(0);
			return postedAnswer;
		}
		return null;
	}

}
