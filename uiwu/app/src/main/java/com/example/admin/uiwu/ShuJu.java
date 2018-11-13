package com.example.admin.uiwu;

public class ShuJu {
    private String tupm;
    private String name;
    private String time;
    private String address;

    public ShuJu(String tupm,String name,String time,String address){
        this.tupm=tupm;
        this.name=name;
        this.time=time;
        this.address=address;
    }

    public void SetTu(String tupm){
        this.tupm=tupm;
    }

    public void SetNa(String name){
        this.name=name;
    }

    public void SetTime(String time){
        this.time=time;
    }

    public void SetAddress(String address){
        this.address=address;
    }

    public String GetTu(){
        return this.tupm;
    }

    public String GetNa(){
        return this.name;
    }

    public String GetTime(){
        return this.time;
    }

    public String GetAddress(){
        return this.address;
    }

}
