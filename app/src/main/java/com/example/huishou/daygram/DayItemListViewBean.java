package com.example.huishou.daygram;

import android.graphics.Bitmap;

public class DayItemListViewBean {
    private int type;          //类型
    private String text1 ;       //文本信息
    private Bitmap icon ;       //图片
    private String text2 ;
    private String text3 ;
    public DayItemListViewBean(){
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
        }
        return text1;
    }

    public void setText(String i,String text){
        switch (i){
            case "week":
                this.text1=text;
            case "day":
                this.text2=text;
            case "content":
                this.text3=text;
        }
    }

    public Bitmap getIcon(){
        return icon ;
    }

    public void setIcon(Bitmap icon){
        this.icon   =   icon ;
    }
}


