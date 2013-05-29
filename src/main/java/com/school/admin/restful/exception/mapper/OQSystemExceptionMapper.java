package com.school.admin.restful.exception.mapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.admin.exception.OQSystemException;
import com.school.admin.jackson.mapper.HibernateObjectMapper;
import com.school.admin.restful.response.dto.RestResponseExceptionWrapper;

@Provider
@Component
public class OQSystemExceptionMapper implements
		ExceptionMapper<OQSystemException> {
	
	@Autowired
	private RestResponseExceptionWrapper<OQSystemException> restResponseExceptionWrapper;
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public  @ResponseBody Response toResponse(OQSystemException arg0) {
		
		restResponseExceptionWrapper.setException(arg0);
		restResponseExceptionWrapper.setResult(arg0.getMessage());
		restResponseExceptionWrapper.setResultCode(Status.INTERNAL_SERVER_ERROR
				.getStatusCode());
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = null;
		try {
			json = this.hibernateObjectMapper.prepareJSON(mapper, restResponseExceptionWrapper);
		} catch (OQSystemException e) {
			e.printStackTrace();
		}
		return Response.status(restResponseExceptionWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

}
