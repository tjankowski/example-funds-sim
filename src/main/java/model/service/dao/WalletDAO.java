/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service.dao;

import model.domain.User;
import model.domain.Wallet;

/**
 * Data Access Object interface for {@link Wallet} objects
 * @author Tomasz Jankowski
 */
public interface WalletDAO {
	
	/**
     * Returns wallet with given file path.
     * @param filePath the file path of wallet file
     * @return the wallet from file
     */
	public Wallet getWallet(String filePath);

	/**
     * Saves given user wallet.
     * @param user user
     * @param wallet the wallet to save
     */
    public void saveWallet(User user, Wallet wallet);

}
