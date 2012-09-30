/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service.dao.hibernate;

import java.util.List;

import model.domain.User;
import model.service.dao.UsersDAO;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link UsersDAO} in hibernate framework.
 * @author Tomasz Jankowski
 */
@Transactional
public class HibernateUsersDAO implements UsersDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * Returns list of all users.
     * Uses named query "allUsers" stored in /web/WEB-INF/classes/queries.hbm.xml .
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return this.sessionFactory.getCurrentSession().getNamedQuery("allUsers").list();
    }

    /**
     * Returns user with given contest id.
     * @param userId the user id
     * @return the user with given id
     */
    public User getUser(long userId) {
        return (User)this.sessionFactory.getCurrentSession().get(User.class, new Long(userId));
    }

    /**
     * Returns user with given userName(login).
     * Uses named query "getUserByUserName" stored in /web/WEB-INF/classes/queries.hbm.xml with param userName.
     * @param name the user userName(login)
     * @return the user with given userName(login)
     */
    public User getUser(String userName) {
       List<User> result = this.sessionFactory.getCurrentSession().getNamedQuery("getUserByUserName").setParameter("userName", userName).list();
       if(result.size() > 0) {
           return result.get(0);
       } else {
           return null;
       }
    }

    /**
     * Saves given user.
     * @param user the user to save
     */
    public void saveUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

}
