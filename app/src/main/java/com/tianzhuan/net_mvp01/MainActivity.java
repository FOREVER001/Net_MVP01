package com.tianzhuan.net_mvp01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tianzhuan.net_mvp01.model.ImageBean;
import com.tianzhuan.net_mvp01.preseter.DownLoaderPreseter;
import com.tianzhuan.net_mvp01.utils.Constants;

/**
 * mvc中Activity是c层，mvp中Activity是V层
 */
public class MainActivity extends AppCompatActivity implements DownloaderContract.PV{
    private ImageView mImageView;
    private DownLoaderPreseter mPreseter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=findViewById(R.id.iv);
        mPreseter=new DownLoaderPreseter(this);
    }

    //点击事件
    public void down(View view) {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(Constants.IMAG_PATH);
        requestDownloader(imageBean);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        if(mPreseter!=null){
            mPreseter.requestDownloader(imageBean);
        }
    }

    @Override
    public void requestDownloaderResult(boolean isSucess, ImageBean imageBean) {
        Toast.makeText(this, isSucess?"下载成功":"下载失败", Toast.LENGTH_SHORT).show();
        if(isSucess && imageBean.getBitmap()!=null){
            mImageView.setImageBitmap(imageBean.getBitmap());
        }
    }
}
