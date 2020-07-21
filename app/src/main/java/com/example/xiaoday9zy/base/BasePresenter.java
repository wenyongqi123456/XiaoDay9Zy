package com.example.xiaoday9zy.base;

import java.util.ArrayList;
import java.util.BitSet;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    public void addModel(BaseModel baseModel){
        models.add(baseModel);
    }

    public void bindView(V view){
       this.mView=view;
    }

    public void destory(){

        mView=null;
        for (int i=0;i<models.size();i++){
            BaseModel baseModel = models.get(i);
            baseModel.disPose();
        }
    }

    protected abstract void initModel();


}
