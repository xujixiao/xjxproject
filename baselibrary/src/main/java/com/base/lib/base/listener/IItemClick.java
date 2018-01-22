package com.base.lib.base.listener;

import android.databinding.ViewDataBinding;

import com.base.lib.base.adapter.IHolderType;

/**
 * Copyright (C), 2011-2017
 * FileName: com.ancient.dimension.base.listener.IItemClick.java
 * Author: xujixiao
 * Date: 2017/12/26 15:26
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      15:26    1.0        Create
 */
public interface IItemClick<E extends IHolderType, B extends ViewDataBinding> {
    public void callBack(E entity, B binding, int position);
}
