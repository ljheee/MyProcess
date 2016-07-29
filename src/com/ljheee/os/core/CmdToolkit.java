package com.ljheee.os.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ����̨��������
 * 
 */
public final class CmdToolkit {

	/**
	 * ��ȡ���������������
	 * 
	 * @param cmd
	 *            ���String����������ǡ�notepad��
	 * @param isPrettify
	 *            ���صĽ���Ƿ�������������У���������ζ�Ż��У�Ĭ�ϲ���������,���˲���ΪnullʱҲ��������
	 * @return ���������������
	 * @throws IOException
	 */
	public static String readConsole(String cmd, Boolean isPrettify) throws IOException {
		StringBuffer cmdout = new StringBuffer();
		Process process = Runtime.getRuntime().exec(cmd); // ִ��һ��ϵͳ����
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
