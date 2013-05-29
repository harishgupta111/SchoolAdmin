package com.school.admin.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class BranchAddress extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2615783799117117951L;
	
	@Id
	@Column(name = "branchAddressId", insertable = false, updatable = false)
	private Long branchAddressId;
	
	@Column(name = "branchAddress")
	private String branchAddress;
	
	@Column(name = "branchState")
	private String branchState;
	
	@Column(name = "branchCity")
	private String branchCity;
	
	@Column(name = "postalCode")
	private String postalCode;

	public Long getBranchAddressId() {
		return branchAddressId;
	}

	public void setBranchAddressId(Long branchAddressId) {
		this.branchAddressId = branchAddressId;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchState() {
		return branchState;
	}

	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	

}
