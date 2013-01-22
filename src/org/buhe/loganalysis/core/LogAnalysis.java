/**
 * 
 */
package org.buhe.loganalysis.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;
import org.buhe.loganalysis.io.LogReader;
import org.buhe.loganalysis.io.LogWriter;


/**
 * @author buhe
 * 
 */
public class LogAnalysis {
	List<LogAnalysisInterceptor> interceptors;
	LogReader reader;
	LogWriter writer;
	File input;
	File outputDir;

	public LogAnalysis(List<LogAnalysisInterceptor> interceptors,
			LogReader reader, LogWriter writer, File input, File outputDir) {
		super();
		this.interceptors = interceptors;
		this.reader = reader;
		this.writer = writer;
		this.input = input;
		this.outputDir = outputDir;
	}

	public void start() {
		Log source = reader.read(input);
		List<Log> sources = new ArrayList<Log>();
		sources.add(source);
		for (LogAnalysisInterceptor i : interceptors) {
			List<Log> target = new ArrayList<Log>();
			for (Log log : sources) {
				i.handle(log);
				target.addAll(log.getResult());
			}
			sources = target;
		}
		for (Log log : sources) {
			writer.write(log, new File(outputDir, log.getName()));
		}
	}
}
