package com.base.lib.base.viewentity;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.viewentity.TitleViewEntity.java
 *
 * Date: 2017/12/25 10:06
 * Description:
 * History:
 *
 * xujixiao      10:06    1.0        Create
 */
public class TitleViewEntity {

    public ObservableInt backIconId = new ObservableInt();
    public ObservableBoolean backIconVisible = new ObservableBoolean();
    public ObservableBoolean titleLayoutVisible = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<View.OnClickListener> titleLeftClick = new ObservableField<>();
    public ObservableField<View.OnClickListener> titleRightClick = new ObservableField<>();
    public ObservableBoolean rightLayoutVisible = new ObservableBoolean();
    public ObservableBoolean rightImageVisible = new ObservableBoolean();
    public ObservableField<String> rightText = new ObservableField<>();
    public ObservableInt rightImageId = new ObservableInt();
}
