package com.serifgungor.sqlitenotdefteri.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.serifgungor.sqlitenotdefteri.R;

public class EkleActivity extends AppCompatActivity {

    EditText etBaslik,etIcerik,etTarih;
    Spinner spKonu;
    Button btnKaydet;
    ArrayAdapter<String> adapter;

    public void notEkle(String dbName,String baslik,String icerik, String tarih, String konu){
        SQLiteDatabase db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("Create table if not exists Notlar(id INTEGER PRIMARY KEY, baslik VARCHAR(255),icerik TEXT, tarih VARCHAR, konu VARCHAR);");
        /*
        Eğer yoksa Notlar isminde tablo üretir.
        Tablonun kolonları parantez içerisindeki başlık ve değişken tipleri ile üretilir.
         */

        /*
        db.execSQL("insert into Notlar (baslik,icerik,tarih,konu) values('"+baslik+"','"+icerik+"','"+tarih+"','"+konu+"')");
        */

        StringBuilder sb = new StringBuilder();
        sb.append("insert into Notlar (baslik,icerik,tarih,konu)");
        sb.append(" values(");
        sb.append("'"+baslik+"'");
        sb.append(",");
        sb.append("'"+icerik+"'");
        sb.append(",");
        sb.append("'"+tarih+"'");
        sb.append(",");
        sb.append("'"+konu+"'");
        sb.append(");");

        db.execSQL(sb.toString());


        /*
        execSQL ile insert,delete,update sorguları gönderebiliriz.
         */

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,new String[]{"Eğitim","Sağlık","Alışveriş","Günlük işler"});

        etBaslik = findViewById(R.id.etEkleBaslik);
        etIcerik = findViewById(R.id.etEkleIcerik);
        etTarih = findViewById(R.id.etEkleTarih);
        spKonu = findViewById(R.id.spEkleKonu);
        btnKaydet = findViewById(R.id.btnEkleNotKaydet);
        spKonu.setAdapter(adapter);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notEkle("myDb",etBaslik.getText().toString(),etIcerik.getText().toString(),etTarih.getText().toString(),spKonu.getSelectedItem().toString());
                finish();

            }
        });
    }
}
