/**
 * 
 */
package org.buhe.loganalysis.io.impl;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.buhe.loganalysis.common.IOHelper;
import org.buhe.loganalysis.core.Log;
import org.buhe.loganalysis.io.LogReader;


/**
 * @author buhe
 *
 */
public class NIOLogReader implements LogReader {

	char[] dist = new char[1024 * 128];
	/* (non-Javadoc)
	 * @see com.npc.lte.tools.loganalysis.io.LogReader#read(java.io.File)
	 */
	@Override
	public Log read(File file) {
		if(!file.exists()){
			throw new IllegalArgumentException("Input file NOT exists");
		}
		
		Charset utf_8 = Charset.forName("UTF-8");
		Log log = new Log(file.getName());
		List<String> content = new ArrayList<String>(10000);
		log.setContent(content);
		RandomAccessFile rFile = null;
		FileChannel fc = null;
		try {
			rFile = new RandomAccessFile(file,"r");
			fc = rFile.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 128);
			while(fc.read(buffer) != -1 ){
				buffer.flip();
				CharBuffer cb = utf_8.decode(buffer);
				int offset = 0;
				while(cb.hasRemaining()){
					char c = cb.get();
					if(c == Character.LETTER_NUMBER){ //TODO - 有bug,如果最后一个字符不是结尾会丢失一部分数据
						int length = cb.position() - 1 - offset;
						int newOffset = cb.position();
						cb.position(offset);
						cb.get(dist,0,length);
						content.add(String.valueOf(dist,0,length));
						cb.position(newOffset);
						offset = newOffset;
					}
				}
				buffer.clear();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOHelper.safeClose(rFile);
			IOHelper.safeClose(fc);
		}
		return log;
	}

	@Override
	public String name() {
		return "NIOLogReader";
	}

}
