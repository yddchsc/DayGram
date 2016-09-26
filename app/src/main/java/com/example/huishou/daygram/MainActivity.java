package com.example.huishou.daygram;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mylist = null;
    private ListViewAdapter listViewAdapter;
    private ImageButton add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = (ListView) findViewById(R.id.myListView);
        mylist.setAdapter(new ListViewAdapter(this , getdata()));
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,readDayActivity.class);
                startActivity(intent);
            }
        });
        add =(ImageButton) findViewById(R.id.imageButton);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,readDayActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<diary> getdata() {
        List<diary> data =   new ArrayList<diary>();
        //添加数据，类型为ChatItemListViewBean
        diary bean = new diary();
        for (int i=1;i<20;i++) {
            bean = new diary();
            bean.setType(1);
            bean.setIcon(BitmapFactory.decodeResource(getResources() , R.drawable.point));
            bean.setText("week","Mon");
            bean.setText("day",Integer.toString(i));
            bean.setText("content","Mon");
            data.add(bean);
            bean = new diary();
            bean.setType(0);
            bean.setIcon(BitmapFactory.decodeResource(getResources() , R.drawable.point));
            bean.setText("day",Integer.toString(i));
            data.add(bean);
        }
        return data;
    }
}

