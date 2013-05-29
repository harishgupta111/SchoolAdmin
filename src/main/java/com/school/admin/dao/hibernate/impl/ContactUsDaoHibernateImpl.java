package com.school.admin.dao.hibernate.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.ContactUsDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.ContactUs;

@Transactional
@Repository("contactUsDao")
public class ContactUsDaoHibernateImpl extends BaseDaoHibernateSupport<ContactUs, Serializable>implements ContactUsDao{

	@Override
	@CacheEvict(value = { "entity.contactUs", "entity.contactUs" }, allEntries = true, beforeInvocation = false)
	public ContactUs create(ContactUs contactUs) throws OQSystemException {
		return super.insert(contactUs, true);
	}

	@Override
	@Cacheable(value = "entity.contactUs", key = "#root.methodName")
	public LinkedHashSet<ContactUs> getAll() throws OQSystemException {
		String strSQL = "Select c from ContactUs c";
		LinkedHashSet<ContactUs> set = null;
		try {
			@SuppressWarnings("unchecked")
			LinkedList<ContactUs> list = new LinkedList<ContactUs>(
					(List<ContactUs>) (this.executeQuery(strSQL)));
			set = new LinkedHashSet<ContactUs>(list);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}

		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "entity.contactUs")
	public ContactUs getById(Long id) throws OQSystemException {
		String strSQL = "Select c from ContactUs c where c.id = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<ContactUs> list = null;
		try {
			list = (List<ContactUs>) this.executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new OQSystemException(e.getMessage());
		}

		if (list != null && list.size() > 0) {
			ContactUs contactUs = list.get(0);
			return contactUs;
		}
		return null;
	}

}
