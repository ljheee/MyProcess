package com.ljheee.os.test;

import java.io.IOException;
import java.io.InputStream;
 
public class Main3 {
 
    public static void main(String[] args) throws IOException {
        Process pro = Runtime.getRuntime().exec("tasklist /v");
        System.out.println(in2st(pro.getInputStream()));
    }
 
    private static String in2st(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        byte[] buffer = new byte[1024];
        while ((count = stream.read(buffer)) != -1) {
            builder.append(new String(buffer, 0, count));
        }
        return builder.toString();
    }
}