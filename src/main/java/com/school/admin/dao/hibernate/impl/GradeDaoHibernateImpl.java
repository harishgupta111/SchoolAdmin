package com.school.admin.dao.hibernate.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.GradeDao;
import com.school.admin.model.Grade;

@Transactional
@Repository("gradeDao")
public class GradeDaoHibernateImpl extends BaseDaoHibernateSupport<Grade, String> implements GradeDao {

	@Override
	public Grade create(Grade t) {
		return super.insert(t, true);
	}

	@Override
	public Set<Grade> getAll() {
		return super.fetchAll();
	}

	@Override
	public Set<Grade> getById(String p) {
		
		return null;
	}

}
