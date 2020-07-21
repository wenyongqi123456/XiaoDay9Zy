package com.example.xiaoday9zy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xiaoday9zy.adapter.FragmentAdapter;
import com.example.xiaoday9zy.fragment.FirstFragment;
import com.example.xiaoday9zy.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.dl)
    DrawerLayout dl;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("首页" );

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new MyFragment());
        ArrayList<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("我的");
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        vp.setAdapter(fragmentAdapter);
        tab.setupWithViewPager(vp);

    }
}
