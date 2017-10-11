package com.example.gray.androiddesign.Test9State.Last;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gray.androiddesign.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //转发按钮
        findViewById(R.id.btn_forward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginContext.getLoginContext().forward(MainActivity.this);
            }
        });

        //注销按钮
        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginContext.getLoginContext().setmState(new LogoutState());
            }
        });

    }
}
