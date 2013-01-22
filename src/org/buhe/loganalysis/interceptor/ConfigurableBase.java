/**
 * 
 */
package org.buhe.loganalysis.interceptor;

import java.util.Properties;

/**
 * @author buhe
 *
 */
public abstract class ConfigurableBase implements Configurable {

	Properties p = new Properties();

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.Configurable#setProperties(java.util.Properties)
	 */
	@Override
	public void setProperties(Properties p) {
		this.p = p;
	}

	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.interceptor.Configurable#getProperties()
	 */
	@Override
	public Properties getProperties() {
		return p;
	}
	
	public void addProperty(String key,String value){
		p.put(key, value);
	}

}
