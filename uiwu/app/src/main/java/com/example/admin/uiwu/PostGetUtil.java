package com.example.admin.uiwu;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostGetUtil {

    /**
     * 使用post方式与服务器通讯
     * @param content
     * @return
     */
    public static String SendPostRequest(String content){
        String acceptData = "";
        HttpURLConnection conn=null;
        try {
            String Strurl="http://47.106.146.182/she";
            URL url = new URL(Strurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true); //有误重定向
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            OutputStream outputStream = conn.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            //例如这样写入参数  // phoneNumber为外部传入的手机号 后面为密匙
            printStream.print(content); //向服务器提交数据
            if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
                Log.i("PostGetUtil","post请求成功");
                InputStream in=conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                String line;
                while((line=bufferedReader.readLine())!=null){ //不为空进行操作
                    acceptData+=line;
                }
                System.out.println("接受到的数据："+acceptData);
                JsonJX.jsonJXDate(acceptData);
                in.close();
            }else {
                Log.i("PostGetUtil","post请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        return acceptData;
    }





    /**
     * 使用get方式与服务器通信
     * @param content
     * @return
     */
    public static String SendGetRequest(String content){

        String acceptData = "";
        HttpURLConnection conn=null;
        try {

            String Strurl="http://47.106.146.182/he?"+content;
            URL url = new URL(Strurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
                Log.i("PostGetUtil","get请求成功");
                InputStream in=conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                String line;
                while((line=bufferedReader.readLine())!=null){ //不为空进行操作
                    acceptData+=line;
                }
                System.out.println("接受到的数据："+acceptData);
                in.close();
            }
            else {
                Log.i("PostGetUtil","get请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            conn.disconnect();
        }
        return acceptData;
    }


}
