package com.ljheee.os.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 控制台处理工具箱
 * 
 */
public final class CmdToolkit {

	/**
	 * 读取控制命令的输出结果
	 * 
	 * @param cmd
	 *            命令：String，比如可以是“notepad”
	 * @param isPrettify
	 *            返回的结果是否进行美化（换行），美化意味着换行，默认不进行美化,当此参数为null时也不美化，
	 * @return 控制命令的输出结果
	 * @throws IOException
	 */
	public static String readConsole(String cmd, Boolean isPrettify) throws IOException {
		StringBuffer cmdout = new StringBuffer();
		Process process = Runtime.getRuntime().exec(cmd); // 执行一个系统命令
		InputStream fis = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		
		if (isPrettify == null || isPrettify) {
			while ((line = br.readLine()) != null) {
				cmdout.append(line);
			}
		} else {
			while ((line = br.readLine()) != null) {
				cmdout.append(line).append(System.getProperty("line.separator"));
			}
		}
		
		return cmdout.toString().trim();
	}

}
