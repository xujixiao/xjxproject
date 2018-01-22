package com.base.lib.http;

import com.base.lib.bean.BaseBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by kec on 2017/8/1.
 */

public interface HttpUrl {


    boolean isLive = true;

    boolean isDev = true;

    boolean isTest = true;

    /**
     * ------------------基础数据接口请求地址--------------------
     */
    String API_BASE_URL1 = "http://116.62.148.251/";// 生产环境(NEW)
    String API_BASE_URL2 = "http://116.62.148.251/";// 开发环境(NEW)
    String API_BASE_URL3 = "http://10.58.134.100:8080";// 测试环境(NEW)
    String API_BASE_URL = isLive ? API_BASE_URL1 : (isDev ? API_BASE_URL2 : API_BASE_URL3);


    String picUrl = API_BASE_URL + "";
    String PICURL_STOCK = API_BASE_URL + "/stock";

    @FormUrlEncoded
    @POST("gcy/app/AppServlet.htm")
    Observable<BaseBean> requestUrl(@FieldMap HashMap<String, String> map);


    @FormUrlEncoded
    @POST("gcy/app/AppServlet.htm")
    Observable<BaseBean> requestUrlWtihArray(@FieldMap HashMap<String, String> map, @Field("attachments[]") List<String> urlList);

    @Multipart
    @POST("gcy/app/AppUploadFileServlet.htm")
    Observable<BaseBean> upload(@Query("version") String version,
                                @Query("cmd") String cmd,
                                @Query("userid") String userid,
                                @Query("timestamp") String timestamp,
                                @Part MultipartBody.Part photo);

}
