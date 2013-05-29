package com.school.admin.dao.service;

import java.util.LinkedHashSet;

import com.school.admin.exception.OQSystemException;
import com.school.admin.model.CorrectAnswer;

public interface CorrectAnswerDaoService {
	
	/**
	 * 
	 * @param correctAnswer
	 * @return
	 * @throws OQSystemException 
	 */
	public CorrectAnswer create(CorrectAnswer correctAnswer) throws OQSystemException;
	
	/**
	 * 
	 * @return
	 * @throws OQSystemException 
	 */
	public LinkedHashSet<CorrectAnswer> getAll() throws OQSystemException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws OQSystemException 
	 */
	public CorrectAnswer getById(Long id) throws OQSystemException;

}
