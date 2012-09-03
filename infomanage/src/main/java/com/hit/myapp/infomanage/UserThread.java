package com.hit.myapp.infomanage;

import java.util.Scanner;

import com.hit.myapp.infomanage.service.UserService;

/**
 * Description: This class is mainly to interact with users and call the corresponding service.
 */
public class UserThread implements Runnable {

	private Scanner s = new Scanner(System.in);
	private UserService us = new UserService();

	/**
	 * Description: deal with commands
	 */
	public void run() {
		String command = s.next();
		if (command.equals("add")) {
			dealAdd();
		} else if (command.equals("search")) {
			dealSearch();
		} else if (command.equals("delete")) {
			dealDel();
		} else if (command.equals("!help")) {
			dealHelp();
			run();
		} else if (command.equals("!quit")) {
			return;
		} else {
			System.out.println("Invalid command!");
			dealHelp();
			run();
		}
	}

	/**
	 * Description: Call the add service.
	 */
	public void dealAdd() {
		System.out.print("name: ");
		String name = s.next();
		System.out.print("mobile: ");
		String mobile = s.next();
		System.out.print("address: ");
		String address = s.next();
		us.doAdd(name, mobile, address);
		this.run();
	}

	/**
	 * Description: Call the search service.
	 */
	public void dealSearch() {
		System.out.print("by (name|mobile|address): ");
		String searchCondition = s.next();

		if (searchCondition.equals("name")) {
			System.out.print("name: ");
			String name = s.next();
			us.searchByName(name);
			this.run();
		} else if (searchCondition.equals("mobile")) {
			System.out.print("mobile: ");
			String mobile = s.next();
			us.searchByMobile(mobile);
			this.run();
		} else if (searchCondition.equals("address")) {
			System.out.print("address: ");
			String address = s.next();
			us.searchByAddress(address);
			this.run();
		} else {
			System.out.println("InValid input!");
			dealHelp();
			dealSearch();
		}
	}

	/**
	 * Description: Call the delete service.
	 */
	public void dealDel() {
		System.out.print("by (name|mobile|address): ");
		String searchCondition = s.next();

		if (searchCondition.equals("name")) {
			System.out.print("name: ");
			String name = s.next();
			us.doDelByName(name);
			this.run();
		} else if (searchCondition.equals("mobile")) {
			System.out.print("mobile: ");
			String mobile = s.next();
			us.doDelByMobile(mobile);
			this.run();
		} else if (searchCondition.equals("address")) {
			System.out.print("address: ");
			String address = s.next();
			us.doDelByAddress(address);
			this.run();
		} else {
			System.out.println("InValid input!");
			dealHelp();
			dealDel();
		}
	}
	/**
	 * Description: Prompt message.
	 */
	public void dealHelp() {
		System.out.println("command only can be: ");
		System.out.println("add: add user information");
		System.out.println("---argument: name|mobile|address");
		System.out.println("search: search user information");
		System.out.println("---argument: name|mobile|address");
		System.out.println("delete: delete user information");
		System.out.println("---argument: name|mobile|address");
		System.out.println("!help: help information");
		System.out.println("!quit: exit the program");
	}

}
