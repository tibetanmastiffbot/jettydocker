package com.me.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PasswordTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeobfuscate() {
		Assert.assertEquals(Password.deobfuscate("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4"), "storepwd");
		Assert.assertEquals(Password.deobfuscate("OBF:1u2u1wml1z7s1z7a1wnl1u2g"), "keypwd");
	}

}
