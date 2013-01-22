/**
 * 
 */
package org.buhe.loganalysis.io.impl.test;

import java.io.File;

import junit.framework.Assert;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.io.impl.OIOLogReader;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class OIOLogReaderTester {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		OIOLogReader r = new OIOLogReader();
		Log log = r.read(new File("D:/server.log"));
		Assert.assertNotSame(0, log.getContent().size()) ;
		System.out.println(log.getContent().size());
	}

}
