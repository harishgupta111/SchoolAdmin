package com.school.admin.dao.service.impl;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.admin.dao.PostedAnswerDao;
import com.school.admin.dao.service.PostedAnswerDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.PostedAnswer;

@Component("postedAnswerDaoService")
public class PostedAnswerDaoServiceImpl implements PostedAnswerDaoService {
	
	@Autowired
	private PostedAnswerDao postedAnswerDao;

	@Override
	public PostedAnswer create(PostedAnswer postedAnswer) throws OQSystemException {
		return this.postedAnswerDao.create(postedAnswer);
	}

	@Override
	public LinkedHashSet<PostedAnswer> getAll() throws OQSystemException {
		return this.postedAnswerDao.getAll();
	}

	@Override
	public PostedAnswer getById(Long id) throws OQSystemException {
		return this.postedAnswerDao.getById(id);
	}

}
