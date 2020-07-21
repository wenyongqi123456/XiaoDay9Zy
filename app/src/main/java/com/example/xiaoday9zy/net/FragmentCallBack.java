package com.example.xiaoday9zy.net;

import com.example.xiaoday9zy.bean.ProBean;

public interface FragmentCallBack {
    void onSuccess(ProBean proBean);
    void onFail(String error);
}
