package com.example.xiaoday9zy.presenter;

import com.example.xiaoday9zy.MainActivity;
import com.example.xiaoday9zy.base.BaseModel;
import com.example.xiaoday9zy.base.BasePresenter;
import com.example.xiaoday9zy.base.BaseView;
import com.example.xiaoday9zy.bean.ProBean;
import com.example.xiaoday9zy.model.FragmentModel;
import com.example.xiaoday9zy.net.FragmentCallBack;
import com.example.xiaoday9zy.view.FragmentView;

public class FragmentPresenter extends BasePresenter<FragmentView> implements FragmentCallBack {
    public FragmentModel model;

    @Override
    protected void initModel() {
        model = new FragmentModel();
        addModel(model);
    }
    public void getData(){

        model.getData(this);
    }

    @Override
    public void onSuccess(ProBean proBean) {
        mView.getData(proBean);


    }

    @Override
    public void onFail(String error) {
        mView.showToast(error);

    }
}
