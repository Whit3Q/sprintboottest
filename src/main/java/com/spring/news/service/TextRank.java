package com.spring.news.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextRank {

    public static String start(String content) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        String command = "C:\\Users\\gw122\\AppData\\Local\\Programs\\Python\\Python39\\python.exe";
        String arg1 = "C:\\Users\\gw122\\AppData\\Local\\Programs\\Python\\Python39\\Scripts\\textRankNews.py";
        String arg2 = content;

        ProcessBuilder builder = new ProcessBuilder(command, arg1, arg2);
        Process process = builder.start();

        int exitVal = process.waitFor();  // 자식 프로세스가 종료될 때까지 기다림
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String line;
        String result;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        if (exitVal != 0) {
            // 비정상 종료
            System.out.println("서브 프로세스가 비정상 종료되었다." + arg2.substring(10));
        }
        return sb.toString();
    }
}
