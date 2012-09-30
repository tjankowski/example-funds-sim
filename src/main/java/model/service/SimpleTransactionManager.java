package model.service;

import java.math.BigDecimal;

import model.domain.User;
import model.domain.Wallet;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;
import model.exceptions.NotEnoughMoneyException;
import model.service.fund.FundUnitService;

/**
 * {@link TransactionManager} implementation.
 * @author Tomasz Jankowski
 */
public class SimpleTransactionManager implements TransactionManager {

    private FundUnitService fundUnitService;
    private WalletService walletService;

    public void buyFundUnits(User user, Fund fund, FundUnit unit, int count) throws NotEnoughMoneyException {
        BigDecimal price = getFundUnitService().buy(fund, unit).multiply(BigDecimal.valueOf(count));
        Wallet wallet = getWalletService().getWallet(user);
        BigDecimal ownedMoney = wallet.getMoney();
        if (price.compareTo(ownedMoney) > 0) {
            throw new NotEnoughMoneyException("Brak funduszy.");
        } else {
        	wallet.setMoney(ownedMoney.subtract(price));
        	wallet.addFundUnits(fund, unit, count);
            getWalletService().saveWallet(user, wallet);
        }
    }

    public void sellFundUnits(User user, Fund fund, FundUnit unit, int count) {
    	Wallet wallet = getWalletService().getWallet(user);
        int selledUnits = wallet.removeFundUnits(fund, unit, count);
        BigDecimal price = getFundUnitService().sell(fund, unit).multiply(BigDecimal.valueOf(selledUnits));
        wallet.setMoney(wallet.getMoney().add(price));
        getWalletService().saveWallet(user, wallet);
    }

    public FundUnitService getFundUnitService() {
        return fundUnitService;
    }

    public void setFundUnitService(FundUnitService fundUnitService) {
        this.fundUnitService = fundUnitService;
    }

	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}

	public WalletService getWalletService() {
		return walletService;
	}

}
