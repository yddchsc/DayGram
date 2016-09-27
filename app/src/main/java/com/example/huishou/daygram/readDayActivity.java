package com.example.huishou.daygram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;


public class readDayActivity extends Activity {

    private diary bean;
    private String content = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        bean = new diary();
        bean= (diary) getIntent().getSerializableExtra("diary");

        final EditText editText = (EditText) findViewById(R.id.editText2);
        editText.setText(bean.getText("content"));
        content = editText.getText().toString();

        String day = "";
        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText(bean.getText("date"));

        ImageButton save =(ImageButton) findViewById(R.id.imageButton1);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (content != "") {
                    bean.setText("content", content);
                    bean.setType(1);
                }else {
                    bean.setType(0);
                }
                int i = Integer.parseInt(bean.getText("day"))-1;
                List<diary> s  = (List<diary>) getObject("object.dat");
                s.set(i,bean);
                saveObject("object.dat",s);
                Intent intent=new Intent(readDayActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void saveObject(String name,List<diary> d){
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

