package com.school.admin.dao.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.admin.dao.PresentedAnswerDao;
import com.school.admin.dao.QuestionDao;
import com.school.admin.dao.QuizDao;
import com.school.admin.dao.UserDao;
import com.school.admin.dao.service.QuizDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.jackson.mapper.HibernateObjectMapper;
import com.school.admin.model.PresentedAnswer;
import com.school.admin.model.Question;
import com.school.admin.model.Quiz;
import com.school.admin.model.QuizQuestionUserMapping;
import com.school.admin.model.User;
import com.school.admin.model.enums.QuestionDifficultyLevel;
import com.school.admin.model.enums.QuestionSubject;

@Component("quizDaoService")
public class QuizDaoServiceImpl implements QuizDaoService {
	
	private static Logger logger = Logger.getLogger(QuizDaoServiceImpl.class);
	
	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private UserDao userDao;	
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private PresentedAnswerDao presentedAnswerDao;
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@Override
	public Quiz create(Quiz quiz) {
		return this.quizDao.create(quiz);
	}

	@Override
	public LinkedHashSet<Quiz> getAll() {
		return this.quizDao.getAll();
	}

	@Override
	public Quiz getById(Long id) {
		return this.quizDao.getById(id);
	}

	@Override
	public String getAllJSon() throws JsonParseException, JsonMappingException, IOException {
		LinkedHashSet<Quiz> set = getAll();
		
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(set);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		LinkedHashSet<Quiz> result = mapper.readValue(json, LinkedHashSet.class);
		System.out.println(result.size());
		return json;
	}

	@Override
	public int create(Long userId, int numberOfQuestions, QuestionDifficultyLevel qdl, QuestionSubject qs) throws OQSystemException {
		LinkedHashSet<Question> setQuestions = this.questionDao.getBySubjectAndDifficultyLevel(qdl, qs);
		List<Question> questionList = this.questionDao.filterRandomQuestions(setQuestions, numberOfQuestions);
		User user = userDao.getById(userId);
		Quiz quiz = new Quiz();
		quiz.setNumberOfQuestions(numberOfQuestions);
		quiz.setTakenDate(new Date());
		quiz.setUser(user);
		Set<QuizQuestionUserMapping> qqumSet = new LinkedHashSet<QuizQuestionUserMapping>();
		QuizQuestionUserMapping qqum = null;
		for(Question q : questionList)
		{
			logger.debug("question id " + q.getQuestionId() + " question text " + q.getText());
			LinkedHashSet<PresentedAnswer> paSet1 = presentedAnswerDao.getPresentedAnswerForQuestion(q);
			LinkedHashSet<PresentedAnswer> paSet2 = presentedAnswerDao.addWrongOptions(q);
			paSet1.addAll(paSet2);
			q.setPresentedAnswerSet(paSet1);
			this.questionDao.create(q);
			qqum = new QuizQuestionUserMapping();
			qqum.setQuestion(q);
			qqum.setQuiz(quiz);
			qqum.setUser(user);
			qqumSet.add(qqum);
		}
		quiz.setQuizQuestionUserMappingSet(qqumSet);
		logger.debug("number of questions created for quiz " + qqumSet.size());
		this.create(quiz);
		return qqumSet.size();
	}

}
