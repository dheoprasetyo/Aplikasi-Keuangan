package com.destiny.pos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.destiny.pos.Model.GiftitemPOJO;
import com.destiny.pos.Model.Model;
import com.destiny.pos.Model.Models;
import com.destiny.pos.SharedPreferance.DB_Helper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaporanActivity extends AppCompatActivity {
    private static final String TAG = "PdfCreatorActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private File pdfFile;
    ArrayList<Models> MyList1;
    Models giftitemPOJO;
    Models deskripsi;
    Models tanggal;
    Models tipe;
    Models kategori;
    Models jumlah;
    Models jenis;
    Models keterangan;
    DB_Helper dbHelper;
    TextView pengeluaranPribadi,pemasukanPribadi,selisihPribadi,pengeluaranUsaha,pemasukanUsaha,selisihUsaha;
    ImageView Home,Utama,Help;
    Button export;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        dbHelper = new DB_Helper(this);
        pengeluaranPribadi=(TextView)findViewById(R.id.tvPengeluaranPribadi);
        pemasukanPribadi=(TextView)findViewById(R.id.tvPemasukanPribadi);
        selisihPribadi=(TextView)findViewById(R.id.tvSelisihPribadi);
        pengeluaranUsaha=(TextView)findViewById(R.id.tvPengeluaranUsaha);
        pemasukanUsaha=(TextView)findViewById(R.id.tvPengeluaranUsaha);
        selisihUsaha=(TextView)findViewById(R.id.tvSelisihUsaha);
        export=(Button)findViewById(R.id.exoirtExcel);
        Home=(ImageView)findViewById(R.id.ivMenuHome);
        Utama=(ImageView)findViewById(R.id.ivMenuUtama);
        Help=(ImageView)findViewById(R.id.ivMenuHelp);
        pribadi();
        usaha();
        giftitemPOJO = new Models();
        DoLOgin doLOgin=new DoLOgin();
        doLOgin.execute();
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaporanActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaporanActivity.this,HelpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void pribadi(){
        Cursor cursorPengeluaran = dbHelper.Hitung("Pengeluaran","Pribadi");
        String pengeluaran = null;
        int Luar = 0;
        while (cursorPengeluaran.moveToNext()){
            pengeluaran=cursorPengeluaran.getString(5);
            if (pengeluaran==null){
                pengeluaran="0";
            }
            Luar = Luar + Integer.parseInt(pengeluaran);
        }
        Cursor cursorPemasukan = dbHelper.Hitung("Pemasukan","Pribadi");
        String pemasukan = null;
        int Masuk = 0;
        while (cursorPemasukan.moveToNext()){
            pemasukan=cursorPemasukan.getString(5);
            if (pemasukan==null){
                pemasukan="0";
            }
            Masuk = Masuk + Integer.parseInt(pemasukan);
        }
        pemasukanPribadi.setText(String.valueOf(Masuk));
        pengeluaranPribadi.setText(String.valueOf(Luar));
        int selisih = Masuk-Luar;
        selisihPribadi.setText(String.valueOf(selisih));
    }

    private void usaha(){
        Cursor cursorPengeluaran = dbHelper.Hitung("Pengeluaran","Usaha");
        String pengeluaran = null;
        int Luar = 0;
        while (cursorPengeluaran.moveToNext()){
            pengeluaran=cursorPengeluaran.getString(5);
            if (pengeluaran==null){
                pengeluaran="0";
            }
            Luar = Luar + Integer.parseInt(pengeluaran);
        }
        Cursor cursorPemasukan = dbHelper.Hitung("Pemasukan","Usaha");
        String pemasukan = null;
        int Masuk = 0;
        while (cursorPemasukan.moveToNext()){
            pemasukan=cursorPemasukan.getString(5);
            if (pemasukan==null){
                pemasukan="0";
            }
            Masuk = Masuk + Integer.parseInt(pemasukan);
        }
        pemasukanUsaha.setText(String.valueOf(Masuk));
        pengeluaranUsaha.setText(String.valueOf(Luar));
        int selisih = Masuk-Luar;
        selisihUsaha.setText(String.valueOf(selisih));

    }


    private void createPdfWrapper() throws FileNotFoundException, DocumentException {

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        String pdfname = "POS.pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(), pdfname);
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document(PageSize.A4);
        PdfPTable table = new PdfPTable(new float[]{3, 3, 3, 3, 3, 3, 3});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell("Deskripsi");
        table.addCell("Tanggal");
        table.addCell("Tipe");
        table.addCell("Kategori");
        table.addCell("Jumlah");
        table.addCell("Jenis");
        table.addCell("Keterangan");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (int i = 0; i < MyList1.size(); i++) {
            deskripsi = MyList1.get(i);
            tanggal = MyList1.get(i);
            tipe = MyList1.get(i);
            kategori = MyList1.get(i);
            jumlah = MyList1.get(i);
            jenis = MyList1.get(i);
            keterangan = MyList1.get(i);
            String deskripsin = deskripsi.getDeskripsi();
            String tanggaln = tanggal.getTanggal();
            String tipen = tipe.getTipe();
            String kategorin = kategori.getKategori();
            String jumlahn = jumlah.getJumlah();
            String jenisn = jenis.getJumlah();
            String keterangann = keterangan.getKeterangan();

            table.addCell(String.valueOf(deskripsin));
            table.addCell(String.valueOf(tanggaln));
            table.addCell(String.valueOf(tipen));
            table.addCell(String.valueOf(kategorin));
            table.addCell(String.valueOf(jumlahn));
            table.addCell(String.valueOf(jenisn));
            table.addCell(String.valueOf(keterangann));
        }

//        System.out.println("Done");

        PdfWriter.getInstance(document, output);
        document.open();
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 30.0f, Font.UNDERLINE, BaseColor.BLUE);
        Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLUE);
        document.add(new Paragraph("Aplikasi Keuangan \n", g));
        document.add(new Paragraph("Untuk Mahasiswa \n", g));
        document.add(table);

//        for (int i = 0; i < MyList1.size(); i++) {
//            document.add(new Paragraph(String.valueOf(MyList1.get(i))));
//        }
        document.close();
        Log.e("safiya", MyList1.toString());
        previewPdf();
    }

    private void previewPdf() {
        PackageManager packageManager = this.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            Toast.makeText(this, "Laporan di Save dalam document/KeuanganMahasiswa", Toast.LENGTH_SHORT).show();
//            intent.setDataAndType(uri, "application/pdf");
//            this.startActivity(intent);
        } else {
            Toast.makeText(this, "Download a PDF Viewer to see the generated PDF", Toast.LENGTH_SHORT).show();
        }
    }

    class DoLOgin extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            dbHelper = new DB_Helper(LaporanActivity.this);
            Cursor cursor = dbHelper.Checker();
            MyList1 = new ArrayList<Models>();
            while (cursor.moveToNext()){
                giftitemPOJO.setDeskripsi(cursor.getString(1));
                giftitemPOJO.setTanggal(cursor.getString(2));
                giftitemPOJO.setTipe(cursor.getString(3));
                giftitemPOJO.setKategori(cursor.getString(4));
                giftitemPOJO.setJumlah(cursor.getString(5));
                giftitemPOJO.setJenis(cursor.getString(6));
                giftitemPOJO.setKeterangan(cursor.getString(7));
                MyList1.add(giftitemPOJO);
                giftitemPOJO = new Models();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
}
