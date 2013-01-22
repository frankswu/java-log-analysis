/**
 * 
 */
package org.buhe.loganalysis.interceptor;

import java.util.Properties;

/**
 * @author buhe
 *
 */
public interface Configurable {

	String[] getKeys();
	
	void setProperties(Properties p);
	
	Properties getProperties();
	
	void addProperty(String key,String value);
}
