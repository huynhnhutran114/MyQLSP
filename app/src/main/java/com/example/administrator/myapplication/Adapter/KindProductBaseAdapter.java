package com.example.administrator.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.myapplication.KindProductPK.KindProduct;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class KindProductBaseAdapter extends BaseAdapter {
    private Activity myContext;
    private int myLayout;
    private ArrayList<KindProduct> myarrayKindProduct;

    public KindProductBaseAdapter(Activity context, int layout, ArrayList<KindProduct> arrayKindProduct) {
        this.myContext = context;
        this.myLayout = layout;
        this.myarrayKindProduct = arrayKindProduct;
    }

    public int getCount() {
        return this.myarrayKindProduct.size();
    }

    public Object getItem(int position) {
        return this.myarrayKindProduct.get(position);
    }

    public long getItemId(int position) {
        return 0L;
    }

    @SuppressLint("ResourceType")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.myContext.getLayoutInflater();
        convertView = inflater.inflate(R.layout.custom_list_kind_product, (ViewGroup)null);
        TextView tvStt = (TextView)convertView.findViewById(R.id.lblStt);
        TextView tvCodeKind = (TextView)convertView.findViewById(R.id.lblCodeKind);
        TextView tvNameKind = (TextView)convertView.findViewById(R.id.lblNameKind);
        tvStt.setText(String.valueOf(((KindProduct)this.myarrayKindProduct.get(position)).getStt()));
        tvCodeKind.setText(((KindProduct)this.myarrayKindProduct.get(position)).getCodeKind());
        tvNameKind.setText(((KindProduct)this.myarrayKindProduct.get(position)).getNameKind());
        return convertView;
    }
}
