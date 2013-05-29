package com.school.admin.dao.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.dao.PostedAnswerDao;
import com.school.admin.dao.QuizDao;
import com.school.admin.dao.QuizQuestionUserMappingDao;
import com.school.admin.dao.service.QuizQuestionUserMappingDaoService;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.CorrectAnswer;
import com.school.admin.model.PostedAnswer;
import com.school.admin.model.Quiz;
import com.school.admin.model.QuizQuestionUserMapping;

@Transactional
@Component("quizQuestionUserMappingDaoService")
public class QuizQuestionUserMappingDaoServiceImpl implements
		QuizQuestionUserMappingDaoService {

	@Autowired
	private QuizQuestionUserMappingDao quizQuestionUserMappingDao;

	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private PostedAnswerDao postedAnswerDao;


	@Override
	public QuizQuestionUserMapping create(
			QuizQuestionUserMapping quizQuestionUserMapping) {
		return this.quizQuestionUserMappingDao.create(quizQuestionUserMapping);
	}

	@Override
	public LinkedHashSet<QuizQuestionUserMapping> getAll() {
		return this.quizQuestionUserMappingDao.getAll();
	}

	@Override
	public QuizQuestionUserMapping getById(Long id) {
		return this.quizQuestionUserMappingDao.getById(id);
	}

	@Override
	public List<QuizQuestionUserMapping> getByQuestionId(List<Long> idList) {
		return this.quizQuestionUserMappingDao.getByQuestionId(idList);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = OQSystemException.class, propagation = Propagation.REQUIRES_NEW)
	public void updateQuizResultNew(Long userId, Long quizId)
			throws OQSystemException {

		Quiz quiz = quizDao.getQuizByUserIdAndQuizId(userId, quizId);
		Set<QuizQuestionUserMapping> setQQUMap = new LinkedHashSet<QuizQuestionUserMapping>(
				quiz.getQuizQuestionUserMappingSet());

		for (QuizQuestionUserMapping qquMap : setQQUMap) {
			Set<PostedAnswer> postedAnswerSet = new HashSet<PostedAnswer>(
					qquMap.getPostedAnswerSet());
			Set<CorrectAnswer> correctAnswerSet = new HashSet<CorrectAnswer>(
					qquMap.getQuestion().getCorrectAnswerSet());

			String[] postedAnswersArray = new String[postedAnswerSet.size()];
			String[] correctAnswersArray = new String[correctAnswerSet.size()];
			int i = 0;
			for (PostedAnswer p : postedAnswerSet) {
				postedAnswersArray[i] = p.getText();
				i++;
			}

			i = 0;
			for (CorrectAnswer p : correctAnswerSet) {
				correctAnswersArray[i] = p.getText();
				i++;
			}

			Arrays.sort(correctAnswersArray);
			Arrays.sort(postedAnswersArray);
			if (Arrays.equals(correctAnswersArray, postedAnswersArray))
				qquMap.setResult(Boolean.TRUE);
			else
				qquMap.setResult(Boolean.FALSE);

			this.quizQuestionUserMappingDao.updateQuizResult(qquMap);

		}

	}
}
