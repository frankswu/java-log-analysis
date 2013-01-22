/**
 * 
 */
package org.buhe.loganalysis.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author buhe
 *
 */
public class Log {

	private String name = "";
	
	private List<String> content = new ArrayList<String>();
	
	private List<Log> result;
	
	public Log(String name){
		this.name = name;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public List<Log> getResult() {
		return result;
	}

	public void setResult(List<Log> result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public Log appendName(String name) {
		name = name.replaceAll("\"|:|\\*|\\\\|<|>", "");
		this.name = this.name + "'''" + name;
		return this;
	}
	
	
}
