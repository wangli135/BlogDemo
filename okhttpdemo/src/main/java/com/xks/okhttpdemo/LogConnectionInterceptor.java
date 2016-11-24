package com.xks.okhttpdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.RealInterceptorChain;

/**
 * Created by Xingfeng on 2016-10-26.
 */

public class LogConnectionInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        RealInterceptorChain realChain = (RealInterceptorChain) chain;

        RealConnection connection = (RealConnection) realChain.connection();

        System.out.println("URL："+chain.request().url().toString()+" Connection地址：" + connection.toString());

        return chain.proceed(chain.request());
    }
}
