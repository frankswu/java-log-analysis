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
public interface LogReader extends Named{

	Log read(File file);
}
