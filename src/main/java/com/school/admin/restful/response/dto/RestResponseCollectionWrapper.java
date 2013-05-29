package com.school.admin.restful.response.dto;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.school.admin.model.SABaseEntity;

@Component(value="restResponseCollectionWrapper")
public class RestResponseCollectionWrapper<T extends SABaseEntity> {
	
	private String result;
	private Integer resultCode;
	private Collection<T> collection;

	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

}
