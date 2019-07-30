package com.example.administrator.myapplication.KindProductPK;

import java.lang.reflect.Array;
import java.nio.channels.AlreadyConnectedException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.Adapter.KindProductBaseAdapter;
import com.example.administrator.myapplication.Database.SQLDatabase;
import com.example.administrator.myapplication.R;

public class KindProductActivity extends Activity{

    private final String CodeKind="MATL";
    ListView listKindProduct;
    ArrayList<KindProduct> arraykind;
    KindProduct KindProduct;
    SQLDatabase database;
    KindProductBaseAdapter adapter;
    Context context=this;
    EditText txtCodeKind,txtNameKind,txtSTT;
    Button btnAdd,btnEdit,btnDelete,btnRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_product);
        addControl();
        ShowDataListView();
        createData();
        loadData();
        EvenButton();
        EvenListView();
    }
    public void addControl(){
        listKindProduct=(ListView)findViewById(R.id.ListView);
        txtCodeKind=(EditText)findViewById(R.id.txtCodeKind);
        txtNameKind=(EditText)findViewById(R.id.txtNameKind);
        txtSTT=(EditText)findViewById(R.id.txtSTT);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnRefresh=(Button)findViewById(R.id.btnRefresh);
    }

    public void ShowDataListView()
    {
        arraykind=new ArrayList<KindProduct>();
        adapter=new KindProductBaseAdapter(KindProductActivity.this,R.layout.custom_list_kind_product,arraykind);
        listKindProduct.setAdapter(adapter);
    }

    public void createData(){
        database=new SQLDatabase(context);
        database.queryData("CREATE TABLE IF NOT EXISTS KINDPRODUCT(STT INTEGER PRIMARY KEY AUTOINCREMENT, CODEKIND VARCHAR(8) NOT NULL, NAMEKIND TEXT NOT NULL)");
        // database.queryData("INSERT INTO KINDPRODUCT VALUES(null,'MATL1','Chai')");
        //database.queryData("DELETE FROM KINDPRODUCT WHERE STT=2");

    }

    public void loadData(){
        arraykind.clear();
        Cursor data=database.getData("SELECT * FROM KINDPRODUCT");
        while(data.moveToNext()){
            int stt=data.getInt(0);
            String codeKind=data.getString(1);
            String nameKind=data.getString(2);
            arraykind.add(new KindProduct(stt,codeKind,nameKind));
        }
        adapter.notifyDataSetChanged();
        defaultData();
    }
    public void defaultData(){
        String number=String.valueOf(arraykind.size()+1);
        String NumberSTT;
        if(Integer.parseInt(number)==1)
        {
            NumberSTT="1";
        }
        else{
            NumberSTT=String.valueOf(arraykind.get(arraykind.size()-1).getStt()+1);
        }
        txtSTT.setText(NumberSTT);
        txtSTT.setEnabled(false);
        txtCodeKind.setText(CodeKind+number);
        txtCodeKind.setEnabled(false);
        txtNameKind.setText("");
    }

    public void EvenButton(){
        btnRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                defaultData();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                addKindProduct();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                editKindProduct();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                deleteKindProduct();
            }
        });
    }

    public void EvenListView(){
        listKindProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                long STT=arraykind.get(position).getStt();
                String CodeKind=arraykind.get(position).getCodeKind();
                String NameKind=arraykind.get(position).getNameKind();
                txtSTT.setText(String.valueOf(STT));
                txtCodeKind.setText(CodeKind);
                txtNameKind.setText(NameKind);
                txtCodeKind.setEnabled(true);
            }
        });
    }

    public boolean isCheckCodeKind(String CodeKind){
        for(KindProduct KP:arraykind)
        {
            if(KP.getCodeKind().equals(CodeKind))
            {
                return true;
            }
        }
        return false;
    }

    public void addKindProduct(){
        String CodeKind=txtCodeKind.getText().toString();
        String NameKind=txtNameKind.getText().toString();
        if(isCheckCodeKind(CodeKind))
        {
            Toast.makeText(context, "Mã thể loại bị trùng",Toast.LENGTH_LONG).show();
        }
        else{
            if(NameKind.equals("")){
                Toast.makeText(context, "Xin vui lòng nhập tên thể loại",Toast.LENGTH_LONG).show();
            }
            else{
                boolean isCheck=database.InsertKind(CodeKind, NameKind);
                if(isCheck==true){
                    Toast.makeText(context, "Thêm thành công",Toast.LENGTH_LONG).show();
                    loadData();
                }
                else{
                    Toast.makeText(context, "Thêm thất bại",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void editKindProduct(){

        KindProduct KindProduct=new KindProduct();
        int STT=Integer.parseInt(txtSTT.getText().toString());
        String CodeKind=txtCodeKind.getText().toString();
        String NameKind=txtNameKind.getText().toString();
        KindProduct.setStt(STT);
        KindProduct.setCodeKind(CodeKind);
        KindProduct.setNameKind(NameKind);
        boolean isCheck=database.updateKind(KindProduct);
        if(isCheck==true){
            Toast.makeText(context, "Sửa thành công",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(context, "Sửa thất bại",Toast.LENGTH_LONG).show();
        }
        loadData();
    }// end editKindProduct

    public void deleteKindProduct(){

        int STT=Integer.valueOf(txtSTT.getText().toString());
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
        alertDialog.setTitle("Xác nhận xóa");
        alertDialog.setMessage("Bạn chắc chắn muốn xóa loại có ID= "+STT);
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                database.Delete(Integer.valueOf(txtSTT.getText().toString()));
                Toast.makeText(context, "Xóa thành công",Toast.LENGTH_LONG).show();
                loadData();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        alertDialog.show();
    }

}
