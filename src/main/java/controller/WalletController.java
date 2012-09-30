/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.domain.User;
import model.domain.Wallet;
import model.domain.WalletItem;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;
import model.service.TransactionManager;
import model.service.UsersManager;
import model.service.WalletService;
import model.service.fund.FundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Wallet Controller to present user wallet.
 * @author Tomasz Jankowski
 */
@Controller
public class WalletController {

    //UsersManager for interaction with User.class objects
    @Autowired
    private UsersManager usersManager;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionManager transactionManager;
    @Autowired
    private FundService fundService;

    /**
     * Action presenting user wallet.
     * @param model model
     * @return view name
     */
    @RequestMapping(value = "/wallet.*", method = RequestMethod.GET)
    public String showWallet(Model model) {
        //Gets userName(login) from context
        SecurityContext ctx = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) ctx.getAuthentication().getPrincipal();
        //Gets user
        User user = getUsersManager().getUser(userDetails.getUsername());
        Wallet wallet = getWalletService().getWallet(user);
        model.addAttribute("user", user);
        model.addAttribute("wallet", wallet);
        return "/wallet";
    }

    /**
     * Sell fund unit action.
     * @param walletItemPosition wallet item position
     * @param count quantity to sell
     * @return view name
     */
    @RequestMapping(value = "/wallet.*", method = RequestMethod.POST)
    public String sellFundUnits(@RequestParam("item") int walletItemPosition, @RequestParam("count") int count) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) ctx.getAuthentication().getPrincipal();
        //Gets user
        User user = getUsersManager().getUser(userDetails.getUsername());
        Wallet wallet = getWalletService().getWallet(user);
        WalletItem walletItem = wallet.getItems().get(walletItemPosition);;
        Fund fund = getFundService().getFund(walletItem.getFund().getId());
        FundUnit unit = walletItem.getFundUnit();
        getTransactionManager().sellFundUnits(user, fund, unit, count);
        
        return "redirect:/wallet.html";
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public void setUsersManager(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}

	public WalletService getWalletService() {
		return walletService;
	}

	public void setFundService(FundService fundService) {
		this.fundService = fundService;
	}

	public FundService getFundService() {
		return fundService;
	}
}
