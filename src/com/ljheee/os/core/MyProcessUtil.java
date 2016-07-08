package com.ljheee.os.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MyProcessUtil {
	
	
	public static Map processList() {
		int count = 0;
		BufferedReader br = null;
		HashMap<Integer,String>  map = new HashMap<Integer,String>();
		
		try {
			Process proc = Runtime.getRuntime().exec("tasklist");
			br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));
//			System.out.println("�������еĽ�����Ϣ:");
			String line = null;
		
		while ((line = br.readLine()) != null) {
//			System.out.println(line);
			
			map.put(++count, line);
		}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
					try {
						if (br != null) br.close();
						System.out.println("��ǰ��������"+count);
						System.out.println("��ǰ���̣�"+map.size());
					} catch (IOException e) {
						e.printStackTrace();
					}
				
		}
			
		
		return map;
	}
	public static void main(String[] args) {
		Map map = processList();
		for (int i = 0; i < map.size(); i++) {
			System.out.println(map.get(i));
		}
	}

}
