/**
 * 
 */
package org.buhe.loganalysis.common.test;

import java.util.ArrayList;
import java.util.List;

import org.buhe.loganalysis.core.Log;


/**
 * @author buhe
 * 
 */
public class LogTesterBase {

	protected Log log;

	public void setUp() throws Exception {
		log = new Log("");

		List<String> content = new ArrayList<String>();
		content.add("10:43:57,256 ERROR [stderr] (async-job-executor-6) 	at java.io.PrintStream.write(PrintStream.java:428)");
		content.add("10:43:57,653 ERROR [stderr] (async-job-executor-8) 	at java.io.PrintStream.println(PrintStream.java:755)");
		content.add("10:42:58,077 ERROR [stderr] (async-job-executor-6) 	at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:202)");
		content.add("  at org.jboss.as.ejb3.tx.CMTTxInterceptor.handleEndTransactionException(CMTTxInterceptor.java:115) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]");
		content.add("  at org.jboss.as.ejb3.tx.CMTTxInterceptor.endTransaction(CMTTxInterceptor.java:95) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]");
		content.add("  at org.jboss.as.ejb3.tx.CMTTxInterceptor.handleEndTransactionException(CMTTxInterceptor.java:115) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]");
		content.add("10:43:58,526 ERROR [stderr] (async-job-executor-9) 	at java.io.PrintStream.println(PrintStream.java:755)");
		content.add("10:44:58,935 ERROR [stderr] (async-job-executor-6) 	at sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:272)");
		content.add("10:43:59,398 ERROR [stderr] (async-job-executor-8) 	at org.jboss.stdio.StdioContext$DelegatingPrintStream.println(StdioContext.java:297)");
		content.add("10:43:59,927 ERROR [stderr] (async-job-executor-6) 	at sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:85)");
		log.setContent(content);

	}

}
