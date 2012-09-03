package com.hit.myapp.infomanage.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hit.myapp.infomanage.dao.IUserDao;
import com.hit.myapp.infomanage.model.User;

/**
 * Description: This class implements the methods defined in the interface of IUserDao.
 */
public class UserDaoImpl implements IUserDao {

	private String path;
	private boolean isExists = false;

	private DocumentBuilder builder = null;
	private Document doc;

	/**
	 * Description: This constructor initialize the DocumentBuilder and the path of XML file.
	 * @param path the path of XML file
	 */
	public UserDaoImpl(String path) throws Exception {
		this.path = path;
		builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	/**
	 * Description: Read or create XML file to generate Document.
	 */
	public void createDoc() throws Exception {
		if (new File(path).exists()) {
			doc = builder.parse(path);
			isExists = true;
		} else {
			doc = builder.newDocument();
			isExists = false;
		}
	}

	/**
	 * Description: Add user information according to name, mobile and address.
	 * @param u  A domain model, indicating user's information, including name, mobile and address.
	 */
	public void doAdd(User u) throws Exception {
		createDoc();
		Element user = doc.createElement("user");
		Element name = doc.createElement("name");
		Element mobile = doc.createElement("mobile");
		Element address = doc.createElement("address");
		name.appendChild(doc.createTextNode(u.getName()));
		mobile.appendChild(doc.createTextNode(u.getMobile()));
		address.appendChild(doc.createTextNode(u.getAddress()));
		user.appendChild(name);
		user.appendChild(mobile);
		user.appendChild(address);
		if (!isExists) {
			Element users = doc.createElement("users");
			users.appendChild(user);
			doc.appendChild(users);
		} else {
			Element users = (Element) doc.getElementsByTagName("users").item(0);
			users.appendChild(user);
		}
		this.outputXml();
	}

	/**
	 * Description: Search for user information according to name or mobile or address, return matching user information list.
	 * @param input user's input cooresponding to tagName
	 * @param tagName the value only can be "name" or "mobile" or "address"
	 * @return matching user information list
	 */
	public List<User> search(String input, String tagName) throws Exception {
		createDoc();
		List<User> userList = new ArrayList<User>();
		NodeList nl = doc.getElementsByTagName("user");
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			Pattern p = Pattern.compile(input);
			Matcher m = p.matcher(e.getElementsByTagName(tagName).item(0)
					.getFirstChild().getNodeValue());
			if (m.matches()) {
				User u = new User();
				u.setName(e.getElementsByTagName("name").item(0)
						.getFirstChild().getNodeValue());
				u.setMobile(e.getElementsByTagName("mobile").item(0)
						.getFirstChild().getNodeValue());
				u.setAddress(e.getElementsByTagName("address").item(0)
						.getFirstChild().getNodeValue());
				userList.add(u);
			}
		}
		return userList;
	}

	/**
	 * Description: Delete user information according to name or mobile or address, return the number of user deleted.
	 * @param input user's input cooresponding to tagName
	 * @param tagName the value only can be "name" or "mobile" or "address"
	 * @return the number of user deleted
	 */
	public int doDel(String input, String tagName) throws Exception {
		createDoc();
		int count = 0;
		NodeList nl = doc.getElementsByTagName("user");
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			Pattern p = Pattern.compile(input);
			Matcher m = p.matcher(e.getElementsByTagName(tagName).item(0)
					.getFirstChild().getNodeValue());
			if (m.matches()) {
				e.getParentNode().removeChild(e);
				count++;
				i--;
			}
		}
		this.outputXml();
		return count;
	}

	/**
	 * Description: Search for the user according to name through function search(), return matching user information list.
	 * @param name user's name
	 * @return matching user information list
	 */
	public List<User> searchByName(String name) throws Exception {
		return search(name, "name");
	}

	/**
	 * Description: Search for the user according to mobile through function search(), return matching user information list.
	 * @param mobile user's mobile
	 * @return matching user information list
	 */
	public List<User> searchByMobile(String mobile) throws Exception {
		return search(mobile, "mobile");
	}

	/**
	 * Description: Search for the user according to address through function search(), return matching user information list.
	 * @param address user's address
	 * @return matching user information list
	 */
	public List<User> searchByAddress(String address) throws Exception {
		return search(address, "address");
	}
	
	/**
	 * Description: Delete user information according to name through function doDel(), return the number of user deleted.
	 * @param name user's name
	 * @return the number of user deleted
	 */
	public int doDelByName(String name) throws Exception {
		return doDel(name, "name");
	}

	/**
	 * Description: Delete user information according to mobile through function doDel(), return the number of user deleted.
	 * @param mobile user's mobile
	 * @return the number of user deleted
	 */
	public int doDelByMobile(String mobile) throws Exception {
		return doDel(mobile, "mobile");
	}

	/**
	 * Description: Delete user information according to address through function doDel(), return the number of user deleted.
	 * @param address user's address
	 * @return the number of user deleted
	 */
	public int doDelByAddress(String address) throws Exception {
		return doDel(address, "address");
	}

	/**
	 * Description: Output the results of add and delete operation to XML file.
	 */
	public void outputXml() throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		t.transform(source, result);
	}

}
