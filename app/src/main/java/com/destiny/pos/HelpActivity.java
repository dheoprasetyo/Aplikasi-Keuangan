package com.destiny.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class HelpActivity extends AppCompatActivity {
    ImageView dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        dashboard=(ImageView)findViewById(R.id.btnDashboard);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        String text = "1.Pilih Menu<br>" +
                "2.Klik Menu Utama. Tekan Input<br>" +
                "3.Pilih Tipe,Kategori,Jenis<br>" +
                "4.Masukan Jumlah Dan Keterangan<br>" +
                "5.Klik Tombol SAVE untuk Menyimpan dan tombol cancel untuk kembal<br>" +
                "6.Klik Menu Laporan untuk Melihat Laporan<br>" +
                "7.Klik Menu Export,Untuk Mengkspor Data Keuangan dalam bentuk Word<br>";

        WebView webView = (WebView)findViewById(R.id.webview);
        webView.loadData("<p style\"text-align: justify\">"+text+"</p>","text/html","UTF-8");
    }
}
