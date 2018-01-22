package com.base.lib.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 *
 *  by 2017/12/20.
 *  by RobinYu
 */

public class LogInterceptor implements Interceptor {
    public static String TAG = "xujixiao";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.i(TAG, "\n");
        Log.i(TAG, "----------Start----------------");
        Log.i(TAG, "| " + request.toString());
        String method = request.method();

        StringBuilder sb = new StringBuilder();
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            for (int i = 0; i < body.size(); i++) {
                sb.append(body.name(i) + "=" + body.value(i) + ",");
            }
            sb.delete(sb.length() - 1, sb.length());
            Log.i(TAG, "| RequestParams:{" + sb.toString() + "}");
        }
        Log.i(TAG, "| Response:" + content);
        Log.i(TAG, "----------End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
