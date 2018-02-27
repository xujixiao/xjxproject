package com.base.lib.base.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by kec on 2017/8/10.
 */

public abstract class CustomBaseAtapter<T> extends BaseAdapter {

    private List<T> _datas;

    public CustomBaseAtapter(List<T> datas) {
        this._datas = datas;
    }

    @Override
    public int getCount() {
        return _datas != null ? _datas.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return position < getCount() ? _datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
