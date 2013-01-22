/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * 线程死锁分析
 * @author buhe
 *
 */
public class ThreadDumpDeadLockInterceptor implements LogAnalysisInterceptor {

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.common.Named#name()
	 */
	@Override
	public String name() {
		return "ThreadDumpDeadLock";
	}

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.LogAnalysisInterceptor#handle(com.npc.lte.tools.loganalysis.core.Log)
	 */
	@Override
	public void handle(Log log) {
		// TODO Auto-generated method stub

	}

}
