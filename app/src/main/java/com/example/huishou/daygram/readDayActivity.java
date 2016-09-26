package com.example.huishou.daygram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class readDayActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        String content="";
        EditText editText =(EditText)findViewById(R.id.editText2);
        content=editText.getText().toString();

        String day = "";
        TextView textview =(TextView)findViewById(R.id.textView);
        day=textview.getText().toString();

        diary sod = new diary();
        sod.setText("day",day);
        sod.setText("content",content);

//保存对象到本地
        saveObject("object.dat",sod);

//得到保存于本地路径的对象
        diary s = (diary) getObject("object.dat");
        if (null != s) {
            System.out.println("姓名：" + s.getText("day") + ", 年龄：" + s.getText("content"));
        }else{
            System.out.println("读取对象失败");
        }
    }
    private void saveObject(String name,diary d){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(name, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(d);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常
                    e.printStackTrace();
                }
            }
        }
    }
    private Object getObject(String name){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = this.openFileInput(name);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            //这里是读取文件产生异常
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    //fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    //ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
        //读取产生异常，返回null
        return null;
    }
}

