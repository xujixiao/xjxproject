package com.base.lib.bean;

/**
 *
 */
public class BaseBean {
    public Object data;
    public String msg;
    public String version;
    public String timestamp;
    public String status;

    @Override
    public String toString() {
        return "BaseBean{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", version='" + version + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
