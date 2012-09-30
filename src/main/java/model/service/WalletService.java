package model.service;

import model.domain.User;
import model.domain.Wallet;

/**
 * Interface for interaction with user {@link Wallet}.
 * Provides all functions related to the {@link Wallet}.
 * @author Tomasz Jankowski
 */
public interface WalletService {
	
	/**
	 * Method to save user wallet.
	 * @param user user
	 * @param wallet wallet
	 */
	public void saveWallet(User user, Wallet wallet);
	
	/**
	 * Method to get user wallet.
	 * @param user user
	 */
	public Wallet getWallet(User user);
	
	 /**
	 * Method to create user wallet.
	 * @param user user
	 */
	public Wallet createWallet(User user);

}
