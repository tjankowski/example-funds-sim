package model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;

/**
 * User wallet.
 * @author Tomasz Jankowski
 */
@Entity
@Table(name="Wallets")
public class Wallet implements Serializable {
    
    private static final long serialVersionUID = 8338251771312465614L;

    private BigDecimal money = new BigDecimal(1000);
    private List<WalletItem> items = new ArrayList<WalletItem>();

    public Wallet() {
        this.money = this.money.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Wallet(BigDecimal money) {
        this.setMoney(money);
    }

    public int addFundUnits(Fund fund, FundUnit unit, int count) {
        for (WalletItem item : items) {
            if (item.getFund().equals(fund) && item.getFundUnit().equals(unit)) {
                item.setQuantity(count + item.getQuantity());
                return item.getQuantity();
            }
        }
        WalletItem item = new WalletItem();
        item.setFund(fund);
        item.setFundUnit(unit);
        item.setQuantity(count);
        items.add(item);
        return count;
    }

    public int removeFundUnits(Fund fund, FundUnit unit, int count) {
        WalletItem walletItemToDelete = null;
        int deletedQuantity = 0;
        for (WalletItem item : items) {
            if (item.getFund().equals(fund) && item.getFundUnit().equals(unit)) {
                int owned = item.getQuantity();
                if (count >= owned) {
                    walletItemToDelete = item;
                    deletedQuantity=owned;
                    break;
                } else {
                   item.setQuantity(owned - count);
                   deletedQuantity = count;
                }
            }
        }
        items.remove(walletItemToDelete);
        return deletedQuantity;
    }

    /**
     * Returns user money.
     * @return the user money
     */
    @Column(name = "money")
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * Sets user money.
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
        this.money = this.money.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Returns user wallet items.
     * @return the user wallet items
     */
    @OneToMany(cascade=CascadeType.ALL, mappedBy="wallet", fetch=FetchType.EAGER, orphanRemoval=true)
    public List<WalletItem> getItems() {
        return items;
    }

    /**
     * Sets user user wallet items.
     * @param items the user wallet items to set
     */
    public void setItems(List<WalletItem> items) {
        this.items = items;
    }

}
