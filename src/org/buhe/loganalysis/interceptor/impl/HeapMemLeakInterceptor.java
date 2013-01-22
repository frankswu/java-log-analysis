/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * 堆转存内存泄露分析
 * 
 * 列出com.npc 开头的所有超过一定阈值的对象
 * @author buhe
 *
 */
public class HeapMemLeakInterceptor implements LogAnalysisInterceptor {

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.common.Named#name()
	 */
	@Override
	public String name() {
		return "HeapMemLeak";
	}

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.LogAnalysisInterceptor#handle(com.npc.lte.tools.loganalysis.core.Log)
	 */
	@Override
	public void handle(Log log) {
		// TODO Auto-generated method stub

	}

}
