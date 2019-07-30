package com.example.administrator.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.ProductPK.Product;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class ProAdapterList extends BaseAdapter{
    ArrayList<Product> myProduct;
    int myLayout;
    Activity myContext;

    public ProAdapterList(Activity context, int layout, ArrayList<Product> Product) {
        this.myContext = context;
        this.myLayout = layout;
        this.myProduct = Product;
    }

    public int getCount() {
        return this.myProduct.size();
    }

    public Object getItem(int position) {
        return this.myProduct.get(position);
    }

    public long getItemId(int position) {
        return 0L;
    }

    @SuppressLint("ResourceType")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.myContext.getLayoutInflater();
        convertView=inflater.inflate(R.layout.custom_list_product, parent,false);

        TextView txtID=(TextView)convertView.findViewById(R.id.lblID);
        txtID.setText(myProduct.get(position).getID()+" ");

        TextView txtCodeProduct=(TextView)convertView.findViewById(R.id.lblCodeProduct);
        txtCodeProduct.setText(myProduct.get(position).getCodeProduct());

        TextView txtNameProduct=(TextView)convertView.findViewById(R.id.lblNameProduct);
        txtNameProduct.setText(myProduct.get(position).getNameProduct());

        TextView txtPriceProduct=(TextView)convertView.findViewById(R.id.lblPriceProduct);
        txtPriceProduct.setText(myProduct.get(position).getPriceProduct()+" ");

        TextView txtKindProduct=(TextView)convertView.findViewById(R.id.lblKindProduct);
        txtKindProduct.setText(myProduct.get(position).getKindProduct());

        return convertView;
    }

    public void notifyDataSetChanged() {
    }
}
