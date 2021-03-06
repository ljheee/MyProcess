package com.ljheee.os.test;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 

/** 
* 控制台处理工具箱 
* 
* @author leizhimin 2009-6-25 14:12:14 
*/ 
public final class CmdToolkit { 


        /** 
         * 读取控制命令的输出结果 
         * 
         * @param cmd                命令 
         * @param isPrettify 返回的结果是否进行美化（换行），美化意味着换行，默认不进行美化,当此参数为null时也不美化， 
         * @return 控制命令的输出结果 
         * @throws IOException 
         */ 
        public static String readConsole(String cmd, Boolean isPrettify) throws IOException { 
                StringBuffer cmdout = new StringBuffer(); 
                Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令 
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
//				readConsole("cmd /c taskmgr.exe ", true);
//				readConsole("cmd /c tskill taskmgr.exe ", true);//结束指定进程，在cmd里可以，此处不行
				Runtime.getRuntime().exec("taskkill /f /im taskmgr.exe");//结束指定进程
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}