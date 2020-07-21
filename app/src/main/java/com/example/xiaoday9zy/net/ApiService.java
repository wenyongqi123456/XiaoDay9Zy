package com.example.xiaoday9zy.net;

import com.example.xiaoday9zy.bean.ProBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl="https://www.wanandroid.com/project/";
    @GET("list/1/json?cid=294")
    Observable<ProBean> getData();
}
