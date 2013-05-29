package com.school.admin.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "id_school")
@Cache(region="entity.school", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class School extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137202884594499852L;
	
	public School() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "schoolId", insertable = false, updatable = false)
	private Long schoolId;
	
	
//	@Fetch(FetchMode.JOIN)
	//@JoinColumn(name = "userId", referencedColumnName = "userId")
//	@ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
//	private User user;
	
//	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
//	private Set<PostedAnswer> postedAnswerSet;
	
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<SchoolBranch> schoolBranchMappingSet;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((schoolBranchMappingSet == null) ? 0
						: schoolBranchMappingSet.hashCode());
		result = prime * result
				+ ((schoolId == null) ? 0 : schoolId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (schoolBranchMappingSet == null) {
			if (other.schoolBranchMappingSet != null)
				return false;
		} else if (!schoolBranchMappingSet.equals(other.schoolBranchMappingSet))
			return false;
		if (schoolId == null) {
			if (other.schoolId != null)
				return false;
		} else if (!schoolId.equals(other.schoolId))
			return false;
		return true;
	}


	public Long getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}


	public Set<SchoolBranch> getSchoolBranchMappingSet() {
		return schoolBranchMappingSet;
	}


	public void setSchoolBranchMappingSet(Set<SchoolBranch> schoolBranchMappingSet) {
		this.schoolBranchMappingSet = schoolBranchMappingSet;
	}
	
	
	
}
