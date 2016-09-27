package com.example.huishou.daygram;

import android.graphics.Bitmap;

import java.io.Serializable;

public class diary implements Serializable {

    private static final long serialVersionUID = 1L;

    private int type;          //类型
    private String text1 ;       //文本信息
    private String text2 ;
    private String text3 ;
    private String text4 ;

    public diary(){
    }

    //三个get方法和三个set方法
    public int getType(){
        return  type ;
    }

    public void setType(int type){
        this.type   =   type ;
    }

    public String getText(String i){
        switch (i){
            case "week":
                return text1;
            case "day":
                return text2;
            case "content":
                return text3;
            case "date":
                return text4;
        }
        return text1;
    }

    public void setText(String i,String text){
        switch (i){
            case "week":
                this.text1=text;
                break;
            case "day":
                this.text2=text;
                break;
            case "content":
                this.text3=text;
                break;
            case "date":
                this.text4=text;
                break;
        }
    }
}


