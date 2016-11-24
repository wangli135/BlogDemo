package com.xks.okhttpdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Xingfeng on 2016-10-21.
 */

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        System.out.println(request.toString());
        
        Response response = chain.proceed(request);

        System.out.println(response);

        return response;
    }
}
