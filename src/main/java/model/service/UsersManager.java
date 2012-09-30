package model.service;

import java.util.List;

import model.domain.User;

/**
 * Interface for interaction with {@link User} objects.
 * Provides all functions related to the {@link User} .
 * @author Tomasz Jankowski
 */
public interface UsersManager {

    /**
     * Returns list of all users.
     * @return list of all users
     */
    public List<User> getAllUsers();

    /**
     * Returns user with given contest id.
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
     * Creates given user.
     * @param user the user to create
     */
    public void createUser(User user);
}