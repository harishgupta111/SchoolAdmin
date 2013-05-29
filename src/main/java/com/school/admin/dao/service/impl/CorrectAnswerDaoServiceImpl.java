package com.school.admin.dao.service.impl;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.admin.dao.CorrectAnswerDao;
import com.school.admin.dao.service.CorrectAnswerDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.CorrectAnswer;

@Component("correctAnswerDaoService")
public class CorrectAnswerDaoServiceImpl implements CorrectAnswerDaoService {
	
	@Autowired
	private CorrectAnswerDao correctAnswerDao;
	
	public CorrectAnswer create(CorrectAnswer correctAnswer) throws OQSystemException {
		return this.correctAnswerDao.create(correctAnswer);
	}

	public LinkedHashSet<CorrectAnswer> getAll() throws OQSystemException {
		return this.correctAnswerDao.getAll();
	}

	public CorrectAnswer getById(Long id) throws OQSystemException {
		return this.correctAnswerDao.getById(id);
	}

}
