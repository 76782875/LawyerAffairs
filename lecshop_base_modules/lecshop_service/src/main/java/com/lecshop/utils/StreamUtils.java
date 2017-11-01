package com.lecshop.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流转化工具类
 * @author dujinkai
 *
 */
public class StreamUtils 
{
	private static StreamUtils streamUtils = new StreamUtils();
	
	private StreamUtils()
	{
		
	}
	
	public static StreamUtils getInstance()
	{
		return streamUtils;
	}
	
	/**
	 * 将输入流转化成字节数组
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public final byte [] stream2Bytes(InputStream is) throws IOException
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1204*100];  
        
        int length = 0;
        
        while(-1 !=(length=is.read(buffer)))
        {
     	   baos.write(buffer, 0, length);
        }
        
        return baos.toByteArray();
	}
}
