package com.base.lib.base.adapter;

import android.view.ViewGroup;

/**
 * Copyright (C), 2011-2017
 * FileName: com.ancient.dimension.base.adapter.IViewHolder.java
 * Author: xujixiao
 * Date: 2017/12/4 15:14
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      15:14    1.0        Create
 */
public interface IViewHolder {

    public BaseHolder CreateViewHolder(ViewGroup parent, int viewType);

    public void onBindViewHolder(BaseHolder holder, int position);

}
