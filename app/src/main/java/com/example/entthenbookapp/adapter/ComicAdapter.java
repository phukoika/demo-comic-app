package com.example.entthenbookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.entthenbookapp.R;
import com.example.entthenbookapp.object.Comic;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends ArrayAdapter<Comic> {
    private Context ct;
    private ArrayList<Comic> arr;
    public ComicAdapter(@NonNull Context context, int resource, @NonNull List<Comic> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }
    public void sortComic (String s) {
        s = s.toUpperCase();
        int k = 0;
        for (int i = 0; i < arr.size(); i++) {
            Comic comic = arr.get(i);
            String name = comic.getName().toUpperCase();
            if(name.indexOf(s) >= 0) {
                arr.set(i, arr.get(k));
                arr.set(k,comic);
                k++;
            }
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comic,null);
        }
        if(arr.size()>0) {
            Comic comic = this.arr.get(position);
            TextView nameItem = convertView.findViewById(R.id.name_item);
            TextView chapItem = convertView.findViewById(R.id.chap_item);
            ImageView imgItem = convertView.findViewById(R.id.img_item);

            nameItem.setText(comic.getName());
            chapItem.setText(comic.getChap());
            Glide.with(this.ct).load(comic.getImgUrl()).into(imgItem);
        }
        return convertView;
    }
}
