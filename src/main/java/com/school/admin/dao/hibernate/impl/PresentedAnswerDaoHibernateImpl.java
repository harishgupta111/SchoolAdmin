package com.school.admin.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.CorrectAnswerDao;
import com.school.admin.dao.PresentedAnswerDao;
import com.school.admin.model.CorrectAnswer;
import com.school.admin.model.PresentedAnswer;
import com.school.admin.model.Question;

@Transactional
@Repository("presentedAnswerDao")
public class PresentedAnswerDaoHibernateImpl extends BaseDaoHibernateSupport<PresentedAnswer, Long> implements PresentedAnswerDao{

	@Autowired
	CorrectAnswerDao correctAnswerDao;
	
	@Override
	@CacheEvict(value = { "entity.presentedAnswer", "entity.presentedAnswer" }, allEntries = true, beforeInvocation = false)
	public PresentedAnswer create(PresentedAnswer presentedAnswer) {
		return this.insert(presentedAnswer, true);
	}

	@Override
	@Cacheable(value="entity.presentedAnswer")
	public LinkedHashSet<PresentedAnswer> getAll() {
		String strSQL = "Select c from PresentedAnswer c";
		@SuppressWarnings("unchecked")
		LinkedHashSet<PresentedAnswer> set = new LinkedHashSet<PresentedAnswer>(new LinkedList<PresentedAnswer>((List<PresentedAnswer>)( this.executeQuery(strSQL))));
		return set;
	}

	@Override
	@Cacheable(value="entity.presentedAnswer")
	public PresentedAnswer getById(Long id) {
		String strSQL = "Select c from PresentedAnswer c where c.presentedAnswerId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unchecked")
		List<PresentedAnswer> list = (List<PresentedAnswer>) this.executeQuery(strSQL,map);
		if (list != null && list.size() > 0) {
			PresentedAnswer presentedAnswer = list.get(0);
			return presentedAnswer;
		}
		return null;
	}

	@Override
	public LinkedHashSet<PresentedAnswer> getPresentedAnswerForQuestion(
			Question q) {
		LinkedHashSet<CorrectAnswer> correctAnswerSet = this.correctAnswerDao.getCorrectAnswerForQuestion(q);
		LinkedHashSet<PresentedAnswer> paSet = new LinkedHashSet<PresentedAnswer>();
		for(CorrectAnswer c : correctAnswerSet)
		{
			PresentedAnswer p = new PresentedAnswer();
			p.setAdditionalText(c.getAdditionalText());
			p.setQuestion(q);
			p.setText(c.getText());
			paSet.add(p);
		}
		return paSet;
	}

	@Override
	public LinkedHashSet<PresentedAnswer> addWrongOptions(Question q) {
		
		LinkedHashSet<PresentedAnswer> set = new LinkedHashSet<PresentedAnswer>();
		PresentedAnswer p = new PresentedAnswer();
		p.setAdditionalText(null);
		p.setQuestion(q);
		p.setText("All of above");
		set.add(p);
		p = new PresentedAnswer();
		p.setAdditionalText(null);
		p.setQuestion(q);
		p.setText("None of above");
		set.add(p);
		return set;
	}
	

}
