package com.school.admin.restful.service;

import java.util.LinkedHashSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.admin.dao.service.QuestionDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.jackson.mapper.HibernateObjectMapper;
import com.school.admin.model.Question;
import com.school.admin.restful.response.dto.RestResponseCollectionWrapper;
import com.school.admin.restful.response.dto.RestResponseWrapper;

@Controller
@Path("/questions")
public class QuestionsRestService {

	@Autowired
	private QuestionDaoService questionDaoService;

	@Autowired
	private RestResponseCollectionWrapper<Question> restResponseCollectionWrapper;

	@Autowired
	private RestResponseWrapper<Question> restResponseWrapper;

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@GET
	@Path("/getAll/")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAll() throws OQSystemException {
		LinkedHashSet<Question> set = questionDaoService.getAll();
		restResponseCollectionWrapper.setCollection(set);
		restResponseCollectionWrapper.setResult("success");
		restResponseCollectionWrapper.setResultCode(Status.CREATED
				.getStatusCode());
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = this.hibernateObjectMapper.prepareJSON(mapper, set);
		return Response.status(restResponseCollectionWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody Response getById(@PathParam("id") Long id) throws OQSystemException
	{
		Question question = questionDaoService.getById(id);
		restResponseWrapper.setData(question);
		restResponseWrapper.setResult("success");
		restResponseWrapper.setResultCode(Status.CREATED
				.getStatusCode());
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = this.hibernateObjectMapper.prepareJSON(mapper, question);
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

}
