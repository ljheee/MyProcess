package com.ljheee.os.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
		
		processList();
			
		
	}

	public static void processList() {
		int count = 0;
		BufferedReader br = null;
		HashMap<Integer,String>  map = new HashMap<Integer,String>();
		
		try {
			Process proc = Runtime.getRuntime().exec("tasklist");
			br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gb2312"));
			System.out.println("�������еĽ�����Ϣ:");
			String line = null;
		
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			int pc = ++count;
			
			map.put(pc, line);
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
			
		for (int i = 0; i < map.size(); i++) {
			String line = map.get(i);
			System.out.println(line);
		}
	}
	
	
}
