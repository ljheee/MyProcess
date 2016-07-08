package com.ljheee.os.test;

import java.io.BufferedInputStream; 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.nio.charset.Charset;   

public class ProcessUtil {          
	public static void main(String[] args) throws Exception {        // ����ϵͳ����  
		ProcessBuilder pb = new ProcessBuilder("tasklist");   
		Process p = pb.start();   
		BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));   
		BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));   System.out.println("Window ϵͳ�����б�");   
		String ostr;        
		while ((ostr = out.readLine()) != null)   
			System.out.println(ostr);   
		String estr = err.readLine();   
		if (estr != null) {   
			System.out.println("\nError Info");   
			System.out.println(estr);  
		}    
	}
}

