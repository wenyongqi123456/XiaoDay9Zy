package com.example.xiaoday9zy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.xiaoday9zy.service.DownService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.pro1)
    ProgressBar pro1;
    @BindView(R.id.tv1)
    TextView tv1;
    private DownService downService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);
        initView();
        downService = new DownService();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void initView() {
        int count=1;
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownActivity.this, DownService.class);
                startService(intent);
            }
        });
        pro1.setMax((int)count);
        final int finalCount = count;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("tag", "run:下载进度 " + finalCount + "/" + count);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pro1.setProgress(finalCount);
                        tv1.setText((int) (100f * finalCount / count) + "%");
                    }
                });

                Toast.makeText(DownActivity.this, "下载成功", Toast.LENGTH_LONG).show();
                notification();
            }


        });

    }
    private void notification() {
        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String channeId = "1";
        String channeName = "final";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channeId, channeName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            nm.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this,channeId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("下载任务")
                .setContentText("下载完成")
                .build();
        nm.notify(100,notification);
    }


}
