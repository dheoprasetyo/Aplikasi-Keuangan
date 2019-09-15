package com.destiny.pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.destiny.pos.Adapter.DataAdapter;
import com.destiny.pos.Model.Model;
import com.destiny.pos.SharedPreferance.DB_Helper;

import java.util.ArrayList;

public class UtamaActivity extends AppCompatActivity {
    TextView Pengeluaran,Pemasukan,Selisih;
    Button Deskripsi,InputData;
    ImageView Home,Utama,Help;
    RecyclerView recycler;
    boolean onClicked = true;
    private ArrayList<Model> pList = new ArrayList<>();
    DB_Helper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        Pengeluaran=(TextView)findViewById(R.id.tvPengeluaran);
        Pemasukan=(TextView)findViewById(R.id.tvPemasukan);
        Selisih=(TextView)findViewById(R.id.tvSelisih);
        Deskripsi=(Button)findViewById(R.id.btnDeskripsi);
        InputData=(Button)findViewById(R.id.btnInput);
        Home=(ImageView)findViewById(R.id.ivMenuHome);
        Utama=(ImageView)findViewById(R.id.ivMenuUtama);
        Help=(ImageView)findViewById(R.id.ivMenuHelp);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        data();
        Deskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClicked){
                    Deskripsi.setText("Pribadi");
                    data();
                    onClicked=false;
                }else{
                    Deskripsi.setText("Usaha");
                    data();
                    onClicked=true;
                }
            }
        });
        InputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(UtamaActivity.this, InputActivity.class);
                goInput.putExtra("IO","INPUT");
                UtamaActivity.this.startActivities(new Intent[]{goInput});
                finish();
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UtamaActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UtamaActivity.this,HelpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void data(){
        recycler.setHasFixedSize(true);
        dbHelper = new DB_Helper(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter dataAdapter = new DataAdapter(dbHelper.ListDeskripsii(Deskripsi.getText().toString()),this,recycler);
        //DataAdapter dataAdapter = new DataAdapter(dbHelper.List(Deskripsi.getText().toString()),this,recycler);
        recycler.setAdapter(dataAdapter);
        Cursor cursorPengeluaran = dbHelper.Hitung("Pengeluaran",Deskripsi.getText().toString());
        String pengeluaran = null;
        int Luar = 0;
        while (cursorPengeluaran.moveToNext()){
            pengeluaran=cursorPengeluaran.getString(5);
            if (pengeluaran==null){
                pengeluaran="0";
            }
            Luar = Luar + Integer.parseInt(pengeluaran);
        }
        Cursor cursorPemasukan = dbHelper.Hitung("Pemasukan",Deskripsi.getText().toString());
        String pemasukan = null;
        int Masuk = 0;
        while (cursorPemasukan.moveToNext()){
            pemasukan=cursorPemasukan.getString(5);
            if (pemasukan==null){
                pemasukan="0";
            }
            Masuk = Masuk + Integer.parseInt(pemasukan);
        }
        Pemasukan.setText(String.valueOf(Masuk));
        Pengeluaran.setText(String.valueOf(Luar));
        int selisih = Integer.parseInt(Pemasukan.getText().toString())-Integer.parseInt(Pengeluaran.getText().toString());
        Selisih.setText(String.valueOf(selisih));
    }
}
