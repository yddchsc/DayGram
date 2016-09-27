package com.example.huishou.daygram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<diary> datas;
    private ListView mylist = null;
    private ListViewAdapter listViewAdapter;
    private ImageButton add;
    private diary beans;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = (ListView) findViewById(R.id.myListView);
        datas = getdata();
        mylist.setAdapter(new ListViewAdapter(this , datas));
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,readDayActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("diary", datas.get(position));
                startActivity(intent);
            }
        });
        add =(ImageButton) findViewById(R.id.imageButton);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new java.util.Date());
                String[] dateAfterSplit= new String[3];
                dateAfterSplit=date.split("-");
                String day=dateAfterSplit[2];
                int i = Integer.parseInt(day)-1;

                Intent intent=new Intent(MainActivity.this,readDayActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("diary", datas.get(i));
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }
    private List<diary> getdata() {
        List<diary> data =   new ArrayList<diary>();
        //添加数据，类型为ChatItemListViewBean
        diary bean = new diary();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String date=sdf.format(new java.util.Date());

        System.out.println(date);

        List<diary> s  = (List<diary>) getObject("object.dat");
        if (null != s) {
            data = s;
            return data;
      //      System.out.println("姓名：" + s.getText("day") + ", 年龄：" + s.getText("content"));
        }else{
            for (int i=0;i<31;i++) {
                bean = new diary();
                bean.setType(0);
                if (i<9) {
                    bean.setText("date", date+ "-0" + Integer.toString(i+1));
                    bean.setText("day", Integer.toString(i+1));
                    bean.setText("week", "");
                    bean.setText("content","");
                }else {
                    bean.setText("date",date + "-" + Integer.toString(i+1));
                    bean.setText("day", Integer.toString(i+1));
                    bean.setText("week", "");
                    bean.setText("content","");
                }
                data.add(bean);
            }
        }
        saveObject("object.dat",data);
        return data;
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

