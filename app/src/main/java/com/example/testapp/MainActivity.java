  package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;


public class MainActivity extends AppCompatActivity {
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("hello world");
    }


    public void selectPicture(View view){
    PictureSelector
            .create(MainActivity.this, PictureSelector.SELECT_REQUEST_CODE)
            .selectPicture(true, 410, 500, 41, 50);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    //mIvImage.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                    picturePath = pictureBean.getPath();
                } else {
                    //mIvImage.setImageURI(pictureBean.getUri());
                    picturePath = pictureBean.getPath();
                }

                //使用 Glide 加载图片
                /*Glide.with(this)
                        .load(pictureBean.isCut() ? pictureBean.getPath() : pictureBean.getUri())
                        .apply(RequestOptions.centerCropTransform()).into(mIvImage);*/
            }
        }
    }

    public void show(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editNum = (EditText)findViewById(R.id.editNum);
        intent.putExtra("num", editNum.getText().toString());
        EditText editName = (EditText)findViewById(R.id.editName);
        intent.putExtra("name", editName.getText().toString());
        EditText editCollege = (EditText)findViewById(R.id.editCollege);
        intent.putExtra("college", editCollege.getText().toString());
        EditText editGrade = (EditText)findViewById(R.id.editGrade);
        intent.putExtra("grade", editGrade.getText().toString());
        Switch switchOrientation = findViewById(R.id.switchOrientation);
        intent.putExtra("orientation",switchOrientation.isChecked());
        intent.putExtra("picture", picturePath);
        startActivity(intent);
    }
}