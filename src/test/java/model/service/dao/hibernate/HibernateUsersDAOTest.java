package model.service.dao.hibernate;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Collections;
import java.util.List;

import model.domain.User;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.common.collect.ImmutableList;

public class HibernateUsersDAOTest {
	
	private HibernateUsersDAO dao;
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	private User user;
	
	@Before
	public void init() {
		initMocks(this);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		
		dao = new HibernateUsersDAO();
		dao.setSessionFactory(sessionFactory);
	}
	
	@Test
	public void shouldGetAllUsers() {
		List<User> users = ImmutableList.of(user);
		Query query = mock(Query.class);
		when(query.list()).thenReturn(users);
		when(session.getNamedQuery("allUsers")).thenReturn(query);
		
		
		List<User> result = dao.getAllUsers();
		
		assertThat(result).isEqualTo(users);
		verify(sessionFactory).getCurrentSession();
		verify(session).getNamedQuery("allUsers");
		verify(query).list();
		verifyNoMoreInteractions(sessionFactory, session, query, user);
	}
	
	@Test
	public void shouldGetUserByUserId() {
		long userId = 10;
		when(session.get(User.class, Long.valueOf(userId))).thenReturn(user);
		
		User result = dao.getUser(userId);
		
		assertThat(result).isEqualTo(user);
		verify(sessionFactory).getCurrentSession();
		verify(session).get(User.class, Long.valueOf(userId));
		verifyNoMoreInteractions(sessionFactory, session, user);
	}
	
	@Test
	public void shouldSaveFund() {
		
		dao.saveUser(user);
		
		verify(sessionFactory).getCurrentSession();
		verify(session).save(user);
		verifyNoMoreInteractions(sessionFactory, session, user);
	}
	
	@Test
	public void shouldGetUserByUserName() {
		List<User> users = ImmutableList.of(user);
		String userName = RandomStringUtils.random(10);
		Query query = mock(Query.class);
		when(query.setParameter("userName", userName)).thenReturn(query);
		when(query.list()).thenReturn(users);
		when(session.getNamedQuery("getUserByUserName")).thenReturn(query);
		
		User result = dao.getUser(userName);
		
		assertThat(result).isEqualTo(user);
		verify(sessionFactory).getCurrentSession();
		verify(session).getNamedQuery("getUserByUserName");
		verify(query).list();
		verify(query).setParameter("userName", userName);
		verify(sessionFactory).getCurrentSession();
		verifyNoMoreInteractions(sessionFactory, session, user);
	}
	
	@Test
	public void shouldGetUserByUserNameFromMoreUsers() {
		User otherUser = mock(User.class);
		List<User> users = ImmutableList.of(user, otherUser);
		String userName = RandomStringUtils.random(10);
		Query query = mock(Query.class);
		when(query.setParameter("userName", userName)).thenReturn(query);
		when(query.list()).thenReturn(users);
		when(session.getNamedQuery("getUserByUserName")).thenReturn(query);
		
		dao.getUser(userName);
		
		verify(sessionFactory).getCurrentSession();
		verify(session).getNamedQuery("getUserByUserName");
		verify(query).list();
		verify(query).setParameter("userName", userName);
		verify(sessionFactory).getCurrentSession();
		verifyNoMoreInteractions(sessionFactory, session, user);
	}
	
	@Test
	public void shouldGetUserByUserNameWhenNoUsers() {
		List<User> users = Collections.emptyList();
		String userName = RandomStringUtils.random(10);
		Query query = mock(Query.class);
		when(query.setParameter("userName", userName)).thenReturn(query);
		when(query.list()).thenReturn(users);
		when(session.getNamedQuery("getUserByUserName")).thenReturn(query);
		
		User result = dao.getUser(userName);
		
		assertThat(result).isNull();
		verify(sessionFactory).getCurrentSession();
		verify(session).getNamedQuery("getUserByUserName");
		verify(query).list();
		verify(query).setParameter("userName", userName);
		verify(sessionFactory).getCurrentSession();
		verifyNoMoreInteractions(sessionFactory, session, user);
	}
	

}
