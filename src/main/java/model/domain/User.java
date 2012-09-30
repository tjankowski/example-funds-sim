package model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a registered user.
 * @author Tomasz Jankowski
 */
@Entity
@Table(name="Users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 5647992933124012409L;

    //user id
    private long id;

    //user login
    private String userName;

    //user role, in default set to ROLE_USER
    private String role = "ROLE_USER";

    //is user account enabled, in default set to true
    private boolean enabled = true;

    //user password
    private String password;

    //user wallet
    private String walletFilePath;

    /**
     * Empty constructor for User class.
     */
    public User() {
    }

    /**
     * Constructor - creates new user with given login and password.
     * @param userName user's login
     * @param password user's password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Returns user id.
     * @return the user id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    /**
     * Sets user id.
     * @param id the user id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns user password.
     * @return the user password
     */
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * Sets user password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user login for login action.
     * @return the userName the user login for login action
     */
    @Column(name="userName")
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user login for login action.
     * @param userName the user login to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns user role in website.
     * @return the role the user role in website
     */
    @Column(name="authority")
    public String getRole() {
        return role;
    }

    /**
     * Sets user role in website.
     * @param role the user role in website to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the status of user account.
     * True if account is active and false if it isn't.
     * @return the enabled the status of user account
     */
    @Column(name="enabled")
    public boolean getEnabled() {
        return enabled;
    }

    /**
     * Sets the status of user account.
     * @param enabled the status of user account to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Sets the file path of user wallet.
     * @param walletFilePath the file path of user wallet to set
     */
	public void setWalletFilePath(String walletFilePath) {
		this.walletFilePath = walletFilePath;
	}

	/**
     * Returns the file path of user wallet.
     * @return the file path of user wallet
     */
	@Column(name = "wallet_file_path")
	public String getWalletFilePath() {
		return walletFilePath;
	}

}
