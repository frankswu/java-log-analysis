/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl.test;

import junit.framework.Assert;

import org.buhe.loganalysis.common.test.LogTesterBase;
import org.buhe.loganalysis.interceptor.impl.RegularInterceptor;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class RegularInterceptorTester extends LogTesterBase {

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.common.test.LogTesterBase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() {
		RegularInterceptor i = new RegularInterceptor();
		
		i.addProperty("Pattern", "^ .*");
		
		i.handle(log);
		
		Assert.assertEquals(3, log.getResult().get(0).getContent().size());
	}
	
	@Test
	public void test2() {
		RegularInterceptor i = new RegularInterceptor();
		
		i.addProperty("Pattern", ".*");
		
		i.handle(log);
		
		Assert.assertEquals(10, log.getResult().get(0).getContent().size());
	}

}
