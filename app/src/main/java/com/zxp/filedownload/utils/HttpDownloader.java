package com.zxp.filedownload.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.path;

/**
 * Created by xiaoxin on 2017/6/3.
 */

public class HttpDownloader {
    private URL url=null;

    /**
     * 根据URL下载文件，前提是这个文件夹中的内容是文本，函数的返回值就是文件当中的内容！
     * @param urlStr
     * @return
     */
    public String download(String urlStr){
        StringBuffer sb=new StringBuffer();
        String line=null;
        BufferedReader buffer=null;
        try{
            url=new URL(urlStr);
            //HttpsURLConnection!!!!
            HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
            buffer=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while((line=buffer.readLine())!=null){
                sb.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                buffer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  sb.toString();
    }

    /**
     * 该函数返回整形-1：代表下载文件出错；0:代有下载文件成历；1：代表文件已经存在
     * @param urlStr
     * @param dir       所想要放文件的目录位置
     * @param fileName  名字
     * @return
     */
    public int downFile(String urlStr,String dir,String fileName){
       InputStream inputStream=null;
        try{
            FileUtils fileUtils=new FileUtils();
            if(fileUtils.isFileExist(dir+"/"+fileName)){
                return 1;
            }else{
                inputStream=getInputStreamFromUrl(urlStr);
                Log.i("zxplog","input path="+dir+",fileName="+fileName);
                File resultFile=fileUtils.write2SDFromInput(dir,fileName,inputStream);
                if(resultFile==null){
                    Log.e("zxpLog","创建失败*********");
                    return -1;

                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            try{
                inputStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    public InputStream getInputStreamFromUrl(String urlStr) throws MalformedURLException,IOException{
        url=new URL(urlStr);
        //这里进行的是IO操作，要么抛出IO异常，要么使用Try Catch语句
        HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
        InputStream inputStream=urlConn.getInputStream();
        return inputStream;

    }







}
