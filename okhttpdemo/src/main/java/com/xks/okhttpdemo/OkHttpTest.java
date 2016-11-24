package com.xks.okhttpdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Xingfeng on 2016-10-21.
 */

public class OkHttpTest {


    public static void main(String[] args) {

        cacheResponse();

    }

    private static void cacheResponse(){

        OkHttpClient client=new OkHttpClient.Builder().cache(new Cache(new File("cache"),24*1024*1024)).build();

        Request request=new Request.Builder().url("http://www.baidu.com").build();

        Call call=client.newCall(request);

        try {
            Response response=call.execute();
            System.out.println(response.body().string());
            System.out.println(response.body().string());
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }


       /* request=new Request.Builder().cacheControl(CacheControl.FORCE_CACHE).url("http://www.baidu.com").build();

        call=client.newCall(request);

        try{
            Response response=call.execute();
            System.out.println(response.body().string());
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    private static void connectBaidu() {

        try {
            Socket socket = new Socket("61.135.169.121", 80);

            StringBuilder get = new StringBuilder("GET /index.html HTTP/1.1").append("\r\n");

            get.append("Connection: Keep-Alive").append("\r\n")
                    .append("Host: www.baidu.com").append("\r\n")
                    .append("User-Agent: okhttp/3.4.1").append("\r\n")
                    .append("Accept: */*").append("\r\n")
                    .append("\r\n");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(get.toString().getBytes());
            outputStream.flush();

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\r\n");
            }

            System.out.println(result.toString());

            outputStream.close();
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void logConnection() {

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new LogConnectionInterceptor()).build();
        Request request = new Request.Builder().url("http://news.qq.com/a/20161026/008371.htm")
                .get().build();
        Call call = client.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Request request1 = new Request.Builder().url("http://news.qq.com/a/20161018/012687.htm")
                .get().build();
        Call call1 = client.newCall(request1);
        try {
            call1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 改写请求
     */
    private static void changeRequest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new GZipRequestInterceptor()).build();
        Request request = new Request.Builder().url("http://www.taobao.com")
                .get().build();
        Call call = okHttpClient.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 应用拦截器
     */
    private static void testApplicationInterceptor() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build();
        Request request = new Request.Builder().url("http://www.baidu.com")
                .get().build();
        Call call = okHttpClient.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 网络拦截器
     */
    private static void testNetworkInterceptor() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new LogInterceptor()).build();
        Request request = new Request.Builder().url("http://www.taobao.com")
                .get().build();
        Call call = okHttpClient.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步请求
     */
    private static void synchronous() {

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("http://www.baidu.com")
                .get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 异步请求
     */
    private static void asynchronous() {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("http://www.baidu.com")
                .get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println(response.body().string());

            }
        });
    }

}
