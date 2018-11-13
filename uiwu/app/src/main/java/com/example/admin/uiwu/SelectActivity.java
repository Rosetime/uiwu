package com.example.admin.uiwu;

import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

public class SelectActivity extends AppCompatActivity {


    public static final int SELECT_PHOTO=1;
    private ImageView imageView;
    private String img_src;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        imageView = findViewById(R.id.select_tupian);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selectImg();
            }
        });

        button = findViewById(R.id.tijc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }


    public void selectImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case SELECT_PHOTO:
                switch (resultCode){
                    case RESULT_OK:
                        Uri uri = data.getData();
                        img_src = uri.getPath();

                        System.out.println(img_src);

                        ContentResolver cr = this.getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            /* 将Bitmap设定到ImageView */
                            imageView.setImageBitmap(bitmap);

                            String[] proj = {MediaStore.Images.Media.DATA};
                            CursorLoader loader = new CursorLoader(this, uri, proj, null, null, null);
                            Cursor cursor = loader.loadInBackground();
                            if (cursor != null) {
                                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                                cursor.moveToFirst();

                                img_src = cursor.getString(column_index);//图片实际路径
                                System.out.println(img_src);

                            }
                            cursor.close();

                        } catch (FileNotFoundException e) {
                            Log.e("Exception", e.getMessage(), e);
                        }

                        break;





                }

                break;
        }

    }




    public void uploadImage() {
        System.out.println("hello wolrd and i "+img_src);
        if(img_src!=null)
        {
            Toast.makeText(getApplicationContext(),"提交完成",Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String uploadurl = "http://47.106.146.182/pictures/serve.php";
                    try {
                        File file = new File(img_src);
                        UploadUtil.uploadImage(file, uploadurl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }


        else
            Toast.makeText(getApplicationContext(),"请点击上面选取图片",Toast.LENGTH_LONG).show();


    }
}
