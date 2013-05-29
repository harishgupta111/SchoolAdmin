package com.automation.quiz.test.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.util.Assert;

import com.school.admin.dao.QuizDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuizDaoTest {
	
	Logger logger = Logger.getLogger(QuizDaoTest.class);
	
	@Autowired
	private QuizDao quizDao;
	
	@Test
	public void shouldGetQuizByUserIdAndQuizId() throws OQSystemException
	{
		logger.debug("STARING shouldGetQuizByUserIdAndQuizId()");
		Quiz quiz = quizDao.getQuizByUserIdAndQuizId(1L, 1L);
		Assert.notNull(quiz.getUser());
		logger.debug("BEFORE RETURNING FROM shouldGetQuizByUserIdAndQuizId()");
	}

}
