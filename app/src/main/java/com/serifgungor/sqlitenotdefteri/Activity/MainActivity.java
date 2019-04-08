package com.serifgungor.sqlitenotdefteri.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.serifgungor.sqlitenotdefteri.Adapter.ListViewAdapter;
import com.serifgungor.sqlitenotdefteri.Model.Not;
import com.serifgungor.sqlitenotdefteri.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnYeniKayit;
    ListView listView;
    ListViewAdapter adapter;
    ArrayList<Not> notlar = new ArrayList<>();

    public  void notSil(String dbName,int notId){
        SQLiteDatabase db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("delete from Notlar where id='"+notId+"'");
    }

    public void notlariListele(String dbName, String tableName) {

        SQLiteDatabase db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        db.execSQL("Create table if not exists Notlar(id INTEGER PRIMARY KEY, baslik VARCHAR(255),icerik TEXT, tarih VARCHAR, konu VARCHAR);");

        Cursor c = db.rawQuery("Select * from " + tableName, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            // Cursor'den dönen eleman sayısı 0'dan büyükse döngüyü başlat
            do {
                notlar.add(new Not(
                        c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("baslik")),
                        c.getString(c.getColumnIndex("icerik")),
                        c.getString(c.getColumnIndex("tarih")),
                        c.getString(c.getColumnIndex("konu"))
                ));

            } while (c.moveToNext());
            adapter = new ListViewAdapter(notlar, getApplicationContext());
            listView.setAdapter(adapter);
        }

        /*
        Cursor sınıfı veritabanı içerisinde gerçekleştirilen select sorgusundan dönen değerleri
        saklayan sınıfımızdır.
        Cursor sınıfından dönen değerleri do-while döngüsü içerisinde yakalayabiliriz.

        Cursor sınıfı metotları

        getString - indisini argümanda belirttiğimiz kolonun string değerini döner
        getInt - indisini argümanda belirttiğimiz kolonun int değerini döner
        getColumnIndex - ismini belirttiğimiz kolonun indisini int olarak döner

        getString(getColumnIndex("baslik")) - başlık kolonunun indisini, indise ait o anki
        String değeri döner
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        notlar.clear();
        notlariListele("myDb", "Notlar");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnYeniKayit = findViewById(R.id.btnYeniKayit);
        listView = findViewById(R.id.listView);


        btnYeniKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EkleActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Not silinsin mi ?");
                adb.setMessage("Gerçekten silinsin mi ?");
                adb.setNegativeButton("Hayır",null);
                adb.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notSil("myDb",notlar.get(position).getId());
                    }
                });
                adb.create();
                adb.show();


            }
        });


    }
}
