/**
 * 
 */
package org.buhe.loganalysis.io.impl.test;

import java.io.File;

import junit.framework.Assert;

import org.buhe.loganalysis.common.test.LogTesterBase;
import org.buhe.loganalysis.io.impl.NIOLogWriter;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class NIOLogWriterTester extends LogTesterBase {

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.common.test.LogTesterBase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() {
		File file = new File("D:/server.xx.yy");
		NIOLogWriter w = new NIOLogWriter();
		w.write(log, file);
		
		Assert.assertEquals(true, file.exists());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAgain() {
		File file = new File("D:/server.xx.yy");
		NIOLogWriter w = new NIOLogWriter();
		w.write(log, file);
		
		Assert.assertEquals(true, file.exists());
	}

}
