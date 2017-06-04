package com.zxp.filedownload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zxp.filedownload.utils.FileUtils;
import com.zxp.filedownload.utils.HttpDownloader;

public class MainActivity extends AppCompatActivity {
    private Button btn_down_text,btn_down_mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_down_text=(Button)findViewById(R.id.btn_down_text);
        btn_down_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       HttpDownloader httpDownloader=new HttpDownloader();
                       //.lrc(歌词文件)  告白气球
                       String lrc=httpDownloader.download("http://www.lrcgc.com/lrc-106-248053/%E5%91%A8%E6%9D%B0%E4%BC%A6-%E5%91%8A%E7%99%BD%E6%B0%94%E7%90%83.lrc");
                       Log.i("MainActivitytest",lrc);
                   }
               }).start();
            }
        });
        btn_down_mp3=(Button)findViewById(R.id.btn_down_mp3);
        btn_down_mp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpDownloader httpDownloader=new HttpDownloader();
                        FileUtils fileUtils=new FileUtils();
                        Log.i("Sdcard",fileUtils.getSDPath());
                        int result=httpDownloader.downFile("http://imgsrc.baidu.com/baike/pic/item/0824ab18972bd40735c53a707e899e510fb30938.jpg", "MyDownload","liqing.jpg");
                        Log.i("downloading************",result+"");
                    }
                }).start();
            }
        });
    }
}
