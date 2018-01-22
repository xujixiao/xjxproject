package com.base.lib.base.viewentity;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.viewentity.ExceptionViewEntity.java
 * Author: xujixiao
 * Date: 2017/12/25 10:26
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      10:26    1.0        Create
 */
public class ExceptionViewEntity {
    public ObservableBoolean visible = new ObservableBoolean();
    public ObservableInt imageId = new ObservableInt();
    public ObservableField<String> text = new ObservableField<>();
}
