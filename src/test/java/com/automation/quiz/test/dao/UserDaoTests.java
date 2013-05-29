package com.automation.quiz.test.dao;

import java.util.LinkedHashSet;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.school.admin.dao.UserDao;
import com.school.admin.exception.OQSystemException;
import com.school.admin.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserDaoTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void shouldCreateUser() throws OQSystemException {
		User u = new User();
		u.setActive(true);
		u.setEmail("junit@itvedas.com");
		u.setName("junit");
		u.setPassword("secret");
		u.setUsername("junit");

		User uc = userDao.create(u);
		Assert.assertEquals(uc.getName(), "junit");
	}

	@Test
	public void getAllUsers() {
		LinkedHashSet<User> set = userDao.getAll();
		Assert.assertEquals(2, set.size());
	}
	
}
