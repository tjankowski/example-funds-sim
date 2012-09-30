package model.service.dao.hibernate;

import java.util.List;

import model.domain.fund.Fund;
import model.service.dao.FundDAO;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

/**
 * Hibernate {@link FundDAO} implementation.
 * @author Tomasz Jankowski
 */
@Transactional
public class HibernateFundDAO implements FundDAO {

     private SessionFactory sessionFactory;
     
     public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

     public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Fund getFund(long fundId) {
        return (Fund)this.sessionFactory.getCurrentSession().get(Fund.class, new Long(fundId));
    }


    public List<Fund> getAllFunds() {
         return getSessionFactory().getCurrentSession().getNamedQuery("allFunds").list();
    }

    public void saveFund(Fund fund) {
        this.sessionFactory.getCurrentSession().save(fund);
    }

    public void updateFund(Fund fund) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(fund);
    }

}
