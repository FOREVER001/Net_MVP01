package com.tianzhuan.net_mvp01;

import com.tianzhuan.net_mvp01.model.ImageBean;

//view层交互，Model层交互共同的需求(契约，合同)
public interface DownloaderContract {
    interface M{
        //P层告诉M层，需要做什么
        void requestDownloader(ImageBean imageBean) throws Exception;
    }
    interface PV{
        //V层告诉P层，需要做什么事情
        void requestDownloader(ImageBean imageBean);
        //P层得到M层的结果返回，再通知V层
        void requestDownloaderResult(boolean isSucess,ImageBean imageBean);
    }
}
