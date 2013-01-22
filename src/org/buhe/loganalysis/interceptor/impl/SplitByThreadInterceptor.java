/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * @author buhe
 *
 */
public class SplitByThreadInterceptor implements LogAnalysisInterceptor {

	private final Map<String,ArrayList<String>> result = new HashMap<String,ArrayList<String>>();
	private final static Pattern STARTWITH_DATE = Pattern.compile("^\\d\\d:\\d\\d:\\d\\d.*");
	
	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.LogAnalysisInterceptor#handle(com.npc.lte.tools.loganalysis.io.Log)
	 */
	@Override
	public void handle(Log log) {
		List<String> content = log.getContent();
		String currentThreadName = null;
		for(String line : content){
			if(!matchDate(line)){ 
				if(currentThreadName == null){
					System.out.println("CurrentThreadName is NULL");
					continue;
				}
				result.get(currentThreadName).add(line);
				continue;
			}
			int start = line.indexOf("(");
			int end = line.indexOf(")");
			if(start == -1 || end == -1) continue;
			String threadName = line.substring(start, end + 1);
			currentThreadName = threadName;
			ArrayList<String> list;
			if((list = result.get(threadName)) != null){
				list.add(line);
			}else{
				list = new ArrayList<String>(200);
				list.add(line);
				result.put(threadName, list);
			}
		}
		//help GC
		log.setContent(null);
		
		List<Log> logsResult = new ArrayList<Log>(result.size());
		for(Map.Entry<String,ArrayList<String>>  entry : result.entrySet()){
			Log _log = new Log(log.getName());
			_log.appendName(entry.getKey());
			_log.setContent(entry.getValue());
			logsResult.add(_log);
		}
		
		log.setResult(logsResult);
	}

	@Override
	public String name() {
		return "SplitByThread";
	}
	
	private boolean matchDate(String line){
		return STARTWITH_DATE.matcher(line).find();
	}

}
