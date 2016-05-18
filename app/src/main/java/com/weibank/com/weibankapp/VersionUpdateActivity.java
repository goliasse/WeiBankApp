package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.weibank.com.weibankapp.utils.NormalMethods;

public class VersionUpdateActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_update);
        /**
         * 从服务器端获取更新
         */
        UmengUpdateAgent.update(this);
        /**
         * 只在WI-FI下更新
         */
       UmengUpdateAgent.setUpdateOnlyWifi(true);
        /**
         *  弹出对话框
         */
        UmengUpdateAgent.setUpdateAutoPopup(true);
        /**
         * 设置对话框监听器
         */
        UmengUpdateAgent.setDialogListener(null);
        /**
         * 从服务器获取更新信息的回调函数
         */
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener(){
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse){
                switch(i){
                    //有更新
                    case 0:
                        UmengUpdateAgent.showUpdateDialog
                                         (VersionUpdateActivity.this,updateResponse);
                        break;
                    //无更新
                    case 1:

                        NormalMethods.toastShowContent(VersionUpdateActivity.this,
                                                       "暂时没有更新");
//                        Toast.makeText(VersionUpdateActivity.this,
//                                       "暂时没有更新", Toast.LENGTH_SHORT).show();
                        break;
                    //WI-FI下更新,且WI-FI不能用
                    case 2:

                        NormalMethods.toastShowContent(VersionUpdateActivity.this,
                                                       "WI-FI不可用,请连接WI-FI");
//                        Toast.makeText(VersionUpdateActivity.this,
//                                       "WI-FI不可用,请连接WI-FI", Toast.LENGTH_SHORT).show();
                        break;
                    //连接超时
                    case 3:

                        NormalMethods.toastShowContent(VersionUpdateActivity.this,
                                                       "连接超时,请稍后再试...");
//                        Toast.makeText(VersionUpdateActivity.this,
//                                        "连接超时,请稍后再试...", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
