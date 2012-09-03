package com.hit.myapp.infomanage.service;

import static org.junit.Assert.fail;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class UserServiceTest {

	private UserService us = new UserService();

	@Test
	public void testDoAdd() {
		new File("ab.xml").delete();
		Assert.assertEquals(us.doAdd("王煜", "15244774064", "哈工大HIT"), true);
		Assert.assertEquals(us.doAdd("王羲之", "1244770001", "MIT"), true);
		Assert.assertEquals(us.doAdd("祖冲之", "8888888888", "MVC"), true);
		Assert.assertEquals(us.doAdd("马祖光", "82HIT42", "Hibernate"), true);
		Assert.assertEquals(us.doAdd("english", "4524514", "Struts2"), true);
		Assert.assertEquals(us.doAdd("wangzhe风范", "1112012000", "Spring"), true);
	}

	@Test
	public void testSearchByName() {
		Assert.assertEquals(us.searchByName("王.*"), 2);
		Assert.assertEquals(us.searchByName(".?祖.*"), 2);
		Assert.assertEquals(us.searchByName("郭德纲"), 0);
	}

	@Test
	public void testSearchByMobile() {
		Assert.assertEquals(us.searchByMobile(".*4477.*"), 2);
		Assert.assertEquals(us.searchByMobile(".*HIT.*"), 1);
		Assert.assertEquals(us.searchByMobile(".*2012"), 0);
	}

	@Test
	public void testSearchByAddress() {
		Assert.assertEquals(us.searchByAddress(".*IT"), 2);
		Assert.assertEquals(us.searchByAddress("S.*"), 2);
		Assert.assertEquals(us.searchByAddress(".*Ericssson.*"), 0);
	}

	@Test
	public void testDoDelByName() {
		Assert.assertEquals(us.doDelByName("郭德纲"), 0);
		Assert.assertEquals(us.doDelByName(".?祖.*"), 2);
	}

	@Test
	public void testDoDelByMobile() {
		Assert.assertEquals(us.doDelByMobile(".*2012"), 0);
		Assert.assertEquals(us.doDelByMobile(".*4477.*"), 2);
	}

	@Test
	public void testDoDelByAddress() {
		Assert.assertEquals(us.doDelByAddress(".*Ericsson.*"), 0);
		Assert.assertEquals(us.doDelByAddress("S.*"), 2);
	}

}
