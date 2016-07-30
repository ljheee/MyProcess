package com.ljheee.os.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.ljheee.os.model.ProcessInfo;

/**
 * MyProcessUtil工具类
 * 
 * @author ljheee
 *
 */
public class MyProcessUtil {

	/**
	 * 返回所有进程[map]
	 * 
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
				System.out.println(line);

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

	public  Map processList2() {
		int Tcount = 0;
		int count = 0;
		BufferedReader br = null;
		ProcessInfo pInfo = null;
		HashMap<Integer, ProcessInfo> map = new HashMap<Integer, ProcessInfo>();

		String name = null;
		String pid = null;
		String sessionName = null;
		String sessionNum = null;
		String memory = null;

		String line = null;
		StringTokenizer st = null;

		try {
			Process proc = Runtime.getRuntime().exec("tasklist");
			br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));


			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());

			
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, " ");
				while (st.hasMoreElements()) {

					Tcount = st.countTokens();
					if (Tcount == 6) {
						name = st.nextToken();
						pid = st.nextToken();
						sessionName  =st.nextToken();
						sessionNum = st.nextToken();
						memory = st.nextToken();
						pInfo = new ProcessInfo(name, pid, sessionName, sessionNum, memory);
						if(pInfo!=null) map.put(++count, pInfo);
					} else if (Tcount == 8) {
						name = st.nextToken()+" "+st.nextToken()+" "+st.nextToken();
						pid = st.nextToken();
						sessionName  =st.nextToken();
						sessionNum = st.nextToken();
						memory = st.nextToken();
						pInfo = new ProcessInfo(name, pid, sessionName, sessionNum, memory);
						if(pInfo!=null) map.put(++count, pInfo);
					} else {
						
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return map;
	}

	public static void main(String[] args) {
		processList();
		System.out.println("++++++++++++++++++++++++++");
		Map map = new MyProcessUtil().processList2();
		for (int i = 0; i < map.size(); i++) {
			ProcessInfo  pInfo = (ProcessInfo) map.get(i);
			System.out.println(pInfo);
		}
		System.out.println(map.size());
	}

}
