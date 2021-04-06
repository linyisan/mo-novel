package com.heng.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class 使用txt作为mysql数据源 {
    public static void main(String[] args) throws IOException
    {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\yisan\\Desktop\\垃圾堆\\data_mysql.txt"));
        for (int i = 1; i <= 10000000; i++)
        {
            String uuid = UUID.randomUUID().toString();
            bufferedWriter.write(i + "," + uuid + "\n");
        }
        bufferedWriter.close();
        System.out.println("写入完毕");
    }
}
