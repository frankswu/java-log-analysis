/**
 * 
 */
package org.buhe.loganalysis.interceptor;

import org.buhe.loganalysis.common.Named;
import org.buhe.loganalysis.core.Log;


/**
 * 根据需求解析日志的拦截器
 * @author buhe
 *
 */
public interface LogAnalysisInterceptor extends Named{

	void handle(Log log);
	
}
