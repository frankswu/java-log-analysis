/**
 * 
 */
package org.buhe.loganalysis.common;

import java.util.ArrayList;
import java.util.List;

import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;
import org.buhe.loganalysis.interceptor.impl.RegularInterceptor;
import org.buhe.loganalysis.interceptor.impl.SplitByThreadInterceptor;
import org.buhe.loganalysis.interceptor.impl.SplitByTimeInterceptor;
import org.buhe.loganalysis.io.LogReader;
import org.buhe.loganalysis.io.LogWriter;
import org.buhe.loganalysis.io.impl.NIOLogReader;
import org.buhe.loganalysis.io.impl.NIOLogWriter;
import org.buhe.loganalysis.io.impl.OIOLogReader;
import org.buhe.loganalysis.io.impl.OIOLogWriter;


/**
 * @author buhe
 *
 */
public class ComponentContainer {

	static List<LogReader> allReader = new ArrayList<LogReader>();
	static List<LogWriter> allWriter = new ArrayList<LogWriter>();
	static List<LogAnalysisInterceptor> allInterceptor = new ArrayList<LogAnalysisInterceptor>();
	
	static{
		allReader.add(new OIOLogReader());
		allReader.add(new NIOLogReader());
		
		allWriter.add(new OIOLogWriter());
		allWriter.add(new NIOLogWriter());
		
		allInterceptor.add(new SplitByThreadInterceptor());
		allInterceptor.add(new SplitByTimeInterceptor());
		allInterceptor.add(new RegularInterceptor());

	}
	
	public static LogReader getLogReader(String name){
		return findComponent(allReader,name);
	}
	
	public static LogWriter getLogWriter(String name){
		return findComponent(allWriter,name);
	}
	
	public static LogAnalysisInterceptor getLogAnalysisInterceptor(String name){
		return findComponent(allInterceptor,name);
	}
	
	private static <T extends Named> T findComponent(List<T> components,String name){
		for(T c : components){
			if(c.name().equals(name)){
				return c;
			}
		}
		throw new IllegalArgumentException(name + " component is not exist.");
	}
	
	public static List<LogReader> getLogReaders(){
		return allReader;
	}
	
	public static List<LogWriter> getLogWriters(){
		return allWriter;
	}
	
	public static List<LogAnalysisInterceptor> getLogAnalysisInterceptors(){
		return allInterceptor;
	}
}
