package com.serifgungor.sqlitenotdefteri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.serifgungor.sqlitenotdefteri.Model.Not;
import com.serifgungor.sqlitenotdefteri.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<Not> liste;
    LayoutInflater layoutInflater;

    public ListViewAdapter(){

    }

    public ListViewAdapter(ArrayList<Not> liste, Context context){
        this.liste = liste;
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /*
        Dolu constructor sayesinde MainActivity sınıfındaki ArrayList'deki değerleri
        Adapter sınıfı içerisine göndeririz.
         */
    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Not getItem(int position) {
        return liste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.listview_satir_goruntusu,null);

        TextView tvBaslik = v.findViewById(R.id.tvBaslik);
        TextView tvKonu = v.findViewById(R.id.tvKonu);
        tvBaslik.setText(liste.get(position).getBaslik());
        tvKonu.setText(liste.get(position).getKonu());

        return v;
    }
}
