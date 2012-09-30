
package model.service;

import java.util.List;

import model.domain.User;
import model.service.dao.UsersDAO;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link UsersManager} interface.
 * @author Tomasz Jankowski
 */
public class SimpleUsersManager implements UsersManager {

    //User.class data access object used for accessing User.class objects
    private UsersDAO usersDao;
    
    private WalletService walletService;

    /**
     * Returns list of all users.
     * @return list of all users
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return getUsersDao().getAllUsers();
    }

    /**
     * Returns user with given contest id.
     * @param userId the user id
     * @return the user with given id
     */
    @Transactional(readOnly = true)
    public User getUser(long userId) {
       return getUsersDao().getUser(userId);
    }

    /**
     * Returns user with given userName(login).
     * @param name the user userName(login)
     * @return the user with given userName(login)
     */
    @Transactional(readOnly = true)
    public User getUser(String userName) {
       return getUsersDao().getUser(userName);
    }

     /**
     * Creates given user.
     * @param user the user to create
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void createUser(User user) {
        getUsersDao().saveUser(user);
        getWalletService().createWallet(user);
        getUsersDao().saveUser(user);
    }

    /**
     * Returns {@link User} data access object used for accessing  {@link User} objects.
     * @return the usersDao the  {@link User} data access object
     */
    public UsersDAO getUsersDao() {
        return usersDao;
    }

    /**
     * Sets  {@link User} data access object used for accessing  {@link User} objects.
     * @param usersDao the the  {@link User} data access object to set
     */
    public void setUsersDao(UsersDAO usersDao) {
        this.usersDao = usersDao;
    }

	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}

	public WalletService getWalletService() {
		return walletService;
	}

}