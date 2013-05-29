package com.school.admin.dao.service.impl;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.admin.dao.ContactUsDao;
import com.school.admin.dao.service.ContactUsDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.ContactUs;

@Component("contactUsDaoServiceImpl")
public class ContactUsDaoServiceImpl implements ContactUsDaoService {
	
	@Autowired
	private ContactUsDao contactUsDao;

	@Override
	public ContactUs create(ContactUs contactUs) throws OQSystemException {
		return this.contactUsDao.create(contactUs);
	}

	@Override
	public LinkedHashSet<ContactUs> getAll() throws OQSystemException {
		return this.contactUsDao.getAll();
	}

	@Override
	public ContactUs getById(Long id) throws OQSystemException {
		return this.contactUsDao.getById(id);
	}

}
