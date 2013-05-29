package com.school.admin.restful.service;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.admin.exception.OQSystemException;
import com.school.admin.jackson.mapper.HibernateObjectMapper;
import com.school.admin.model.ContactUs;

@Controller
@Path("/contactUs")
public class ContactUsRestService {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	
	@POST
	@Path("/submit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody Response submit(String jsonRequest) throws OQSystemException 
	{
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		ContactUs contactUs = null;
		
		try {
			contactUs = mapper.readValue(jsonRequest, ContactUs.class);
			Set<ConstraintViolation<ContactUs>> constraintViolations = validator.validate(contactUs);
			
		} catch (JsonParseException e) {
			throw new OQSystemException(e);
		} catch (JsonMappingException e) {
			throw new OQSystemException(e);
		} catch (IOException e) {
			throw new OQSystemException(e);
		};
		
		return null;
	}

}
