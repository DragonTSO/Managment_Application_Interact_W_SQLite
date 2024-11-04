package com.example.managment_application.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managment_application.DTO.CatDTO;
import com.example.managment_application.R;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {
    Context context;
    ArrayList<CatDTO> listCat;
    public CatAdapter(Context context, ArrayList<CatDTO> listCat) {
        this.context = context;
        this.listCat = listCat;
    }

    @Override
    public int getCount() {
            return listCat.size();
    }

    @Override
    public Object getItem(int i) {
        return listCat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listCat.get(i).getId();
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        View row;
        if(v != null)
            row = v;
        else {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_list_cat,null);
        }

        CatDTO objcat = listCat.get(i);
        TextView tv_id = row.findViewById(R.id.tv_id);
        TextView tv_name = row.findViewById(R.id.tv_name);
        tv_id.setText(String.valueOf(objcat.getId()));
        tv_name.setText(objcat.getName());
        return row;

    }
}
