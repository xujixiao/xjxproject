package com.base.lib.http;

import android.content.DialogInterface;
import android.util.Log;

import com.base.lib.base.BaseActivity;
import com.base.lib.base.BaseFragment;
import com.base.lib.bean.BaseBean;
import com.base.lib.dialog.ConfirmDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author nfmomo
 *         <p>
 *         定义Retrofit处理链条中的具体功能
 */

public class ConverterCol {

    public static Gson gson = new GsonBuilder()
            .registerTypeAdapter(
                    new TypeToken<TreeMap<String, Object>>() {
                    }.getType(),
                    new JsonDeserializer<TreeMap<String, Object>>() {
                        @Override
                        public TreeMap<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                            TreeMap<String, Object> treeMap = new TreeMap<>();
                            JsonObject jsonObject = json.getAsJsonObject();
                            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                            for (Map.Entry<String, JsonElement> entry : entrySet) {
                                treeMap.put(entry.getKey(), entry.getValue());
                            }
                            return treeMap;
                        }
                    }).create();

    /**
     * 出错,给提示
     */
    public static Consumer errorToast(final BaseActivity activity) {

        return new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                try {
                    activity.hideRefreshing();
                    String message = ((Throwable) o).getMessage().toString();
                    if (!message.isEmpty()) {
                        Log.e("接口请求异常", message);
                    }
                } catch (Exception e) {
                    Log.e("接口请求异常", "线程阻塞");
                    activity.hideRefreshing();
                }
            }
        };

    }

    /**
     * 出错,给提示
     */
    public static Consumer errorToast() {
        return new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                try {
                    String message = ((Throwable) o).getMessage().toString();
                    if (!message.isEmpty()) {
                        Log.e("接口请求异常", message);
                    }
                } catch (Exception e) {
                    Log.e("接口请求异常", "线程阻塞");
                }
            }
        };
    }

    /**
     * 出错, 弹框提示（多数是后台查询数据为空）
     */
    public static Consumer errorDialog(final BaseActivity activity) {
        return new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                try {
                    activity.hideRefreshing();
                    String message = ((Throwable) o).getMessage().toString();
                    if (!message.isEmpty()) {
                        ConfirmDialog alert = new ConfirmDialog.Builder(activity)// builder
                                .setTitle("提示")
                                .setContent(message) //TODO 内容
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface mdialog, int which) {
                                        mdialog.dismiss();
                                    }
                                })
                                .create();
                        alert.show();
                        Log.e("接口请求异常", message);
                    }
                } catch (Exception e) {
                    Log.e("接口请求异常", "线程阻塞");
                    activity.hideRefreshing();
                }
            }
        };
    }

    /**
     * 出错，不给提示（多数是后台查询数据为空）
     */
    public static Consumer error(final BaseActivity activity) {
        return new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                try {
                    String message = ((Throwable) o).getMessage().toString();
                    if (!message.isEmpty()) {
                        activity.hideRefreshing();
                        Log.e("接口请求异常", message);
                    }
                } catch (Exception e) {
                    Log.e("接口请求异常", "线程阻塞");
                    activity.hideRefreshing();
                }
            }
        };
    }

    /**
     * 出错，不给提示（多数是后台查询数据为空）
     */
    public static Consumer error(final BaseFragment fragment) {
        return new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                try {
                    String message = ((Throwable) o).getMessage().toString();
                    if (!message.isEmpty()) {
                        fragment.hideRefreshing();
                        Log.e("接口请求异常", message);
                    }
                } catch (Exception e) {
                    Log.e("接口请求异常", "线程阻塞");
                    fragment.hideRefreshing();
                }
            }
        };
    }

    /**
     * 出错，不给提示（多数是后台查询数据为空）
     */
    public static Consumer error() {
        return new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                try {
                    String message = ((Throwable) o).getMessage().toString();
                    if (!message.isEmpty()) {
                        Log.e("接口请求异常", message);
                    }
                } catch (Exception e) {
                    Log.e("接口请求异常", "线程阻塞");
                }
            }
        };
    }


    /**
     * 第一层json处理 非数组
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseEntity(BaseBean baseBean, final Class<T> clazz) {
        try {
            String status = baseBean.status;
            String message = baseBean.msg;
            String data = gson.toJson(baseBean.data);

            //对请求进行判断
            if (status.equals("false")) {//请求失败
                throw new RuntimeException(message);
            } else {//请求成功
                if (data.equals("null") || data.isEmpty()) {
                    return clazz.newInstance();
                } else {
                    data = data.replaceAll(":null", ":\"\"");
                    data = data.replaceAll("\\[null,", "\\[");
                    data = data.replaceAll("\\[null]", "\\[\\]");
                    data = data.replaceAll(",null,", ",");
                    data = data.replaceAll(",null\\]", "\\]");
                    return gson.fromJson(data, clazz);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 第一层json处理 非数组
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Function<BaseBean, T> base(final Class<T> clazz) {
        return new Function<BaseBean, T>() {
            @Override
            public T apply(@NonNull BaseBean baseBean) throws Exception {
                String status = baseBean.status;
                String message = baseBean.msg;
                String data = gson.toJson(baseBean.data);

                //对请求进行判断
                if (!status.equals("false")) {//请求失败
                    Log.e("接口请求失败", message);
                    throw new RuntimeException(message);
                } else {//请求成功
                    Log.e("接口请求成功", data);
                    if (data.equals("null") || data.isEmpty()) {
                        return clazz.newInstance();
                    } else {
                        data = data.replaceAll(":null", ":\"\"");
                        data = data.replaceAll("\\[null,", "\\[");
                        data = data.replaceAll("\\[null]", "\\[\\]");
                        data = data.replaceAll(",null,", ",");
                        data = data.replaceAll(",null\\]", "\\]");
                        return gson.fromJson(data, clazz);
                    }
                }
            }
        };
    }

    /**
     * 第一层json处理 数组
     *
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> Function<BaseBean, List<T>> baseList(final Class<T[]> clazz) {
        return new Function<BaseBean, List<T>>() {
            @Override
            public List<T> apply(@NonNull BaseBean baseBean) throws Exception {
                String status = baseBean.status;
                String message = baseBean.msg;
                String data = gson.toJson(baseBean.data);

                //对请求进行判断
                if (!status.equals("0")) {//请求失败
                    Log.e("接口请求失败", "message");
                    throw new RuntimeException(message);
                } else {//请求成功
                    Log.e("接口请求成功", data);
                    if (data.equals("null") || data.isEmpty()) {
                        return new ArrayList<T>();
                    } else {
                        data = data.replaceAll(":null", ":\"\"");
                        data = data.replaceAll("\\[null,", "\\[");
                        data = data.replaceAll("\\[null]", "\\[\\]");
                        data = data.replaceAll(",null,", ",");
                        data = data.replaceAll(",null\\]", "\\]");
                        T[] arr = gson.fromJson(data, clazz);
                        return Arrays.asList(arr);
                    }
                }
            }
        };
    }

    /**
     * 第一层json处理,不对status进行判断
     * 后台erp特别需求，为求走通流程，临时的筛选器
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Function<BaseBean, T> noJudge(final Class<T> clazz) {
        return new Function<BaseBean, T>() {
            @Override
            public T apply(@NonNull BaseBean baseBean) throws Exception {
                String status = baseBean.status;
                String message = baseBean.msg;
                String data = gson.toJson(baseBean.data);

                Log.e("接口请求成功", data);
                if (data.equals("null") || data.isEmpty()) {
                    return clazz.newInstance();
                } else {
                    data = data.replaceAll(":null", ":\"\"");
                    data = data.replaceAll("\\[null,", "\\[");
                    data = data.replaceAll("\\[null]", "\\[\\]");
                    data = data.replaceAll(",null,", ",");
                    data = data.replaceAll(",null\\]", "\\]");
                    return gson.fromJson(data, clazz);
                }
            }
        };
    }

    /**
     * 出错,给提示
     */
    public static Action completeWithLoading(final BaseActivity activity) {
        return new Action() {
            @Override
            public void run() throws Exception {
                try {
                    activity.hideRefreshing();
                } catch (Exception e) {
                    activity.hideRefreshing();
                    Log.e("接口请求异常", "线程阻塞");
                }
            }
        };
    }

    /**
     * 出错,给提示
     */
    public static Action completeWithLoading(final BaseFragment fragment) {
        return new Action() {
            @Override
            public void run() throws Exception {
                try {
                    fragment.hideRefreshing();
                } catch (Exception e) {
                    fragment.hideRefreshing();
                    Log.e("接口请求异常", "线程阻塞");
                }
            }
        };
    }

}
