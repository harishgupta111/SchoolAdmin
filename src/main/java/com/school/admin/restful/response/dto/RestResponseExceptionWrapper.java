package com.school.admin.restful.response.dto;

import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.school.admin.exception.OQException;

@Provider
@Component(value="restResponseExceptionWrapper")
public class RestResponseExceptionWrapper<T extends OQException> {
	
	private String result;
	private Integer resultCode;
	private T exception;
	private String userFriendlyMessage;
	
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
	public T getException() {
		return exception;
	}
	public void setException(T exception) {
		this.exception = exception;
	}
	public String getUserFriendlyMessage() {
		return userFriendlyMessage;
	}
	public void setUserFriendlyMessage(String userFriendlyMessage) {
		this.userFriendlyMessage = userFriendlyMessage;
	}
	
	
}
