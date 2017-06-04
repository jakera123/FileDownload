package com.zxp.filedownload.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by xiaoxin on 2017/6/3.
 */

public class FileUtils {
    private String SDPATH;
    public String getSDPath (){
        return SDPATH;
    }
    public FileUtils(){
        SDPATH= Environment.getExternalStorageDirectory()+"/";
    }
//
    /**
     * 在SD卡上创建文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public File creatSDFile(String fileName) throws IOException{
        File file=new File(SDPATH+fileName);
        Log.i("zxplog","file="+SDPATH+fileName);
        file.createNewFile();
        Log.i("zxplog","fileState:"+file.exists());
        return file;
    }

    /**
     * 在SD卡上创建目录
     * @param dirName
     * @return
     */
    public File createSDDir(String dirName){
        File dir=new File(SDPATH+dirName);
        Log.i("zxplog","dir="+SDPATH+dirName);
        dir.mkdir();
        Log.i("zxplog","dirsxist:"+dir.exists());
        return dir;
    }

    public boolean isFileExist(String fileName){
        File file=new File(SDPATH+fileName);
        Log.i("zxplog","isfile="+SDPATH+fileName);
        return file.exists();
    }

    /**
     * 将一个InputStream里的数据写入SD卡
     * @param path
     * @param fileName
     * @param input
     * @return
     */
    public File write2SDFromInput(String path, String fileName, InputStream input){
        File file=null;
        OutputStream output=null;
        try{
            createSDDir(path);
            Log.i("zxplog","创建文件前");
            File f=creatSDFile(path+"/"+fileName);
            if(f==null){
                Log.i("zxplog","创建文件失败了……");
            }
            output=new FileOutputStream(f);
            byte buffer[]=new byte[4*1024];
            while((input.read(buffer))!=-1){
                output.write(buffer);
            }
            System.out.println(path);
            file=new File(path);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                output.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }

}
