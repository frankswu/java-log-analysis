package org.buhe.loganalysis.common.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.buhe.loganalysis.core.LogAnalysis;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;
import org.buhe.loganalysis.interceptor.impl.SplitByThreadInterceptor;
import org.buhe.loganalysis.io.impl.OIOLogReader;
import org.buhe.loganalysis.io.impl.OIOLogWriter;


public class LogAnalysisTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<LogAnalysisInterceptor> interceptors = new ArrayList<LogAnalysisInterceptor>();
		interceptors.add(new SplitByThreadInterceptor());
		LogAnalysis la = new LogAnalysis(interceptors, new OIOLogReader(),
				new OIOLogWriter(), new File("d:/server.log"), new File(
						"d:/server2"));
		la.start();
	}

}
