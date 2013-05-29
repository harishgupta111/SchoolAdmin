package com.school.admin.dao.service;

import java.util.LinkedHashSet;

import com.school.admin.model.PresentedAnswer;

public interface PresentedAnswerDaoService {
	
	public PresentedAnswer create(PresentedAnswer presentedAnswer);
	
	public LinkedHashSet<PresentedAnswer> getAll();
	
	public PresentedAnswer getById(Long id);

}
