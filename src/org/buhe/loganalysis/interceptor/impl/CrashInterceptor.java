/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * 分析Crash日志,主要分析是否OOME,其他的比较难分析,慢慢完善
 * @author buhe
 *
 */
public class CrashInterceptor implements LogAnalysisInterceptor {

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.common.Named#name()
	 */
	@Override
	public String name() {
		return "Crash";
	}

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.LogAnalysisInterceptor#handle(com.npc.lte.tools.loganalysis.core.Log)
	 */
	@Override
	public void handle(Log log) {
		// TODO Auto-generated method stub

	}

}
