package com.example.administrator.viewutilsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.viewutilsdemo.utils.OnClick;
import com.example.administrator.viewutilsdemo.utils.ViewInject;
import com.example.administrator.viewutilsdemo.utils.ViewUtils;


public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tv1)
    private TextView tv1;
    @ViewInject(R.id.tv2)
    private TextView tv2;

    private int a ; // 自定义变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        // 测试成功
        Log.i("TAG", "onCreate: "+tv1.getText()+tv2.getText());

    }

    @OnClick(R.id.btn1)
    public void clickme(View view){
        Toast.makeText(this, "点击我了", Toast.LENGTH_SHORT).show();
    }
}
