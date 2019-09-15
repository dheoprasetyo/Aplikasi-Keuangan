package com.destiny.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class TentangActivity extends AppCompatActivity {
    ImageView dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        dashboard=(ImageView)findViewById(R.id.btnDashboard);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TentangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        String text = "Aplikasi Dibuat Oleh : <br>" +
                "Nama  : ....<br>" +
                "NPM   : ....<br>" +
                "Kelas : ....<br>";

        WebView webView = (WebView)findViewById(R.id.webview);
        webView.loadData("<p style\"text-align: justify\">"+text+"</p>","text/html","UTF-8");
    }
}
