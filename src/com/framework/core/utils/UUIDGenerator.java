package com.framework.core.utils;

import java.net.InetAddress;
/**
 * 主键生成器,采用Hibernate的UUID的方式
 * @author gaofeng
 *
 */
public class UUIDGenerator {
	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt( InetAddress.getLocalHost().getAddress() );
		}
		catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );

	public UUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class
	 * in the same quater second - very unlikely)
	 */
	protected static int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there
	 * are > Short.MAX_VALUE instances created in a millisecond)
	 */
	protected  static short getCount() {
		synchronized(UUIDGenerator.class) {
			if (counter<0) counter=0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected static int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected static short getHiTime() {
		return (short) ( System.currentTimeMillis() >>> 32 );
	}
	protected static int getLoTime() {
		return (int) System.currentTimeMillis();
	}
	public static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	

	protected static String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace( 8-formatted.length(), 8, formatted );
		return buf.toString();
	}

	protected static String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace( 4-formatted.length(), 4, formatted );
		return buf.toString();
	}
	/**
	 * 生成序列
	 * @param session
	 * @param obj
	 * @return
	 */	
	public  static String randomId() {
		return new StringBuffer(36)
			.append( format( getIP() ) )
			.append( format( getJVM() ) )
			.append( format( getHiTime()) )
			.append( format( getLoTime() ))
			.append( format( getCount() ) )
			.toString();
	}
}
