package com.xks.okhttpdemo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

/**
 * Created by Xingfeng on 2016-11-02.
 */

public class OkioTest {

    public static void main(String[] args) {

        getResponseWithOkio();

        getResponseWithoutOkio();

    }

    private static void getResponseWithOkio() {

        long current = System.currentTimeMillis();

        HttpURLConnection conn = null;

        BufferedSink sink = null;
        BufferedSource source = null;

        try {
            URL url = new URL("http://www.baidu.com");
            conn = (HttpURLConnection) url.openConnection();

            source = Okio.buffer(Okio.source(conn.getInputStream()));

            FileOutputStream fous = new FileOutputStream("okio.txt");
            sink = Okio.buffer(Okio.sink(fous));

            sink.writeAll(source);

            conn.disconnect();
            source.close();
            sink.close();

            FileInputStream fins = new FileInputStream("okio.txt");
            source = Okio.buffer(Okio.source(fins));

            ByteString byteString = source.readByteString();
            source.close();
            System.out.println(byteString.utf8());

            System.out.println("Okio time: " + (System.currentTimeMillis() - current));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sink != null)
                try {
                    sink.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (source != null) {
                try {
                    source.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private static void getResponseWithoutOkio() {

        long current = System.currentTimeMillis();

        HttpURLConnection conn = null;

        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            URL url = new URL("http://www.baidu.com");
            conn = (HttpURLConnection) url.openConnection();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            writer = new FileWriter("io.txt");

            String line = "";

            while ((line = reader.readLine()) != null) {
                writer.write(line + "\r\n");
            }

            conn.disconnect();
            reader.close();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(new FileInputStream("io.txt")));

            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\r\n");
            }

            reader.close();

            System.out.println(result.toString());

            System.out.println("IO time: " + (System.currentTimeMillis() - current));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
