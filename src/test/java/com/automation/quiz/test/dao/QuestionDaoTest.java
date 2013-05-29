package com.automation.quiz.test.dao;

import java.util.LinkedHashSet;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.school.admin.dao.QuestionDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.Question;
import com.school.admin.model.enums.QuestionDifficultyLevel;
import com.school.admin.model.enums.QuestionSubject;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuestionDaoTest implements InitializingBean {

	Logger logger = Logger.getLogger(QuestionDaoTest.class);

	@Autowired
	private QuestionDao questionDao;

	@Test
	@Rollback(true)
	public void shouldCreateQuestion() {
		logger.debug("STARING shouldCreateQuestion()");
		Question q = new Question();
		q.setText("What are the factors of 4?");
		q.setQuestionDifficultyLevel(QuestionDifficultyLevel.BEGINNER);
		q.setQuestionSubject(QuestionSubject.JAVA);
		Question q1 = questionDao.create(q);
		Assert.assertNotNull(q1);
		Assert.assertEquals(q1.getText(), "What are the factors of 4?");
		logger.debug("BEFORE RETURNING FROM shouldCreateQuestion()");
	}

	@Test
	public void shouldGetBySubjectAndDifficultyLevel() throws OQSystemException {
		LinkedHashSet<Question> set = this.questionDao
				.getBySubjectAndDifficultyLevel(
						QuestionDifficultyLevel.BEGINNER, QuestionSubject.JAVA);
		Assert.assertEquals(set.size(), 4);
	}
	
	@Test
	public void shouldFilterRandomQuestions() throws OQSystemException {
		logger.debug("STARTING shouldFilterRandomQuestions()");
		LinkedHashSet<Question> set = this.questionDao
				.getBySubjectAndDifficultyLevel(
						QuestionDifficultyLevel.BEGINNER, QuestionSubject.JAVA);
		List<Question> questionList = this.questionDao.filterRandomQuestions(set, 3);
		Assert.assertEquals(3, questionList.size());
		logger.debug("BEFORE RETURNING FROM shouldFilterRandomQuestions()");
	}


	@Test
	public void shouldReturnAllQuestions() throws OQSystemException {
		logger.debug("STARTING shouldReturnAllQuestions()");
		LinkedHashSet<Question> q1 = questionDao.getAll();
		Assert.assertNotNull(q1);
		Assert.assertEquals(q1.size(), 4);
		logger.debug("BEFORE RETURNING FROM shouldReturnAllQuestions()");
	}

	@Test
	public void shouldReturnAllQuestionsShouldNotFireSQL() throws OQSystemException {
		logger.debug("STARTING shouldReturnAllQuestionsShouldNotFireSQL()");
		LinkedHashSet<Question> q1 = questionDao.getAll();
		Assert.assertNotNull(q1);
		logger.debug("BEFORE RETURNING FROM shouldReturnAllQuestionsShouldNotFireSQL()");
	}
	
//	@Test
//	public void verifyCache() {
//		Cache cache = ehCacheManager.getCache("entity.question");
//		ValueWrapper v = cache.get("getAll");
//		@SuppressWarnings("unchecked")
//		LinkedHashSet<Question> q1 = (LinkedHashSet<Question>) v.get();
//		Assert.assertEquals(q1.size(), 5);
//	}

	public void afterPropertiesSet() throws Exception {
		Assert.assertNotNull(questionDao);
	}

}
