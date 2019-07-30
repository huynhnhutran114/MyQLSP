package com.example.administrator.myapplication.ProductPK;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.myapplication.Adapter.ProAdapterList;
import com.example.administrator.myapplication.Adapter.ProductBaseAdapter;
import com.example.administrator.myapplication.Database.SQLDatabase;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {
    private final String CodeProduct = "MSP0";
    SQLDatabase database;
    ArrayList<Product> arrayList;
    Context context = this;
    ListView listPro;
    ProAdapterList proAdapterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        addControl();
        ctreaData();
    }
    public void addControl() {


        listPro=(ListView) findViewById(R.id.listproduct);
        arrayList =new ArrayList();
        proAdapterList=new ProAdapterList(ListProduct.this,R.layout.custom_list_product,this.arrayList);
        listPro.setAdapter((ListAdapter) this.proAdapterList);
    }
    public  void ctreaData()
    {
        Product product=new Product(11,"MS11","Dong ho nam",14500,"DH kim");
        arrayList.add(product);
         product=new Product(12,"MS12","Dong ho nu",15500,"DH kim");
        arrayList.add(product);
        product=new Product(13,"MS13","Dong ho de thuong",11500,"DH dien tu");
        arrayList.add(product);
        product=new Product(14,"MS14","Dong ho doremon",12500,"DH dien tu");
        arrayList.add(product);
        product=new Product(15,"MS15","Dong ho hellokity",10500,"DH dien tu");
        arrayList.add(product);
        proAdapterList.notifyDataSetChanged();
    }
}
