package com.weibank.com.weibankapp.utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/1/29.
 */
public class DownloadApkUtil extends AsyncTask<String ,Integer,File>{

    public DownloadApkUtil(Context context,ProgressDialog progressDialog,File desFile){

        super();
        this.progressDialog = progressDialog;
        this.progressDialog.setTitle("应用程序下载");
        this.progressDialog.setMessage("正在下载中...");
        this.desFile = desFile;
        this.context = context;
        try{
            if(!this.desFile.exists()){
                this.desFile.mkdirs();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onPreExecute(){

        this.progressDialog.setProgress(0);
        this.progressDialog.show();
    }
    @Override
    protected File doInBackground(String... params){
        URL url;
        HttpURLConnection connection = null;
        try{
            url = new URL(params[0]);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10 * 1000);
            connection.setDoInput(true);
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            long filelength = connection.getContentLength();
            FileOutputStream fos = new FileOutputStream(this.desFile);
            byte[] datas = new byte[1024];
            int length = 0;
            int total = 0;
            while((length = is.read(datas)) != -1){

                fos.write(datas,0,length);
                total += length;
                int progress = (int)((total / (float)filelength) * 100);
                /**
                 * 更新进度条
                 */
                publishProgress(progress);
            }
            if(fos != null){
                fos.close();
            }
            if(is != null){
                is.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(connection != null){

                connection.disconnect();
                connection = null;
            }
        }
        return this.desFile;
    }
    @Override
    protected void onProgressUpdate(Integer... values){

        progressDialog.setProgress(values[0]);
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(File file){

        progressDialog.dismiss();
        /**
         * 安装apk
         */
        NormalMethods.installApk(this.context,file);
        super.onPostExecute(file);
    }
    /**
     * ProgressDialog对象
     */
    private ProgressDialog progressDialog;
    /**
     * File对象
     */
    private File desFile;
    /**
     * Context对象
     */
    private Context context;
}
