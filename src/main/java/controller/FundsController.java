package controller;

import java.util.List;

import model.domain.User;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;
import model.exceptions.NotEnoughMoneyException;
import model.service.TransactionManager;
import model.service.UsersManager;
import model.service.fund.FundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Funds Controller to interact with funds.
 * @author Tomasz Jankowski
 */
@Controller
public class FundsController {

    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private UsersManager usersManager;

    @Autowired
    private FundService fundService;

    /**
     * Presents funds list to user.
     * @param model model
     * @return view name
     */
    @RequestMapping("/funds.*")
    public String showFunds(Model model) {
        List<Fund> funds = getFundService().getAllFunds();
        model.addAttribute("funds", funds);
        return "/funds";
    }

    /**
     * Presents single fund to user.
     * @param fundId fund identifier
     * @param model model
     * @return view name
     */
    @RequestMapping("/funds/{fundId}.*")
    public String showFund(@PathVariable long fundId, Model model) {
        Fund fund = getFundService().getFund(fundId);
        model.addAttribute("fund", fund);
        model.addAttribute("fundId", fundId);
        model.addAttribute("units", FundUnit.values());
        return "/fund";
    }

    /**
     * Buy fund unit action.
     * @param fundId fund identifier
     * @param unitName fund unit name
     * @param count quantity to buy
     * @return view name
     */
    @RequestMapping(value = "/funds/{fundId}.*", method = RequestMethod.POST)
    public String buyFundUnits(@PathVariable long fundId, @RequestParam("unitName") String unitName, @RequestParam("count") int count) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) ctx.getAuthentication().getPrincipal();
        //Gets user
        User user = getUsersManager().getUser(userDetails.getUsername());
        Fund fund = getFundService().getFund(fundId);
        FundUnit unit = FundUnit.valueOf(unitName);
        try {
            getTransactionManager().buyFundUnits(user, fund, unit, count);
        } catch (NotEnoughMoneyException ex) {
           return "redirect:/funds/"+fundId +".html?success=false";
        }
        return "redirect:/wallet.html";
    }

    public TransactionManager getTransactionManager() {
		return transactionManager;
	}
    
    public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public void setUsersManager(UsersManager userManager) {
        this.usersManager = userManager;
    }

    public FundService getFundService() {
        return fundService;
    }

    public void setFundService(FundService fundService) {
        this.fundService = fundService;
    }

}
