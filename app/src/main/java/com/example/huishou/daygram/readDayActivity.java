package com.example.huishou.daygram;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class readDayActivity extends Activity {

    private ListView mylist = null;
    private ListViewAdapter listViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = (ListView) findViewById(R.id.myListView);
        mylist.setAdapter(new ListViewAdapter(this , getdata()));
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(readDayActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<DayItemListViewBean> getdata() {
        List<DayItemListViewBean> data =   new ArrayList<DayItemListViewBean>();
        //添加数据，类型为ChatItemListViewBean
        DayItemListViewBean bean = new DayItemListViewBean();
        for (int i=1;i<20;i++) {
            bean = new DayItemListViewBean();
            bean.setType(1);
            bean.setIcon(BitmapFactory.decodeResource(getResources() , R.drawable.point));
            bean.setText("week","Mon");
            bean.setText("day",Integer.toString(i));
            bean.setText("content","hkaifuewqoirueqwoiurq");
            data.add(bean);
            bean = new DayItemListViewBean();
            bean.setType(0);
            bean.setIcon(BitmapFactory.decodeResource(getResources() , R.drawable.point));
            bean.setText("week","Mon");
            bean.setText("day",Integer.toString(i));
            bean.setText("content","hkaifuewqoirueqwoiurq");
            data.add(bean);
        }
        return data;
    }
}

