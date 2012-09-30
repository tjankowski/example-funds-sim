package model.service;

import model.domain.User;
import model.domain.Wallet;
import model.service.dao.WalletDAO;

/**
 * 
 * {@link WalletService} implementation.
 * @author Tomasz Jankowski
 *
 */
public class SimpleWalletService implements WalletService {
	
	private WalletDAO walletDAO;
	
	public void saveWallet(User user, Wallet wallet) {
		getWalletDAO().saveWallet(user, wallet);
	}
	
	public Wallet getWallet(User user) {
		return getWalletDAO().getWallet(user.getWalletFilePath());
	}
	
	public Wallet createWallet(User user) {
		Wallet wallet = new Wallet();
        user.setWalletFilePath(Long.toString(user.getId())+".xml");
        getWalletDAO().saveWallet(user, wallet);
        return wallet;
	}

	public void setWalletDAO(WalletDAO walletDAO) {
		this.walletDAO = walletDAO;
	}

	public WalletDAO getWalletDAO() {
		return walletDAO;
	}

}
