package com.ancient.dimension.ui.entity;

import com.base.lib.base.adapter.IHolderType;

public class TopicEntity implements IHolderType {
    public String content;
    public String unixtime;
    public String updatetime;

    @Override
    public int holderType() {
        return 0;
    }
}
