package com.example.admin.uiwu;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class sbsoActivity extends AppCompatActivity {


    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbso);

        editText = findViewById(R.id.ll1);
        new Thread(){
            public void run(){
                String date=PostGetUtil.SendGetRequest("id=1");
                PostGetUtil.SendPostRequest("id=1");
                Message msg=new Message();
                msg.obj=date;
                msg.what=1;
                handler.sendMessage(msg);
            }
        }.start();
    }



    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(getApplicationContext(),"Toast我的发布"+msg.obj.toString(),Toast.LENGTH_LONG).show();
                    editText.setText(msg.obj.toString());



            }
        }
    };

}
