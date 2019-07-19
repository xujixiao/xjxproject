package com.ancient.dimension.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.ancient.dimension.TagConstants;
import com.ancient.dimension.ui.entity.BaseEntity;
import com.ancient.dimension.ui.entity.ListEntity;
import com.ancient.dimension.ui.entity.TopicEntity;
import com.base.lib.base.BasePresenter;
import com.base.lib.base.view.IView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.List;

public class TopicPresenter extends BasePresenter<TopicPresenter.View> {
    public interface View extends IView {
        public void onResult(List<TopicEntity> list);
    }

    public void getTopic(Context context, int page, int size) {
//        53eafc8ba3cc21c2bddb4bf18f0554f2
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        new Novate.Builder(context)
                .baseUrl("http://v.juhe.cn/")
                .build()
                .rxGet("joke/content/list.php?key=" +
                                TagConstants.APP_TOPIC_KEY
                                + "&page=" + page + "&pagesize=" + size + "&sort=desc&time=" + time,
                        new HashMap<>(), new RxStringCallback() {
                            @Override
                            public void onNext(Object tag, String response) {
                                Log.d(TagConstants.TAG, response);

                                BaseEntity<ListEntity> entityBaseEntity = new Gson().fromJson(response,
                                        new TypeToken<BaseEntity<ListEntity>>() {
                                        }.getType());
                                mvpView.onResult(entityBaseEntity.result.data);
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
