package com.tianzhuan.net_mvp01.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tianzhuan.net_mvp01.DownloaderContract;
import com.tianzhuan.net_mvp01.model.ImageBean;
import com.tianzhuan.net_mvp01.preseter.DownLoaderPreseter;
import com.tianzhuan.net_mvp01.utils.Constants;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoaderEngine implements DownloaderContract.M {
    private DownLoaderPreseter mPreseter;
    public DownLoaderEngine(DownLoaderPreseter downLoaderPreseter) {
        this.mPreseter=downLoaderPreseter;
    }

    @Override
    public void requestDownloader(ImageBean imageBean) throws Exception {
        //p层让我做这个需求
        new Thread(new DownLoader(imageBean)).start();
    }

    private class DownLoader implements Runnable {
        private final ImageBean mImageBean;
        public DownLoader(ImageBean imageBean) {
            this.mImageBean=imageBean;
        }

        @Override
        public void run() {
            try {
                URL url=new URL(mImageBean.getRequestPath());
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(Constants.SUCESS,bitmap);
                }else {
                    showUi(Constants.ERROR,null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(Constants.ERROR,null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            mImageBean.setBitmap(bitmap);
            if(mPreseter!=null){
                mPreseter.requestDownloaderResult(resultCode==Constants.SUCESS,mImageBean);
            }
        }
    }
}
