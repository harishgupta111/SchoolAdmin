package com.school.admin.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.SchoolDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.School;

@Transactional
@Repository("sachoolDao")
public class SchoolDaoHibernateImpl extends BaseDaoHibernateSupport<School, Long>
		implements SchoolDao {

	@Override
	@CacheEvict(value = { "entity.school", "entity.school" }, allEntries = true, beforeInvocation = false)
	public School create(School school) {
		return this.insert(school, true);
	}

	@Override
	@Cacheable(value="entity.school", key="#root.methodName")
	public LinkedHashSet<School> getAll() {
		String strSQL = "Select c from School c";
		@SuppressWarnings("unchecked")
		LinkedHashSet<School> set = new LinkedHashSet<School>(new LinkedList<School>(
				(List<School>) (this.executeQuery(strSQL))));
		return set;
	}

	@Override
	@Cacheable(value="entity.school")
	public School getById(Long id) {
		String strSQL = "Select c from School c where c.schoolId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unchecked")
		List<School> list = (List<School>) this.executeQuery(strSQL, map);
		if (list != null && list.size() > 0) {
			School school = list.get(0);
			return school;
		}
		return null;
	}
	
	@Override
	@Cacheable(value="entity.school")
	public School getAll(Long id) {
		String strSQL = "Select schoolId from School";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unchecked")
		List<School> list = (List<School>) this.executeQuery(strSQL, map);
		if (list != null && list.size() > 0) {
			School school = list.get(0);
			return school;
		}
		return null;
	}

	@Override
	public Set<School> getById(String p) {
		// TODO Auto-generated method stub
		return null;
	}

}
