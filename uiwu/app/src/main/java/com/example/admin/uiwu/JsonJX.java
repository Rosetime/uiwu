package com.example.admin.uiwu;

import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonJX {


    public static void jsonJXDate(String date) {
        if(date!=null) {
            try {
                JSONObject jsonObject = new JSONObject(date);
                String resultCode = jsonObject.getString("message");
                if (resultCode.equals("sucessed 01 ")) {
                    JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<resultJsonArray.length();i++){
                        jsonObject = resultJsonArray.getJSONObject( i );
                        String name = jsonObject.getString("loss_name");
                        System.out.println(name+"\nhello world  ");
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public static void jsonJXDate(String date, ArrayList<Object> arrayList) {
        if(date!=null) {
            arrayList.clear();
            try {
                JSONObject jsonObject = new JSONObject(date);
                String resultCode = jsonObject.getString("message");
                if (resultCode.equals("sucessed 01 ")) {
                    JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<resultJsonArray.length();i++){
                        jsonObject = resultJsonArray.getJSONObject( i );
                        arrayList.add(new ShuJu(jsonObject.getString("loss_image"),jsonObject.getString("loss_name"),jsonObject.getString("loss_time"),jsonObject.getString("loss_address")));

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
