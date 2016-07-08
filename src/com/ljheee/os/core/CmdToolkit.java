package com.ljheee.os.core;


import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 

/** 
* ����̨�������� 
* 
* @author leizhimin 2009-6-25 14:12:14 
*/ 
public final class CmdToolkit { 


        /** 
         * ��ȡ��������������� 
         * 
         * @param cmd                ���� 
         * @param isPrettify ���صĽ���Ƿ�������������У���������ζ�Ż��У�Ĭ�ϲ���������,���˲���ΪnullʱҲ�������� 
         * @return ��������������� 
         * @throws IOException 
         */ 
        public static String readConsole(String cmd, Boolean isPrettify) throws IOException { 
                StringBuffer cmdout = new StringBuffer(); 
                Process process = Runtime.getRuntime().exec(cmd);     //ִ��һ��ϵͳ���� 
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
        
        public static void main(String[] args) {
			try {
				readConsole("QQ", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
