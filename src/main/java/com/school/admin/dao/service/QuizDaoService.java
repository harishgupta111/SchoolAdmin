package com.school.admin.dao.service;

import java.io.IOException;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Quiz;
import com.school.admin.model.enums.QuestionDifficultyLevel;
import com.school.admin.model.enums.QuestionSubject;

public interface QuizDaoService {
	
	public Quiz create(Quiz quiz);
	
	public int create(Long userId, int numberOfQuestions, QuestionDifficultyLevel qdl, QuestionSubject qs) throws OQSystemException;
	
	public LinkedHashSet<Quiz> getAll();
	
	public String getAllJSon() throws JsonParseException, JsonMappingException, IOException;
	
	public Quiz getById(Long id);


}
