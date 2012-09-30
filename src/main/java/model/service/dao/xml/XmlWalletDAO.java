package model.service.dao.xml;

import java.io.File;
import java.io.IOException;

import model.domain.User;
import model.domain.Wallet;
import model.domain.WalletItem;
import model.domain.fund.Fund;
import model.service.dao.WalletDAO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

/**
 * Xml {@link WalletDAO} implementation using XStream.
 * @author Tomasz Jankowski
 *
 */
public class XmlWalletDAO implements WalletDAO {
	
	private static final Logger LOG = Logger.getLogger(XmlWalletDAO.class);
	
	private String walletFilePath;
	private XStream xStream;
	
	public Wallet getWallet(String userPath) {
		getxStream().processAnnotations(new Class[]{Wallet.class, WalletItem.class, Fund.class});
		File file = new File(getFilePath(userPath));
		String xml = null;
		try {
			xml = FileUtils.readFileToString(file);
		} catch (IOException e) {
			LOG.error("File I/O operations exception", e);
			return null;
		}
		Wallet wallet = (Wallet)getxStream().fromXML(xml);
		return wallet;
	}

	public void saveWallet(User user, Wallet wallet) {
		getxStream().processAnnotations(new Class[]{Wallet.class, WalletItem.class, Fund.class});
		String xml = getxStream().toXML(wallet);
		try {
			FileUtils.writeStringToFile(new File(getFilePath(user.getWalletFilePath())), xml);
		} catch (IOException e) {
			LOG.error("File I/O operations exception", e);
		}
	}
	
	private String getFilePath(String userPath) {
		StringBuffer filePath = new StringBuffer(getWalletFilePath());
		filePath.append(File.separator);
		filePath.append(userPath);
		return filePath.toString();
	}

	public void setWalletFilePath(String walletFilePath) {
		this.walletFilePath = walletFilePath;
	}

	public String getWalletFilePath() {
		return walletFilePath;
	}

	public void setxStream(XStream xStream) {
		this.xStream = xStream;
	}

	public XStream getxStream() {
		return xStream;
	}

}
