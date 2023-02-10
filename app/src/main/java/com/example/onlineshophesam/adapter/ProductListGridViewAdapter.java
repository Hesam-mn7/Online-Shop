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
import com.example.onlineshophesam.entity.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductListGridViewAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<Product> arrayList;
    private ArrayList<Product> temp;
    private int layoutId;

    public ProductListGridViewAdapter(Context context, ArrayList<Product> arrayList , int layoutId) {
        this.context = context;
        this.arrayList = arrayList;
        this.temp = arrayList;
        this.layoutId = layoutId;
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

        if(view == null){
            view = LayoutInflater.from(context).inflate(layoutId,null);

        }

        Product product = arrayList.get(position);

        TextView tvidProduct = view.findViewById(R.id.tvidProduct);
        TextView tvtitleProduct = view.findViewById(R.id.tvtitleProduct);
        TextView tvmodelProduct = view.findViewById(R.id.tvmodelProduct);
        TextView tvvalueProduct = view.findViewById(R.id.tvvalueProduct);
        ImageView imgProduct = view.findViewById(R.id.imgProduct);

        tvtitleProduct.setText(String.valueOf(product.getTitleProduct()));
        tvvalueProduct.setText(String.valueOf(product.getValueProduct()+" تومان"));
        tvmodelProduct.setText((product.getModelProduct())+" مدل");
        tvidProduct.setText("کالای شماره "+String.valueOf(product.getIdProduct()));
        Picasso.get()
                .load(product.getImgProduct())
                .placeholder(R.drawable.loadingg)
                .error(R.drawable.nophoto)
                .into(imgProduct);

        return view;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence filterRequest) {
                FilterResults filterResults = new FilterResults();

                ArrayList<Product> filterlist = new ArrayList<>();
                for(Product item : temp){
                    if(item.getTitleProduct().contains(filterRequest)){
                        filterlist.add(item);
                    }
                }
                filterResults.values=filterlist;
                filterResults.count=filterlist.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayList = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}


