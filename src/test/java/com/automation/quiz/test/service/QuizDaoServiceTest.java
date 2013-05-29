package com.automation.quiz.test.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.school.admin.dao.service.QuizDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.enums.QuestionDifficultyLevel;
import com.school.admin.model.enums.QuestionSubject;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuizDaoServiceTest {

	@Autowired
	private QuizDaoService quizDaoService;

	@Test
	@Rollback(true)
	public void testCreateQuiz() throws OQSystemException {
		int n = quizDaoService.create(1L, 2, QuestionDifficultyLevel.BEGINNER,
				QuestionSubject.JAVA);
		Assert.assertEquals(2, n);

	}

}
