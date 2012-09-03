package com.hit.myapp.infomanage.service;

import java.util.Iterator;
import java.util.List;

import com.hit.myapp.infomanage.dao.IUserDao;
import com.hit.myapp.infomanage.dao.impl.UserDaoImpl;
import com.hit.myapp.infomanage.model.User;

/**
 * Description: This class is mainly to provide services for the upper.
 */
public class UserService {

	private IUserDao userDao;
	private final static String path = "ab.xml";

	public UserService() {
		try {
			userDao = new UserDaoImpl(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: Provide add service according to name, mobile and address, return whether operation is successful.
	 * @param name user's name
	 * @param mobile user's mobile
	 * @param address user's address
	 * @return indicate whether operation is successful
	 */
	public boolean doAdd(String name, String mobile, String address) {
		boolean flag = false;
		User u = new User();
		u.setName(name);
		u.setMobile(mobile);
		u.setAddress(address);
		try {
			userDao.doAdd(u);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1 address added");
		return flag;
	}

	/**
	 * Description: Provide search service according to name, return the number of results.
	 * @param name user's name
	 * @return the number of search results
	 */
	public int searchByName(String name) {
		List<User> userList = null;
		int resultLength = 0;
		try {
			userList = userDao.searchByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userList == null || userList.size() == 0) {
			System.out.println("no match result");
		} else {
			resultLength = userList.size();
			Iterator<User> iter = userList.iterator();
			while (iter.hasNext()) {
				User u = iter.next();
				System.out.println("name: " + u.getName());
				System.out.println("mobile: " + u.getMobile());
				System.out.println("address: " + u.getAddress());
			}
		}
		return resultLength;
	}

	/**
	 * Description: Provide search service according to mobile, return the number of results.
	 * @param mobile user's mobile
	 * @return the number of search results
	 */
	public int searchByMobile(String mobile) {
		List<User> userList = null;
		int resultLength = 0;
		try {
			userList = userDao.searchByMobile(mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userList == null || userList.size() == 0) {
			System.out.println("no match result");
		} else {
			resultLength = userList.size();
			Iterator<User> iter = userList.iterator();
			while (iter.hasNext()) {
				User u = iter.next();
				System.out.println("name: " + u.getName());
				System.out.println("mobile: " + u.getMobile());
				System.out.println("address: " + u.getAddress());
			}
		}
		return resultLength;
	}

	/**
	 * Description: Provide search service according to address, return the number of results.
	 * @param address user's address
	 * @return the number of search results
	 */
	public int searchByAddress(String address) {
		List<User> userList = null;
		int resultLength = 0;
		try {
			userList = userDao.searchByAddress(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userList == null || userList.size() == 0) {
			System.out.println("no match result");
		} else {
			resultLength = userList.size();
			Iterator<User> iter = userList.iterator();
			while (iter.hasNext()) {
				User u = iter.next();
				System.out.println("name: " + u.getName());
				System.out.println("mobile: " + u.getMobile());
				System.out.println("address: " + u.getAddress());
			}
		}
		return resultLength;
	}

	/**
	 * Description: Provide delete service according to name, return the number of data deleted .
	 * @param name user's name
	 * @return the number of data deleted
	 */
	public int doDelByName(String name) {
		int count = 0;
		try {
			count = userDao.doDelByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(count + " address entries deleted");
		return count;
	}

	/**
	 * Description: Provide delete service according to mobile, return the number of data deleted .
	 * @param mobile user's mobile number
	 * @return the number of data deleted
	 */
	public int doDelByMobile(String mobile) {
		int count = 0;
		try {
			count = userDao.doDelByMobile(mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(count + " address entries deleted");
		return count;
	}

	/**
	 * Description: Provide delete service according to address, return the number of data deleted .
	 * @param address user's address
	 * @return the number of data deleted
	 */
	public int doDelByAddress(String address) {
		int count = 0;
		try {
			count = userDao.doDelByAddress(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(count + " address entries deleted");
		return count;
	}

}
