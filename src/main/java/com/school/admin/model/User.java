package com.school.admin.model;

import java.util.Collection;
import java.util.List;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "oq_User")
@Cache(region="entity.user", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class User extends SABaseEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4153994237761419631L;
	
	public User() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", insertable = false, updatable = false)
	private Long userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	@Type(type = "yes_no")
	private Boolean active;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "email")
	private String email;

	@Transient
	private List<GrantedAuthority> authoritiesList;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<School> schoolSet;
	
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
//	private Set<PostedAnswer> postedAnswerSet;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<QuizQuestionUserMapping> quizQuestionUserMappingSet;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Quiz> getQuizSet() {
		return quizSet;
	}

	public void setQuizSet(Set<Quiz> quizSet) {
		this.quizSet = quizSet;
	}

	public Set<QuizQuestionUserMapping> getQuizQuestionUserMappingSet() {
		return quizQuestionUserMappingSet;
	}

	public void setQuizQuestionUserMappingSet(
			Set<QuizQuestionUserMapping> quizQuestionUserMappingSet) {
		this.quizQuestionUserMappingSet = quizQuestionUserMappingSet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getActive();
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.getAuthoritiesList();
	}

	public List<GrantedAuthority> getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(List<GrantedAuthority> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}


	
}
