package com.ancient.dimension.ui.presenter;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.ancient.dimension.ui.entity.JsonEntity;
import com.base.lib.base.BasePresenter;
import com.base.lib.base.view.IView;
import com.google.gson.Gson;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by 11073 on 2018/1/30.
 */

public class TestPresenter extends BasePresenter {

    interface view extends IView {
        void onJsonResult(JsonEntity jsonEntity);
    }
    public void gianInfo(Context context) {
        new Novate.Builder(context)
                .baseUrl("http://www.ds06ji.com:15780/")
                .build()
                .rxGet("back/api.php?app_id=122000055", new HashMap<String, Object>(), new RxStringCallback() {
                    @Override
                    public void onNext(Object tag, String response) {
                        Log.d("xujixiao", response);
                        byte[] str = Base64.decode(response, Base64.DEFAULT);
                        Log.d("xujixiao", new String(str));
                        JsonEntity jsonEntity = new Gson().fromJson(new String(str), JsonEntity.class);
                        Log.d("xujixiao", jsonEntity.toString());

                    }

                    @Override
                    public void onError(Object tag, Throwable e) {

                    }

                    @Override
                    public void onCancel(Object tag, Throwable e) {

                    }
                });

    }
}
