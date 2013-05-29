package com.automation.quiz.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.service.QuizQuestionUserMappingDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.QuizQuestionUserMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuizQuestionUserMappingDaoTest {
	
	Logger logger = Logger.getLogger(QuizQuestionUserMappingDaoTest.class);
	
	@Autowired
	private QuizQuestionUserMappingDaoService quizQuestionUserMappingDaoService;
	
	@Test
	public void shouldGetByQuestionIds() throws OQSystemException
	{
		logger.info("STARING shouldGetByQuestionIds()");
		List<Long> list = new ArrayList<Long>();
		list.add(1L); 
		List<QuizQuestionUserMapping> newList = quizQuestionUserMappingDaoService.getByQuestionId(list);
		org.junit.Assert.assertEquals(newList.size(), 1);
		logger.info("BEFORE RETURNING FROM shouldGetByQuestionIds()");
	}

	

	@Transactional
	public void shouldUpdateQuizResultsNew() throws OQSystemException
	{
		logger.info("STARING shouldUpdateQuizResultsNew()");
		quizQuestionUserMappingDaoService.updateQuizResultNew(1L, 1L);
		logger.info("BEFORE RETURNING FROM shouldUpdateQuizResultsNew()");
	}

}
