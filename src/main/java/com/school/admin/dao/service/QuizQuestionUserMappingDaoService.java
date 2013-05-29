package com.school.admin.dao.service;

import java.util.LinkedHashSet;
import java.util.List;

import com.school.admin.exception.OQSystemException;
import com.school.admin.model.QuizQuestionUserMapping;

public interface QuizQuestionUserMappingDaoService {
	
	public QuizQuestionUserMapping create(QuizQuestionUserMapping quizQuestionUserMapping);
	
	public LinkedHashSet<QuizQuestionUserMapping> getAll();
	
	public QuizQuestionUserMapping getById(Long id);
	
	public List<QuizQuestionUserMapping> getByQuestionId(List<Long> idList);
	
	public void updateQuizResultNew(Long userId, Long quizId) throws OQSystemException; 

}
