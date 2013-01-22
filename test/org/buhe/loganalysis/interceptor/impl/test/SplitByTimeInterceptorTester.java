/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl.test;

import java.util.Properties;

import junit.framework.Assert;

import org.buhe.loganalysis.common.test.LogTesterBase;
import org.buhe.loganalysis.interceptor.impl.SplitByTimeInterceptor;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class SplitByTimeInterceptorTester extends LogTesterBase{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() {
		SplitByTimeInterceptor i = new SplitByTimeInterceptor();
		Assert.assertEquals(true, i.bigger("22:11:11", "11:22:33"));  
		Assert.assertEquals(false, i.bigger("22:11:11", "23:22:33"));  
		Assert.assertEquals(false, i.bigger("22:11:11", "22:22:33"));
		Assert.assertEquals(true, i.bigger("22:11:21", "22:11:02")); 
		Assert.assertEquals(false, i.bigger("11:22:21", "22:11:02")); 
	}
	
	@Test
	public void test2() {
		SplitByTimeInterceptor i = new SplitByTimeInterceptor();
		Properties p = new Properties();
		p.put("StartTime", "10:42:59");
		p.put("EndTime", "10:44:44");
		i.setProperties(p );
		i.handle(log);
		Assert.assertEquals(5,log.getResult().get(0).getContent().size());
	}

}
