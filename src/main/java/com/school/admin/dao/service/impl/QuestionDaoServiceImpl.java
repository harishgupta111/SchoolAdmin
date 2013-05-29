package com.school.admin.dao.service.impl;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.admin.dao.QuestionDao;
import com.school.admin.dao.service.QuestionDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Question;

@Component("questionDaoService")
public class QuestionDaoServiceImpl implements QuestionDaoService{
	
		@Autowired
		private QuestionDao questionDao;

		public Question create(Question question) {
			return this.questionDao.create(question);
		}

		public LinkedHashSet<Question> getAll() throws OQSystemException {
			return this.questionDao.getAll();
		}

		public Question getById(Long id) throws OQSystemException {
			return this.questionDao.getById(id);
		}

}
