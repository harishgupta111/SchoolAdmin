package com.school.admin.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

public class Student {
	
	private String studentId;
	
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "branchGradeMappingId", referencedColumnName = "branchGradeMappingId")
	@ManyToOne(targetEntity = School.class, fetch=FetchType.LAZY)
	private BranchGradeMapping branchGradeMapping;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	
	

}
