/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.framework.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 文件实用类，文件的delete和copy.
 * 所有的文件大小以4096 bytes为单位.
 * 参考Spring的FileCopyUtils和FileSystemUtils类
 * @author gaof
 * @since 2011-4-1
 */
public abstract class FileUtils {
    /**
     * 操作的基本块
     */
	public static final int BUFFER_SIZE = 4096;

	/**
	 * 把给定的文件copy到目的文件.
	 * @param in 源文件
	 * @param out 目的文件
	 * @return 返回实际copy字节数
	 * @throws IOException 输入输出异常
	 */
	public static int copy(File in, File out) throws IOException {
		Assert.notNull(in,  "没有指定源文件");
		Assert.notNull(out, "没有指定目标文件");
		return copy(new BufferedInputStream(new FileInputStream(in)),
		    new BufferedOutputStream(new FileOutputStream(out)));
	}
	/**
	 * 把给定的源流的内容copy到目的流.
	 * @param in 源文件
	 * @param out 目的文件
	 * @return 返回实际copy字节数
	 * @throws IOException 输入输出异常
	 */
	public static int copy(InputStream in, OutputStream out) throws IOException {
		Assert.notNull(in, "源流对象没有指定！");
		Assert.notNull(out, "目标流对象没有指定");
		//实际拷贝
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		}
		finally {
			//最后关闭资源
			try {
				in.close();
			}
			catch (IOException ex) {
			}
			try {
				out.close();
			}
			catch (IOException ex) {
			}
		}
	}


	/**
	 * 把给定的源字符流的内容copy到目的字符流.
	 * @param in 源字符流
	 * @param out 目的字符流
	 * @return 返回实际copy字符数
	 * @throws IOException 输入输出异常
	 */
	public static int copy(Reader in, Writer out) throws IOException {
		Assert.notNull(in,  "没有指定源字符流");
		Assert.notNull(out, "没有指定目的字符流");
		try {
			int byteCount = 0;
			char[] buffer = new char[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			//刷新流
			out.flush();
			return byteCount;
		}
		finally {
			//关闭资源流
			try {
				in.close();
			}
			catch (IOException ex) {
			}
			try {
				out.close();
			}
			catch (IOException ex) {
			}
		}
	}
	/**
	 * 对文件夹进行递归删除,对文件直接删除.
	 * @param root 传递的根文件夹或文件
	 * @return 如果删除成功，则返回true，否则返回false
	 */
	public static boolean deleteRecursively(File root) {
		if (root != null && root.exists()) {
			if (root.isDirectory()) {
				File[] children = root.listFiles();
				if (children != null) {
					for (int i = 0; i < children.length; i++) {
						deleteRecursively(children[i]);
					}
				}
			}
			return root.delete();
		}
		return false;
	}

	/**
	 * 递归的把源文件或源文件夹拷贝到目标文件或文件夹中.
	 * to the <code>dest</code> file/directory.
	 * @param src  源文件夹
	 * @param dest 目标文件夹
	 * @throws IOException 抛出异常
	 */
	public static void copyRecursively(File src, File dest) throws IOException {
		Assert.isTrue(src != null && (src.isDirectory() || src.isFile()), "源文件必须是一个文件或文件夹！");
		Assert.notNull(dest, "目标文件不能为空！");
		doCopyRecursively(src, dest);
	}

	/**
	 *把源文件或源文件夹拷贝到目标文件或文件夹中.
	 * @param src  源文件夹
	 * @param dest 目标文件夹
	 * @throws IOException 抛出异常
	 */
	private static void doCopyRecursively(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			//创建文件夹
			dest.mkdir();
			File[] entries = src.listFiles();
			if (entries == null) {
				throw new IOException("不能在文件夹中列举文件: " + src);
			}
			for (int i = 0; i < entries.length; i++) {
				File file = entries[i];
				doCopyRecursively(file, new File(dest, file.getName()));
			}
		}
		else if (src.isFile()) {
			try {
				dest.createNewFile();
			}
			catch (IOException ex) {
				IOException ioex = new IOException("创建文件失败: " + dest);
				ioex.initCause(ex);
				throw ioex;
			}
			FileUtils.copy(src, dest);
		}
		else {
			// 特殊的文件处理，既不是文件也不是文件夹.
		}
	}
	public static void main(String[] args)throws Exception {

	}

}
