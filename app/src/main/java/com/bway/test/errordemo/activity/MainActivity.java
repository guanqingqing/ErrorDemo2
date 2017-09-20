package com.bway.test.errordemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bway.test.errordemo.R;
import com.bway.test.errordemo.adapter.MyAdapter;
import com.bway.test.errordemo.bean.Data;
import com.bway.test.errordemo.utils.NetDataCallBack;
import com.bway.test.errordemo.utils.Okhttp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NetDataCallBack<Data> {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<Data.TopStoriesBean> mlist = new ArrayList<>();
    private MyAdapter myadapter;
    private String path = "https://news-at.zhihu.com/api/4/news/latest";
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        System.out.println(s.equals("any string"));
        initview();
        LoadData();

    }

    private void initview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        myadapter = new MyAdapter(this,mlist);
        recyclerview.setAdapter(myadapter);

        myadapter.setSetItemimageClick(new MyAdapter.setItemimageClick() {
            @Override
            public void setimageClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                intent.putExtra("imagpath",mlist.get(position).getImage());
                startActivity(intent);
            }
        });

        myadapter.setSetLongItemClick(new MyAdapter.setLongItemClick() {
            @Override
            public void setimageClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });


    }
    private void LoadData() {
        Okhttp okhttp = new Okhttp();
        okhttp.getdata(path,this,Data.class);
    }


    @Override
    public void success(Data data) {
        mlist.addAll(data.getTop_stories());
        myadapter.notifyDataSetChanged();
    }

    @Override
    public void faild(int positon, String str) {

    }
}
