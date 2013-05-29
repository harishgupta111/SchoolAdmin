package com.school.admin.dao;

import java.io.Serializable;
import java.util.Set;

import com.school.admin.model.SABaseEntity;
import com.school.admin.model.School;

public interface BaseDao<T extends SABaseEntity, P extends Serializable> {
	
	public T create(T t);
	
	public Set<T> getAll();
	
	public Set<T> getById(P p);


}
