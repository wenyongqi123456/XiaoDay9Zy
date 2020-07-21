package com.example.xiaoday9zy.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaoday9zy.DownActivity;
import com.example.xiaoday9zy.R;
import com.example.xiaoday9zy.adapter.AutoAdapter;
import com.example.xiaoday9zy.base.BaseFragment;
import com.example.xiaoday9zy.bean.ProBean;
import com.example.xiaoday9zy.presenter.FragmentPresenter;
import com.example.xiaoday9zy.view.FragmentView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment<FragmentPresenter> implements FragmentView {


    @BindView(R.id.rv_first)
    RecyclerView rvFirst;
    private ArrayList<ProBean.DataBean.DatasBean> list;
    private AutoAdapter autoAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_first, container, false);
//
//        return view;
//    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getData();

    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        autoAdapter = new AutoAdapter(getActivity(), list);
        rvFirst.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvFirst.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFirst.setAdapter(autoAdapter);
        autoAdapter.setOnItemClickListener(new AutoAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), DownActivity.class));
            }
        });


    }

    @Override
    protected void initPresenter() {
        mPresenter = new FragmentPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_first;
    }


    @Override
    public void getData(ProBean proBean) {
        list.addAll(proBean.getData().getDatas());
        autoAdapter.notifyDataSetChanged();


    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(),str, Toast.LENGTH_SHORT).show();
    }
}
