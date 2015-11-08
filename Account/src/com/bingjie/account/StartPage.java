package com.bingjie.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class StartPage extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startpage);
		getSupportActionBar().hide();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(StartPage.this, MainActivity.class); // 从启动动画ui跳转到主ui
				startActivity(intent);
				StartPage.this.finish(); // 结束启动动画界面
			}
		}, 1000); // 启动动画持续3秒钟
	}

}
