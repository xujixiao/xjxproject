//package com.base.lib.http;
//
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.base.lib.base.constants.BaseLogConstant;
//import com.base.lib.bean.BaseBean;
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.nio.charset.Charset;
//
//import okhttp3.MediaType;
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//
//import static okhttp3.internal.Util.UTF_8;
//
///**
// * 重写为了默认处理网络回调的信息
// * <p>
// * Created by nfmomo on 17/4/1.
// */
//
//public class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
//    private final Gson gson;
//    private final TypeAdapter<T> adapter;
//
//    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//        this.gson = gson;
//        this.adapter = adapter;
//    }
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
//
//        String response = value.string();
//        Log.e("请求成功", response);
//
//        BaseBean httpStatus = null;
//        try {
//            httpStatus = gson.fromJson(response, BaseBean.class);
//        } catch (Exception e) {
//            Log.d(BaseLogConstant.tag, "接口请求异常MyResponseBodyConverter");
//        }
//
//        String resultCode = httpStatus.status;
//
//        //接口返回错误
//        if ("false".equals(resultCode)) {
//            value.close();
//            if (!TextUtils.isEmpty(httpStatus.msg)) {
//                throw new ApiException(httpStatus.status, httpStatus.msg);
//            } else {
//                Log.d(BaseLogConstant.tag,"未知错误----");
//            }
//        }
//
//        MediaType contentType = value.contentType();
//        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
//        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
//        Reader reader = new InputStreamReader(inputStream, charset);
//        JsonReader jsonReader = gson.newJsonReader(reader);
//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }
//    }
//}
