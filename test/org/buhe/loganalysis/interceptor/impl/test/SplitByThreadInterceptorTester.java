/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl.test;

import junit.framework.Assert;

import org.buhe.loganalysis.common.test.LogTesterBase;
import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.impl.SplitByThreadInterceptor;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class SplitByThreadInterceptorTester extends LogTesterBase{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() {
		SplitByThreadInterceptor i = new SplitByThreadInterceptor();
		i.handle(log);
		Assert.assertEquals(3, log.getResult().size());
		for(Log l : log.getResult()){
			for(String line : l.getContent()){
				System.out.println(line);
			}
		}
	}

}
