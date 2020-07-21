package com.example.xiaoday9zy.base;

import java.util.IllegalFormatCodePointException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {

    private CompositeDisposable compositeDisposable = null;

    public void addDispose(Disposable disposable) {
        if (compositeDisposable == null) {
            synchronized (BaseModel.class) {
                if (compositeDisposable == null) {
                    compositeDisposable = new CompositeDisposable();
                }
            }

        }
        compositeDisposable.add(disposable);
    }

    public void disPose() {
        compositeDisposable.dispose();
    }

    public void removeDispose(Disposable disposable) {

        if (compositeDisposable != null) {
            compositeDisposable.remove(disposable);

        }
    }
}
