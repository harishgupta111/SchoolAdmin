package com.school.admin.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "schoolbranch")
@Cache(region="entity.schoolbranch", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class SchoolBranch extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137202884594499852L;
	
	public SchoolBranch() {
		super();
	}

	@Id
	@Column(name = "branchId", insertable = false, updatable = false)
	private Long branchId;
	

	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "schoolId", referencedColumnName = "schoolId")
	@ManyToOne(targetEntity = School.class, fetch=FetchType.LAZY)
	private School school;
	
	@OneToMany(mappedBy = "schoolBranch", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<Grade> gradeSet;
	
	@OneToMany(mappedBy = "schoolBranch", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<BranchGradeMapping> branchGradeMappingSet;
	
	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public School getSchool() {
		return school;
	}


	public void setSchool(School school) {
		this.school = school;
	}


}
