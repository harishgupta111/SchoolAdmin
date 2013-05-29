package com.school.admin.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

public class Teacher {
	
	private String teacherId;
	


	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "branchGradeMappingId", referencedColumnName = "branchGradeMappingId")
	@ManyToOne(targetEntity = School.class, fetch=FetchType.LAZY)
	private BranchGradeMapping branchGradeMapping;



	public String getTeacherId() {
		return teacherId;
	}



	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	

}
