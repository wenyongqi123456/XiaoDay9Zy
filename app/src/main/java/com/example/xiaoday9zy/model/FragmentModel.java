package com.example.xiaoday9zy.model;

import com.example.xiaoday9zy.base.BaseModel;
import com.example.xiaoday9zy.bean.ProBean;
import com.example.xiaoday9zy.net.ApiService;
import com.example.xiaoday9zy.net.FragmentCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentModel extends BaseModel {

    public void getData(FragmentCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<ProBean> observable = apiService.getData();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDispose(d);

                    }

                    @Override
                    public void onNext(ProBean proBean) {
                        callBack.onSuccess(proBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
