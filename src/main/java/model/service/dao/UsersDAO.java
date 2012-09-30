/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service.dao;

import model.domain.User;
import java.util.List;

/**
 * Data Access Object interface for {@link User} objects
 * @author Tomasz Jankowski
 */
public interface UsersDAO {

    /**
     * Returns list of all users.
     * @return list of all users
     */
    public List<User> getAllUsers();

    /**
     * Returns user with given id.
     * @param userId the user id
     * @return the user with given id
     */
    public User getUser(long userId);

    /**
     * Returns user with given userName(login).
     * @param name the user userName(login)
     * @return the user with given userName(login)
     */
    public User getUser(String userName);

    /**
     * Saves given user.
     * @param user the user to save
     */
    public void saveUser(User user);

}