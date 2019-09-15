package com.destiny.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView Utama,Laporan,Tentang,Help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utama = (ImageView)findViewById(R.id.ivMenuUtama);
        Laporan = (ImageView)findViewById(R.id.ivMenuLaporan);
        Tentang = (ImageView)findViewById(R.id.ivTentangAplikasi);
        Help = (ImageView)findViewById(R.id.ivMenuHelp);
        Utama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UtamaActivity.class);
                startActivity(intent);
            }
        });
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HelpActivity.class);
                startActivity(intent);
            }
        });
        Tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TentangActivity.class);
                startActivity(intent);
            }
        });
        Laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LaporanActivity.class);
                startActivity(intent);
            }
        });
    }
}
