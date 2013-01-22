/**
 * 
 */
package org.buhe.loganalysis.interceptor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.interceptor.ConfigurableBase;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * 根据正则表达式匹配日志
 * @author buhe
 *
 */
public class RegularInterceptor extends ConfigurableBase implements
		LogAnalysisInterceptor {

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.common.Named#name()
	 */
	@Override
	public String name() {
		return "Regular";
	}

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.Configurable#getKeys()
	 */
	@Override
	public String[] getKeys() {
		return new String[]{"Pattern"};
	}

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.LogAnalysisInterceptor#handle(com.npc.lte.tools.loganalysis.core.Log)
	 */
	@Override
	public void handle(Log log) {
		String pattren = (String) this.getProperties().get("Pattern");
		Pattern regular = Pattern.compile(pattren);
		
		List<Log> result = new ArrayList<Log>();
		log.setResult(result);
		Log _log = new Log(log.getName());
		_log.appendName(pattren.replaceAll("\\\\", ";"));
		result.add(_log);
		
		List<String> content = log.getContent();
		for(String line : content){
			if(regular.matcher(line).find()){
				_log.getContent().add(line);
			}
		}
	}

}
