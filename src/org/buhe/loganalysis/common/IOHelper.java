/**
 * 
 */
package org.buhe.loganalysis.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;

/**
 * @author buhe
 *
 */
public class IOHelper {
	
	public static void safeClose(InputStream i){
		if(i != null){
			try {
				i.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(Reader i){
		if(i != null){
			try {
				i.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(OutputStream i){
		if(i != null){
			try {
				i.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(Writer i){
		if(i != null){
			try {
				i.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(RandomAccessFile i){
		if(i != null){
			try {
				i.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(FileChannel i){
		if(i != null){
			try {
				i.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
