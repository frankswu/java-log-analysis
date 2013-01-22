/**
 * 
 */
package org.buhe.loganalysis.common.test;

import junit.framework.Assert;

import org.buhe.loganalysis.core.Log;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class LogTester {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Log log = new Log("");
		log.appendName("xxx:xxxx");
		Assert.assertEquals("'''xxxxxxx", log.getName());
	}
	
	@Test
	public void test2() {
		Log log = new Log("");
		log.appendName("xxx\"xxxx");
		Assert.assertEquals("'''xxxxxxx", log.getName());
	}

}
