package com.ancient.dimension.ui.entity;

public class JsonEntity {
    public String is_wap;
    public String wap_url;
    public String is_update;
    public String update_url;
    public String code;
    public String msg;

    @Override
    public String toString() {
        return "JsonEntity{" +
                "is_wap='" + is_wap + '\'' +
                ", wap_url='" + wap_url + '\'' +
                ", is_update='" + is_update + '\'' +
                ", update_url='" + update_url + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
