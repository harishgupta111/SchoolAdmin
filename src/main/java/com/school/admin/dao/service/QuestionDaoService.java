package com.school.admin.dao.service;

import java.util.LinkedHashSet;

import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Question;

public interface QuestionDaoService {
	
	public Question create(Question question);
	
	public LinkedHashSet<Question> getAll() throws OQSystemException;
	
	public Question getById(Long id) throws OQSystemException;
}
