package com.school.admin.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

public class Grade extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8987471309238565025L;
	
	private Long gradeId;
	
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "gradeId", referencedColumnName = "gradeId")
	@ManyToOne(targetEntity = School.class, fetch=FetchType.LAZY)
	private SchoolBranch schoolBranch;
	
	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<Grade> gradeSet;

}
