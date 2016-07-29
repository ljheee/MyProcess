package com.ljheee.os.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * MyProcessUtil工具类
 * 
 * @author ljheee
 *
 */
public class MyProcessUtil {

	/**
	 * 返回所有进程[map]
	 * @return
	 */
	public static Map processList() {
		int count = 0;
		BufferedReader br = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		try {
			Process proc = Runtime.getRuntime().exec("tasklist");
			br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));
			// System.out.println("正在运行的进程信息:");
			String line = null;

			while ((line = br.readLine()) != null) {
			// System.out.println(line);

				map.put(++count, line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				System.out.println("当前进程数：" + count);
				System.out.println("当前进程：" + map.size());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return map;
	}


}
