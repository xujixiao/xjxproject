package com.base.lib.http;

import android.content.Context;
import android.util.Log;

import com.base.lib.base.BaseActivity;
import com.base.lib.bean.BaseBean;
import com.base.lib.tools.DateUtils;
import com.base.lib.utils.AppUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by kec on 2017/8/3.
 */

public class Api {


    private static HttpUrl httpUrl;
    private static HttpUrl httpUrlpws;

    private static String sign = "";

    /**
     * 获取请求接口需要的基本参数
     *
     * @return
     */
    private static HashMap<String, String> getMap(String sign, Context context) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Body.RequestId", AppUtil.getIMEI(context));
        map.put("Body.RequestTime", DateUtils.getFormatTimeFromTimestamp(System.currentTimeMillis(), DateUtils.FORMAT_DATE_TIME_ALL));
        map.put("Body.Signature", sign);
        map.put("Body.Language", "zh-CN");
        return map;
    }


    private static HttpUrl get() {
        if (httpUrl == null) {

            OkHttpClient.Builder okhttp = new OkHttpClient().newBuilder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new LogInterceptor())
                    .connectTimeout(60, TimeUnit.SECONDS);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUrl.API_BASE_URL)
                    .client(okhttp.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MyConverterFactory.create())
                    .build();

            httpUrl = retrofit.create(HttpUrl.class);
        }
        return httpUrl;
    }


    /**
     * 添加请求头
     *
     * @return
     */
    public static OkHttpClient genericClient(final Context context) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                //用于认识不同的会话，建议使用Guid
                                .addHeader("RequestId", AppUtil.getIMEI(context))//AppUtils.getIMEI(MyApplication.context)
                                //客户端的请求时间，格式：yyyy-MM-dd HH:mm:ss
                                .addHeader("RequestTime", DateUtils.getFormatTimeFromTimestamp(System.currentTimeMillis(), DateUtils.FORMAT_DATE_TIME_ALL))
                                //根据请求体，生成数字签名，用来确保请求包没有被篡改。使用MD5
                                .addHeader("Signature", sign)
                                //“zh-CN”,”en-US”。默认中文
                                .addHeader("Language", "zh-CN")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return httpClient;
    }

    /**
     * 添加请求头 加 token
     *
     * @return
     */
    public static OkHttpClient genericClient(final String token) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("token", token)
                                .build();

                        Log.v("lincoln", "request:" + request.toString());
                        Log.v("lincoln", "request headers:" + request.headers().toString());

                        return chain.proceed(request);
                    }
                })
                .build();

        return httpClient;
    }


    /**
     * P3登录
     * <p>
     * 登录
     */
    public static Disposable request(BaseActivity activity, HashMap<String, String> map, Consumer<BaseBean> consumer, Consumer<Throwable> error) {
//
        activity.showProgress();
        return get().requestUrl(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle())
                .subscribe(consumer, error, ConverterCol.completeWithLoading(activity));
    }

    public static Disposable requestWithArrat(BaseActivity activity, HashMap<String, String> map, List<String> attachment, Consumer<BaseBean> consumer, Consumer<Throwable> error) {
//
        activity.showProgress();
        return get().requestUrlWtihArray(map, attachment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle())
                .subscribe(consumer, error, ConverterCol.completeWithLoading(activity));
    }

    public static Disposable upload(BaseActivity activity, HashMap<String, String> map, File file,
                                    Consumer<BaseBean> consumer, Consumer<Throwable> error) {
//
        activity.showProgress();

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);
        return get().upload(map.get("version"),
                map.get("cmd"),
                map.get("userid"),
                map.get("timestamp"), body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle())
                .subscribe(consumer, error, ConverterCol.completeWithLoading(activity));
    }

}
