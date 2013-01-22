/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.ConfigurableBase;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * 
 * @author buhe
 * 
 */
public class SplitByTimeInterceptor extends ConfigurableBase implements
		LogAnalysisInterceptor {

	private final static Pattern STARTWITH_DATE = Pattern
			.compile("^\\d\\d:\\d\\d:\\d\\d.*");

	@Override
	public void handle(Log log) {
		String start = (String) this.getProperties().get("StartTime");
		String end = (String) this.getProperties().get("EndTime");
		if(start == null || end == null){
			throw new IllegalArgumentException("Start and End Time NOT null");
		}
		List<Log> result = new ArrayList<Log>();
		log.setResult(result);
		Log _log = new Log(log.getName());
		_log.appendName(start + "+").appendName(end);
		result.add(_log);
		
		List<String> content = log.getContent();
		String currentDate = null;
		boolean found = false;
		for (String line : content) {
			if (!matchDate(line)) {
				if (currentDate == null) {
					System.out.println("CurrentThreadName is NULL");
					continue;
				}
				if (found) {
					_log.getContent().add(line);
				}
				continue;
			}
			Matcher m = STARTWITH_DATE.matcher(line);
			m.find();
			String date = m.group().substring(0, 8);
			if (bigger(date, start) && bigger(end, date)) {
				found = true;
				_log.getContent().add(line);
			} else {
				found = false;
			}
		}

	}

	@Override
	public String name() {
		return "SplitByTime";
	}

	@Override
	public String[] getKeys() {
		return new String[] { "StartTime", "EndTime" };
	}

	private boolean matchDate(String line) {
		return STARTWITH_DATE.matcher(line).find();
	}

	public boolean bigger(String b, String s) {
		String[] b_split = b.split(":");
		String[] s_split = s.split(":");
		int b_second = Integer.parseInt(b_split[0])* 3600 + Integer.parseInt(b_split[1]) * 60 +  Integer.parseInt(b_split[2]);
		int s_second = Integer.parseInt(s_split[0])* 3600 + Integer.parseInt(s_split[1]) * 60 +  Integer.parseInt(s_split[2]);
		return b_second > s_second;
	}

}
