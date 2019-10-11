package com.tianzhuan.net_mvp01.preseter;

import android.os.SystemClock;

import com.tianzhuan.net_mvp01.DownloaderContract;
import com.tianzhuan.net_mvp01.MainActivity;
import com.tianzhuan.net_mvp01.engine.DownLoaderEngine;
import com.tianzhuan.net_mvp01.model.ImageBean;
//p层几乎不做事情？谷歌的sample中，p层是包揽了所以的活
public class DownLoaderPreseter implements DownloaderContract.PV {
    private MainActivity view;
    private DownLoaderEngine model;//下载的模型

    public DownLoaderPreseter(MainActivity view) {
        this.view = view;
        //因为需要在model层告知P层结果如何，所以传递this(P)
        model=new DownLoaderEngine(this);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        //接受到View层的指令，去完成某个需求（可以自己完成，也可以让别人去完成）
//        try {
//            model.requestDownloader(imageBean);
//        }catch (Exception e){
//            e.printStackTrace();
//            //省略了异常处理的代码
//        }

        //内存泄漏
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(50000);
            }
        }).start();
    }

    @Override
    public void requestDownloaderResult(final boolean isSucess, final ImageBean imageBean) {
        //将完成的结果告知View层（刷新UI）
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.requestDownloaderResult(isSucess,imageBean);
            }
        });
    }
}
