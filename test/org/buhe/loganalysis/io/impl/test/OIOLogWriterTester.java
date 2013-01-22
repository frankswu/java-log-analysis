/**
 * 
 */
package org.buhe.loganalysis.io.impl.test;

import java.io.File;

import org.buhe.loganalysis.common.test.LogTesterBase;
import org.buhe.loganalysis.io.impl.OIOLogWriter;
import org.junit.Before;
import org.junit.Test;


/**
 * @author buhe
 *
 */
public class OIOLogWriterTester extends LogTesterBase {

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
		OIOLogWriter w = new OIOLogWriter();
		w.write(log, file);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAgain() {
		File file = new File("D:/server.xx.yy");
		OIOLogWriter w = new OIOLogWriter();
		w.write(log, file);
	}

}
