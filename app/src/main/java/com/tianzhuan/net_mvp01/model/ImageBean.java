package com.tianzhuan.net_mvp01.model;

import android.graphics.Bitmap;

public class ImageBean {
    private String requestPath;
    private Bitmap mBitmap;

    public String getRequestPath() {
        return requestPath == null ? "" : requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}
