package com.school.admin.dao.service;

import java.util.LinkedHashSet;

import com.school.admin.exception.OQSystemException;
import com.school.admin.model.ContactUs;

public interface ContactUsDaoService {
	
	public ContactUs create(ContactUs contactUs) throws OQSystemException;
	
	public LinkedHashSet<ContactUs> getAll() throws OQSystemException;
	
	public ContactUs getById(Long id) throws OQSystemException;

}
