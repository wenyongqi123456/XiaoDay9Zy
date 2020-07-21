package com.example.xiaoday9zy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DownService extends Service {
    public DownService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        downLoadOk();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    private void downLoadOk() {


        OkHttpClient okClient = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1")
                .get()
                .build();

        Call newCall = okClient.newCall(request);

        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", "onFailure: 网络错误" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream inputStream = response.body().byteStream();

                saveFile(inputStream, Environment.getExternalStorageDirectory() + "/bm.apk", response.body().contentLength());

            }
        });

    }

    private void saveFile(InputStream inputStream, String s, final long l) {

        int count = 1;
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(s));
            byte[] bytes = new byte[1024 * 10];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                count += length;

            }
            outputStream.close();
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
