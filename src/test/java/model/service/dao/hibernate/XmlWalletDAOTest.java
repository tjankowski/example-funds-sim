package model.service.dao.hibernate;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.File;
import java.io.IOException;

import model.domain.User;
import model.domain.Wallet;
import model.domain.WalletItem;
import model.domain.fund.Fund;
import model.service.dao.xml.XmlWalletDAO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import com.google.common.io.Files;
import com.thoughtworks.xstream.XStream;

public class XmlWalletDAOTest {
	
	private static File FILES_DIR;
	
	private XmlWalletDAO dao;
	private String walletFilePath;
	@Mock
	private XStream xStream;
	
	@BeforeClass
	public static void beforeClass() {
		FILES_DIR = Files.createTempDir();
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		if (FILES_DIR != null && FILES_DIR.exists()) {
			Files.deleteRecursively(FILES_DIR);
		}
	}
	
	@Before
	public void init() {
		initMocks(this);
		
		walletFilePath = FILES_DIR.getAbsolutePath();
		
		dao = new XmlWalletDAO();
		dao.setWalletFilePath(walletFilePath);
		dao.setxStream(xStream);
	}
	
	@Test
	public void shouldThrowExceptionGetWallet() {
		String userPath = RandomStringUtils.random(10);
		Wallet wallet =  mock(Wallet.class);
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		when(xStream.fromXML(argumentCaptor.capture())).thenReturn(null);
		
		Wallet result = dao.getWallet(userPath);
		assertThat(result).isNull();
		verify(xStream).processAnnotations(new Class[]{Wallet.class, WalletItem.class, Fund.class});
		verifyNoMoreInteractions(xStream, wallet);
	}
	
	@Test
	public void shouldGetWallet() throws IOException {
		String userPath = RandomStringUtils.random(10);
		File file = new File(walletFilePath + File.separator + userPath);
		FileUtils.writeStringToFile(file, userPath);
		
		Wallet wallet =  mock(Wallet.class);
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		when(xStream.fromXML(argumentCaptor.capture())).thenReturn(wallet);
		
		Wallet result = dao.getWallet(userPath);
		assertThat(result).isEqualTo(wallet);
		verify(xStream).processAnnotations(new Class[]{Wallet.class, WalletItem.class, Fund.class});
		verify(xStream).fromXML(argumentCaptor.capture());
		verifyNoMoreInteractions(xStream, wallet);
	}
	
	@Test
	public void shouldSaveWallet() throws IOException {
		String userPath = RandomStringUtils.random(10);
		File file = new File(walletFilePath + File.separator + userPath);
		String xml = RandomStringUtils.random(50);
		
		Wallet wallet =  mock(Wallet.class);
		User user =  mock(User.class);
		when(user.getWalletFilePath()).thenReturn(userPath);
		when(xStream.toXML(wallet)).thenReturn(xml);
		
		dao.saveWallet(user, wallet);
		assertThat(FileUtils.sizeOf(file)).isGreaterThan(0l);
		assertThat(FileUtils.readFileToString(file)).isEqualTo(xml);
		verify(xStream).processAnnotations(new Class[]{Wallet.class, WalletItem.class, Fund.class});
		verify(xStream).toXML(wallet);
		verifyNoMoreInteractions(xStream, wallet);
	}

}
