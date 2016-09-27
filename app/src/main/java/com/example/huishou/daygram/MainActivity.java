package com.example.huishou.daygram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

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
    private String datemonth="";
    private String dateyear="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = (ListView) findViewById(R.id.myListView);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String date=sdf.format(new java.util.Date());

        datas = getdata(date);
        mylist.setAdapter(new ListViewAdapter(this , getdata(date)));
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,editDayActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("edit", datas.get(position));
                intent.putExtras(mBundle);
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

                Intent intent=new Intent(MainActivity.this,editDayActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("edit", datas.get(i));
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
        ArrayAdapter<String> adapter;
        String m[] = {"","January","February","March","April","May","June","July","August","September","October","November","December"};
        final Spinner month = (Spinner) findViewById(R.id.month);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapter);

        final Spinner year = (Spinner) findViewById(R.id.year);
        String y[] = {"","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,y);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapterYear);

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                datemonth = (String)month.getItemAtPosition(position);//从spinner中获取被选择的数据
                switch(datemonth){
                    case "January":
                        datemonth = "01";
                        break;
                    case "February":
                        datemonth = "02";
                        break;
                    case "March":
                        datemonth = "03";
                        break;
                    case "April":
                        datemonth = "04";
                        break;
                    case "May":
                        datemonth = "05";
                        break;
                    case "June":
                        datemonth = "06";
                        break;
                    case "July":
                        datemonth = "07";
                        break;
                    case "August":
                        datemonth = "08";
                        break;
                    case "September":
                        datemonth = "09";
                        break;
                    case "October":
                        datemonth = "10";
                        break;
                    case "November":
                        datemonth = "11";
                        break;
                    case "December":
                        datemonth = "12";
                        break;
                }
                if (dateyear!="" && datemonth!=""){
                    mylist.setAdapter(new ListViewAdapter(MainActivity.this , getdata(dateyear+"-"+datemonth)));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dateyear = (String)year.getItemAtPosition(position);//从spinner中获取被选择的数据
                if (dateyear!="" && datemonth!=""){
                    mylist.setAdapter(new ListViewAdapter(MainActivity.this , getdata(dateyear+"-"+datemonth)));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    private List<diary> getdata(String date) {
        List<diary> data =   new ArrayList<diary>();
        diary bean = new diary();

        String[] dateAfterSplit = new String[2];
        dateAfterSplit = date.split("-");
        String year = dateAfterSplit[0].substring(0,2);
        String month = dateAfterSplit[1];

        List<diary> s  = (List<diary>) getObject(date+".dat");
        if (null != s) {
            data = s;
            return data;
      //      System.out.println("姓名：" + s.getText("day") + ", 年龄：" + s.getText("content"));
        }else{
            for (int i=0;i<31;i++) {
                bean = new diary();
                bean.setType(0);
                bean.setText("date", date);
                bean.setText("day", Integer.toString(i+1));
                bean.setText("week", onDateSet(Integer.parseInt(year),Integer.parseInt(month),i+1));
                bean.setText("content","");
                data.add(bean);
            }
        }
        saveObject(date+".dat",data);
        return data;
    }
    public String onDateSet(int year, int monthOfYear,
                          int dayOfMonth) {
        int y = year - 1;
        int m = monthOfYear;
        int c = 20;
        int d = dayOfMonth + 12;
        int w = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
        String myWeek = null;
        switch (w) {
            case 0:
                myWeek = "Sun";
                break;
            case 1:
                myWeek = "Mon";
                break;
            case 2:
                myWeek = "Tue";
                break;
            case 3:
                myWeek = "Wed";
                break;
            case 4:
                myWeek = "Thu";
                break;
            case 5:
                myWeek = "Fri";
                break;
            case 6:
                myWeek = "Sat";
                break;
            default:
                break;
        }
        return myWeek;
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

