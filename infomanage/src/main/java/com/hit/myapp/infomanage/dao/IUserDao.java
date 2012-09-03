package com.hit.myapp.infomanage.dao;

import java.util.List;

import com.hit.myapp.infomanage.model.User;

/**
 * Description: This interface defines some methods to execute user's commands.
 */
public interface IUserDao {

	/**
	 * Description: Add user information according to name, mobile and address.
	 * @param u  A domain model, indicating user's information, including name, mobile and address.
	 */
	public void doAdd(User u) throws Exception;

	/**
	 * Description: Search for the user according to name, return matching user information list.
	 * @param name user's name
	 * @return matching user information list
	 */
	public List<User> searchByName(String name) throws Exception;

	/**
	 * Description: Search for the user according to mobile, return matching user information list.
	 * @param mobile user's mobile
	 * @return matching user information list
	 */
	public List<User> searchByMobile(String mobile) throws Exception;

	/**
	 * Description: Search for the user according to address, return matching user information list.
	 * @param address user's address
	 * @return matching user information list
	 */
	public List<User> searchByAddress(String address) throws Exception;

	/**
	 * Description: Delete user information according to name, return the number of user deleted.
	 * @param name user's name
	 * @return the number of user deleted
	 */
	public int doDelByName(String name) throws Exception;

	/**
	 * Description: Delete user information according to mobile, return the number of user deleted.
	 * @param mobile user's mobile
	 * @return the number of user deleted
	 */
	public int doDelByMobile(String mobile) throws Exception;

	/**
	 * Description: Delete user information according to address, return the number of user deleted.
	 * @param address user's address
	 * @return the number of user deleted
	 */
	public int doDelByAddress(String address) throws Exception;

}
