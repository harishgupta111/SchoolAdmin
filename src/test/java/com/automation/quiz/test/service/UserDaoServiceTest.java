package com.automation.quiz.test.service;

import java.util.Set;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.school.admin.dao.service.UserDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserDaoServiceTest implements InitializingBean {

	Logger logger = Logger.getLogger(UserDaoServiceTest.class);

	@Autowired
	private UserDaoService userDaoService;

	public void afterPropertiesSet() throws Exception {
		Assert.assertNotNull(this.userDaoService);
	}

	@Test
	public void shouldGetQuizesByUser() throws OQSystemException {
		logger.debug("STARTING shouldGetByUserIdAndQuizId()");
		Set<Quiz> setQuiz = this.userDaoService.getQuizesByUser(1L);
		Assert.assertNotNull(setQuiz);
		Assert.assertEquals(setQuiz.size(), 2);
		logger.debug("BEFORE RETURNING FROM shouldGetQuizesByUser()");
	}

	@Test
	public void shouldGetById() throws OQSystemException {
		logger.debug("STARTING shouldGetById()");
		Assert.assertNotNull(this.userDaoService.getById(1L));
		logger.debug("BEFORE RETURNING FROM shouldGetById()");
	}

	@Test
	public void shouldGetByUsername() throws OQSystemException {
		logger.debug("STARTING shouldGetByUsername()");
		UserDetails userDetails = this.userDaoService
				.loadUserByUsername("user1");
		Assert.assertNotNull(userDetails);
		logger.debug("BEFORE RETURNING FROM shouldGetByUsername()");
	}

}
