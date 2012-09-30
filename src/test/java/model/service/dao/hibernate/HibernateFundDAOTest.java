package model.service.dao.hibernate;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import model.domain.fund.Fund;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.common.collect.ImmutableList;

public class HibernateFundDAOTest {
	
	private HibernateFundDAO dao;
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	private Fund fund;
	
	@Before
	public void init() {
		initMocks(this);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		
		dao = new HibernateFundDAO();
		dao.setSessionFactory(sessionFactory);
	}
	
	@Test
	public void shouldGetAllFunds() {
		List<Fund> funds = ImmutableList.of(fund);
		Query query = mock(Query.class);
		when(query.list()).thenReturn(funds);
		when(session.getNamedQuery("allFunds")).thenReturn(query);
		
		
		List<Fund> result = dao.getAllFunds();
		
		assertThat(result).isEqualTo(funds);
		verify(sessionFactory).getCurrentSession();
		verify(session).getNamedQuery("allFunds");
		verify(query).list();
		verifyNoMoreInteractions(sessionFactory, session, query, fund);
	}
	
	@Test
	public void shouldGetFund() {
		long fundId = 10;
		when(session.get(Fund.class, Long.valueOf(fundId))).thenReturn(fund);
		
		Fund result = dao.getFund(fundId);
		
		assertThat(result).isEqualTo(fund);
		verify(sessionFactory).getCurrentSession();
		verify(session).get(Fund.class, Long.valueOf(fundId));
		verifyNoMoreInteractions(sessionFactory, session, fund);
	}
	
	@Test
	public void shouldSaveFund() {
		
		dao.saveFund(fund);
		
		verify(sessionFactory).getCurrentSession();
		verify(session).save(fund);
		verifyNoMoreInteractions(sessionFactory, session, fund);
	}
	
	@Test
	public void shouldUpdateFund() {
		
		dao.updateFund(fund);
		
		verify(sessionFactory).getCurrentSession();
		verify(session).saveOrUpdate(fund);
		verifyNoMoreInteractions(sessionFactory, session, fund);
	}

}
