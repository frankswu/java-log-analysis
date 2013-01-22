/**
 * 
 */
package org.buhe.loganalysis.io;

import java.io.File;

import org.buhe.loganalysis.common.Named;
import org.buhe.loganalysis.core.Log;


/**
 * @author buhe
 *
 */
public interface LogWriter extends Named{

	void write(Log log,File file);
}
