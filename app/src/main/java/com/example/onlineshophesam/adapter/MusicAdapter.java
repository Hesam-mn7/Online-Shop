package com.example.onlineshophesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshophesam.R;
import com.example.onlineshophesam.entity.Music;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private ArrayList<Music> arrayList;
    private ArrayList<Music> temp;

    public MusicAdapter(Context context, ArrayList<Music> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.temp = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.music_main,null);
        }

        Music music =arrayList.get(position);

        TextView tvtitleMusic = view.findViewById(R.id.tvtitleMusic);
        TextView tvdetailMusic = view.findViewById(R.id.tvdetailMusic);
        ImageView imgMusic = view.findViewById(R.id.imgMusic);

        tvtitleMusic.setText(String.valueOf(music.getTitleMusic()));
        tvdetailMusic.setText(String.valueOf(music.getDetailMusic()));

        Picasso.get()
                .load(music.getImgMusic())
                .placeholder(R.drawable.loadingg)
                .error(R.drawable.nophoto)
                .into(imgMusic);

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence filterRequest) {
                FilterResults filterResults = new FilterResults();

                ArrayList<Music> filterlist = new ArrayList<>();
                for(Music item : temp){
                    if(item.getTitleMusic().contains(filterRequest) || item.getDetailMusic().contains(filterRequest)){
                        filterlist.add(item);
                    }
                }
                filterResults.values=filterlist;
                filterResults.count=filterlist.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayList = (ArrayList<Music>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
