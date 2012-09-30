package model.domain;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class WalletTest {
	
	private Wallet wallet;
	
	@Mock
	private Fund fund;
	
	@Before
	public void init() {
		initMocks(this);
		
		wallet = new Wallet();
		assertThat(wallet.getMoney()).isEqualTo(BigDecimal.valueOf(1000l).setScale(2));
	}
	
	@Test
	public void shouldAddFundUnitsToEmptyWallet() {
		int quantity = 1;
		assertThat(wallet.getItems().isEmpty()).isTrue();
		int result = wallet.addFundUnits(fund, FundUnit.AFundUnit, quantity);
		assertThat(result).isEqualTo(quantity);
		assertThat(wallet.getItems().isEmpty()).isFalse();
		assertThat(wallet.getItems().get(0).getFund()).isEqualTo(fund);
		assertThat(wallet.getItems().get(0).getFundUnit()).isEqualTo(FundUnit.AFundUnit);
		assertThat(wallet.getItems().get(0).getQuantity()).isEqualTo(quantity);
	
	}
	
	@Test
	public void shouldAddFundUnitsToWallet() {
		int firstQuantity = 1;
		int secondQuantity = 3;
		assertThat(wallet.getItems().isEmpty()).isTrue();
		wallet.addFundUnits(fund, FundUnit.AFundUnit, firstQuantity);
		int result = wallet.addFundUnits(fund, FundUnit.AFundUnit, secondQuantity);
		assertThat(result).isEqualTo(firstQuantity + secondQuantity);
		assertThat(wallet.getItems().isEmpty()).isFalse();
		assertThat(wallet.getItems().get(0).getFund()).isEqualTo(fund);
		assertThat(wallet.getItems().get(0).getFundUnit()).isEqualTo(FundUnit.AFundUnit);
		assertThat(wallet.getItems().get(0).getQuantity()).isEqualTo(firstQuantity + secondQuantity);
	}
	
	@Test
	public void shouldAddFundUnitsToWalletButWithDiffrentFundUnit() {
		int firstQuantity = 1;
		int secondQuantity = 3;
		assertThat(wallet.getItems().isEmpty()).isTrue();
		wallet.addFundUnits(fund, FundUnit.AFundUnit, firstQuantity);
		int result = wallet.addFundUnits(fund, FundUnit.BFundUnit, secondQuantity);
		assertThat(result).isEqualTo(secondQuantity);
		assertThat(wallet.getItems().isEmpty()).isFalse();
		assertThat(wallet.getItems().get(0).getFund()).isEqualTo(fund);
		assertThat(wallet.getItems().get(0).getFundUnit()).isEqualTo(FundUnit.AFundUnit);
		assertThat(wallet.getItems().get(0).getQuantity()).isEqualTo(firstQuantity);
		assertThat(wallet.getItems().get(1).getFund()).isEqualTo(fund);
		assertThat(wallet.getItems().get(1).getFundUnit()).isEqualTo(FundUnit.BFundUnit);
		assertThat(wallet.getItems().get(1).getQuantity()).isEqualTo(secondQuantity);
	}
	
	@Test
	public void shouldRemoveFundUnitsFromEmptyWallet() {
		int quantity = 1;
		int result = wallet.removeFundUnits(fund, FundUnit.AFundUnit, quantity);
		assertThat(result).isEqualTo(0);
		assertThat(wallet.getItems().isEmpty()).isTrue();
	}
	
	@Test
	public void shouldRemoveFundUnitsFromWallet() {
		int firstQuantity = 3;
		int secondQuantity = 1;
		wallet.addFundUnits(fund, FundUnit.AFundUnit, firstQuantity);
		int result = wallet.removeFundUnits(fund, FundUnit.AFundUnit, secondQuantity);
		assertThat(result).isEqualTo(secondQuantity);
		assertThat(wallet.getItems().isEmpty()).isFalse();
		assertThat(wallet.getItems().get(0).getQuantity()).isEqualTo(2);
	}
	
	@Test
	public void shouldRemoveMoreThenInWalletFundUnitsFromWallet() {
		int firstQuantity = 1;
		int secondQuantity = 3;
		wallet.addFundUnits(fund, FundUnit.AFundUnit, firstQuantity);
		int result = wallet.removeFundUnits(fund, FundUnit.AFundUnit, secondQuantity);
		assertThat(result).isEqualTo(1);
		assertThat(wallet.getItems().isEmpty()).isTrue();
	}
	
	@Test
	public void shouldRemoveFundUnitsFromWalletButWithDiffrentFundUnit() {
		int firstQuantity = 3;
		int secondQuantity = 3;
		int toRemove = 2;
		wallet.addFundUnits(fund, FundUnit.AFundUnit, firstQuantity);
		wallet.addFundUnits(fund, FundUnit.BFundUnit, secondQuantity);
		int result = wallet.removeFundUnits(fund, FundUnit.AFundUnit, toRemove);
		assertThat(result).isEqualTo(toRemove);
		assertThat(wallet.getItems().isEmpty()).isFalse();
		assertThat(wallet.getItems().get(0).getQuantity()).isEqualTo(1);
		assertThat(wallet.getItems().get(1).getQuantity()).isEqualTo(3);
	}

}
