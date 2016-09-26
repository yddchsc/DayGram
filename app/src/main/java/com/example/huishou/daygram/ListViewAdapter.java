package com.example.huishou.daygram;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ListViewAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<diary> listItems;    //日记信息集合
    private LayoutInflater listContainer;           //视图容器

    public final class ListItemView{                //自定义控件集合
        public TextView week;
        public TextView day;
        public TextView content;
        public ImageView point;
    }

    public ListViewAdapter(Context context, List<diary> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getItemViewType(int position){
        diary bean = listItems.get(position);
        return bean.getType();
    }
    @Override
    public int getViewTypeCount(){
        return 2 ;
    }

    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        //自定义视图
        ListItemView  listItemView = null;
        /*if (convertView != null) {
            listItemView = (ListItemView) convertView.getTag();
            return convertView;
        }else{*/
            if (getItemViewType(position)  ==  1) {
                listItemView = new ListItemView();
                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.listview_one, null);
                //获取控件对象
                listItemView.week = (TextView) convertView.findViewById(R.id.week);
                listItemView.day = (TextView) convertView.findViewById(R.id.day);
                listItemView.content = (TextView) convertView.findViewById(R.id.content);
                //设置控件集到convertView
                convertView.setTag(listItemView);
            } else{
                listItemView = new ListItemView();
                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.listview_two, null);
                //获取控件对象
                listItemView.point = (ImageView) convertView.findViewById(R.id.imageView6);
                //设置控件集到convertView
                convertView.setTag(listItemView);
                return convertView;
            }
    //    }

        listItemView.week.setText(listItems.get(position).getText("week"));
        listItemView.day.setText(listItems.get(position).getText("day"));
        listItemView.content.setText(listItems.get(position).getText("content"));

        //注册按钮点击时间爱你
       /* listItemView.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return convertView;
    }

}