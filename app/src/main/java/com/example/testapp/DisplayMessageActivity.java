package com.example.testapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new TimeThread().start(); //启动新的线程

        Intent intent = getIntent();

        ImageView imageView = findViewById(R.id.templateImage);
        imageView.setImageResource(R.drawable.template);

        TextView textNum = findViewById(R.id.textNum);
        textNum.setText(intent.getStringExtra("num"));
        TextView textName = findViewById(R.id.textName);
        textName.setText(intent.getStringExtra("name"));
        TextView textCollege = findViewById(R.id.textCollege);
        textCollege.setText(intent.getStringExtra("college"));
        TextView textGrade = findViewById(R.id.textGrade);
        textGrade.setText(intent.getStringExtra("grade"));
        TextView textOrientation = findViewById(R.id.textOrientation);
        ImageView imagePicture = findViewById(R.id.imagePicture);
        imagePicture.setImageURI(Uri.parse(intent.getStringExtra("picture")));


        if(intent.getBooleanExtra("orientation", false)){
            textOrientation.setText("批准入校");
        }
        else{
            textOrientation.setText("批准出校");
        }


    }


    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);

        }
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    TextView textTime = findViewById(R.id.textTime);
                    textTime.setText(new SimpleDateFormat("yyyy/M/d aahh:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())));
                    break;
            }
            return false;
        }
    });

}