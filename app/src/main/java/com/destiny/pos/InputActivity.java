package com.destiny.pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.destiny.pos.Model.Model;
import com.destiny.pos.SharedPreferance.DB_Helper;

import java.text.DateFormat;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button Tanggal,Deskripsi,Tipe,Jenis,Kategori,Save,Cancel,Pribadi,DLL,Usaha,Pengeluaran,Pemasukan,Transportasi,Pulsa,PaketData,TokenListrik,Lunas,Hutang,Fotocopy,Makan;
    Button GajiAslab,Bonus,Freelancer,SumberDariOrtu,HasilUsaha;
    EditText Jumlah,Keterangan;
    ImageView Home,Utama,Help;
    DB_Helper dbHelper;
    Dialog dialogDeskkripsi,dialogTipe,dialogkategori,dialogJenis,dialogPribadiKeluaran,dialogPribadiPemasukan;
    LinearLayout linearJenis;
    String id=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Tanggal=(Button)findViewById(R.id.btnPilihTanggal);
        Deskripsi=(Button)findViewById(R.id.btnDeskripsi);
        Tipe=(Button)findViewById(R.id.btnTipe);
        Jenis=(Button)findViewById(R.id.btnJenis);
        Kategori=(Button)findViewById(R.id.btnKategori);
        Save=(Button)findViewById(R.id.btnSave);
        Cancel=(Button)findViewById(R.id.btnCancel);
        Jumlah=(EditText)findViewById(R.id.etJumlah);
        Keterangan=(EditText)findViewById(R.id.etKeterangan);
        Home=(ImageView)findViewById(R.id.ivMenuHome);
        Utama=(ImageView)findViewById(R.id.ivMenuUtama);
        Help=(ImageView)findViewById(R.id.ivMenuHelp);
        linearJenis=(LinearLayout)findViewById(R.id.linearJenis);
        Intent data = getIntent();
        //Get IntentData
        final String IO = data.getStringExtra("IO");
        if (IO.equals("Update")){
            id = data.getStringExtra("ID");
            final String deskripsi = data.getStringExtra("DESKRIPSI");
            final String tanggal = data.getStringExtra("TANGGAL");
            final String tipe = data.getStringExtra("TIPE");
            final String kategori = data.getStringExtra("KATEGORI");
            final String jumlah = data.getStringExtra("JUMLAH");
            final String jenis = data.getStringExtra("JENIS");
            final String keterangan = data.getStringExtra("KETERANGAN");
            Deskripsi.setText(deskripsi);
            Tanggal.setText(tanggal);
            Tipe.setText(tipe);
            Kategori.setText(kategori);
            Jumlah.setText(jumlah);
            Jenis.setText(jenis);
            Keterangan.setText(keterangan);
            Save.setText("Update");
            if (deskripsi.equals("Usaha")){
                linearJenis.setVisibility(View.VISIBLE);
            }
        }else{
            Save.setText("Save");
        }
        //Deskripsi
        dialogDeskkripsi = new Dialog(InputActivity.this);
        dialogDeskkripsi.setContentView(R.layout.dialog_deskripsi);
        Pribadi = (Button)dialogDeskkripsi.findViewById(R.id.btnPribadi);
        Usaha = (Button)dialogDeskkripsi.findViewById(R.id.btnUsaha);
        Deskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDeskkripsi.show();
            }
        });
        Pribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deskripsi.setText("Pribadi");
                dialogDeskkripsi.hide();
                linearJenis.setVisibility(View.GONE);
            }
        });
        Usaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deskripsi.setText("Usaha");
                dialogDeskkripsi.hide();
                linearJenis.setVisibility(View.VISIBLE);
            }
        });
        //DONE


        //Tipe
        dialogTipe = new Dialog(InputActivity.this);
        dialogTipe.setContentView(R.layout.dialog_tipe);
        Pengeluaran = (Button)dialogTipe.findViewById(R.id.btnPengeluaran);
        Pemasukan = (Button)dialogTipe.findViewById(R.id.btnPemasukan);
        Tipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTipe.show();
            }
        });
        Pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tipe.setText("Pengeluaran");
                dialogTipe.hide();
            }
        });
        Pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tipe.setText("Pemasukan");
                dialogTipe.hide();
            }
        });
        //DONE


        //Dialog Kategori
        Kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Deskripsi.getText().toString().equals("Pribadi")){
                    if (Tipe.getText().toString().equals("Pemasukan")){
                        dialogPribadiPemasukan = new Dialog(InputActivity.this);
                        dialogPribadiPemasukan.setContentView(R.layout.dialog_kategori_pribadi_pemasukan);
                        GajiAslab=(Button)dialogPribadiPemasukan.findViewById(R.id.btnGajiAslab);
                        Freelancer=(Button)dialogPribadiPemasukan.findViewById(R.id.btnFreelancer);
                        SumberDariOrtu=(Button)dialogPribadiPemasukan.findViewById(R.id.btnOrtu);
                        HasilUsaha=(Button)dialogPribadiPemasukan.findViewById(R.id.btnHasilUsaha);
                        dialogPribadiPemasukan.show();
                        GajiAslab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Gaji Aslab");
                                dialogPribadiPemasukan.hide();
                            }
                        });
                        Freelancer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Freelancer");
                                dialogPribadiPemasukan.hide();
                            }
                        });
                        SumberDariOrtu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Sumber Dari Orangtua");
                                dialogPribadiPemasukan.hide();
                            }
                        });
                        HasilUsaha.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Hasil Usaha");
                                dialogPribadiPemasukan.hide();
                            }
                        });
                    }else if(Tipe.getText().toString().equals("Pengeluaran")){
                        dialogPribadiKeluaran = new Dialog(InputActivity.this);
                        dialogPribadiKeluaran.setContentView(R.layout.dialog_kategori_pribadi_pengeluaran);
                        Transportasi=(Button)dialogPribadiKeluaran.findViewById(R.id.btnTransportasi);
                        Fotocopy=(Button)dialogPribadiKeluaran.findViewById(R.id.btnFotocopy);
                        Makan=(Button)dialogPribadiKeluaran.findViewById(R.id.btnMakan);
                        TokenListrik=(Button)dialogPribadiKeluaran.findViewById(R.id.btnTokenListrik);
                        Pulsa=(Button)dialogPribadiKeluaran.findViewById(R.id.btnPulsa);
                        PaketData=(Button)dialogPribadiKeluaran.findViewById(R.id.btnPaketData);
                        dialogPribadiKeluaran.show();

                        Transportasi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Transportasi");
                                dialogPribadiKeluaran.hide();
                            }
                        });
                        Fotocopy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Fotocopy");
                                dialogPribadiKeluaran.hide();
                            }
                        });
                        Makan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Makan");
                                dialogPribadiKeluaran.hide();
                            }
                        });
                        TokenListrik.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Token Listrik");
                                dialogPribadiKeluaran.hide();
                            }
                        });
                        Pulsa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Pulsa");
                                dialogPribadiKeluaran.hide();
                            }
                        });
                        PaketData.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Kategori.setText("Paket Data");
                                dialogPribadiKeluaran.hide();
                            }
                        });
                    }
                }else{
                    dialogkategori = new Dialog(InputActivity.this);
                    dialogkategori.setContentView(R.layout.dialog_kategori);
                    DLL = (Button)dialogkategori.findViewById(R.id.btnDLL);
                    Pulsa= (Button)dialogkategori.findViewById(R.id.btnPulsa);
                    PaketData = (Button)dialogkategori.findViewById(R.id.btnPaketData);
                    TokenListrik = (Button)dialogkategori.findViewById(R.id.btnTokenListrik);
                    dialogkategori.show();
                    DLL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Kategori.setText("Dan Lain Lain");
                            dialogkategori.hide();
                        }
                    });
                    Pulsa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Kategori.setText("Pulsa");
                            dialogkategori.hide();
                        }
                    });
                    PaketData.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Kategori.setText("Paket Data");
                            dialogkategori.hide();
                        }
                    });
                    TokenListrik.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Kategori.setText("Token Listrik");
                            dialogkategori.hide();
                        }
                    });
                }
            }
        });

        //DONE

        //Dialog Jenis
        dialogJenis = new Dialog(InputActivity.this);
        dialogJenis.setContentView(R.layout.dialog_jenis);
        Lunas = (Button)dialogJenis.findViewById(R.id.btnLunas);
        Hutang = (Button)dialogJenis.findViewById(R.id.btnHutang);
        Jenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogJenis.show();
            }
        });
        Lunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jenis.setText("Lunas");
                dialogJenis.hide();
            }
        });
        Hutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jenis.setText("Hutang");
                dialogJenis.hide();
            }
        });


        Tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Tanggal.getText().toString().equals("Pilih Tanggal")){
                    Toast.makeText(InputActivity.this, "Isi Tanggal !!", Toast.LENGTH_SHORT).show();
                }else if(Jumlah.getText().toString().isEmpty()){
                    Toast.makeText(InputActivity.this, "Isi Jumlah !!", Toast.LENGTH_SHORT).show();
                }else if(Keterangan.getText().toString().isEmpty()){
                    Toast.makeText(InputActivity.this, "Isi Keterangan !!", Toast.LENGTH_SHORT).show();
                }else{
                    if (IO.equals("Update")){
                        dbHelper = new DB_Helper(InputActivity.this);
                        Model model = new Model(id,Deskripsi.getText().toString(),Tanggal.getText().toString(),
                                Tipe.getText().toString(),Kategori.getText().toString(),
                                Jumlah.getText().toString(),Jenis.getText().toString(),Keterangan.getText().toString());
                        dbHelper.updateData(model);
                        Intent intent = new Intent(InputActivity.this,UtamaActivity.class);
                        startActivity(intent);
                    }else{
                        dbHelper = new DB_Helper(InputActivity.this);
                        Cursor cursor = dbHelper.Checker();
                        while (cursor.moveToNext()){
                            id=cursor.getString(0);
                        }
                        if (id==null){
                            id="0";
                        }else{
                            int intID = Integer.parseInt(id);
                            id=String.valueOf(intID+1);
                        }
                        Model model;
                        model = new Model(id,Deskripsi.getText().toString(),Tanggal.getText().toString(),
                                Tipe.getText().toString(),Kategori.getText().toString(),
                                Jumlah.getText().toString(),Jenis.getText().toString(),Keterangan.getText().toString());
                        dbHelper.InsertData(model);
                        Toast.makeText(InputActivity.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InputActivity.this,UtamaActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this,UtamaActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this,HelpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        Tanggal.setText(currentDateString);
    }
}
