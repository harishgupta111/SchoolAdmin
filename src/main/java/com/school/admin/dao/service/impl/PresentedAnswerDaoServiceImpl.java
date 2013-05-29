package com.school.admin.dao.service.impl;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.admin.dao.PresentedAnswerDao;
import com.school.admin.dao.service.PresentedAnswerDaoService;
import com.school.admin.model.PresentedAnswer;

@Component("presentedAnswerDaoService")
public class PresentedAnswerDaoServiceImpl implements PresentedAnswerDaoService {
	
	@Autowired
	private PresentedAnswerDao presentedAnswerDao;

	public PresentedAnswer create(PresentedAnswer presentedAnswer) {
		return this.presentedAnswerDao.create(presentedAnswer);
	}

	public LinkedHashSet<PresentedAnswer> getAll() {
		return this.presentedAnswerDao.getAll();
	}

	public PresentedAnswer getById(Long id) {
		return this.presentedAnswerDao.getById(id);
	}

}
