package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.administrator.myapplication.KindProductPK.KindProductActivity;
import com.example.administrator.myapplication.ProductPK.ProductActivity;

public class Manager extends Activity{


    Button btnKindProduct,btnProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        addControl();
        evenClick();

    }
    private void addControl(){
        btnKindProduct=(Button)findViewById(R.id.btnKind);
        btnProduct=(Button)findViewById(R.id.btnProduct);
    }
    private void evenClick(){
        btnKindProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(Manager.this, KindProductActivity.class);
                startActivity(intent);
            }
        });
        btnProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(Manager.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
}