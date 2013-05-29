package com.school.admin.dao.service;

import java.util.LinkedHashSet;

import com.school.admin.exception.OQSystemException;
import com.school.admin.model.PostedAnswer;

public interface PostedAnswerDaoService {
	
	public PostedAnswer create(PostedAnswer postedAnswer) throws OQSystemException;
	
	public LinkedHashSet<PostedAnswer> getAll() throws OQSystemException;
	
	public PostedAnswer getById(Long id) throws OQSystemException;

}
