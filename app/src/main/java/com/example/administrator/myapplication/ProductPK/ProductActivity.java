package com.example.administrator.myapplication.ProductPK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.administrator.myapplication.Adapter.ProductBaseAdapter;
import com.example.administrator.myapplication.Database.SQLDatabase;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Adapter.ProductBaseAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Database.SQLDatabase;

import java.util.ArrayList;

public class ProductActivity extends Activity {
    private final String CodeProduct = "MSP0";
    SQLDatabase database;
    ArrayList<Product> arrayProduct;
    Context context = this;
    ListView listProduct;
    Product product;
    ProductBaseAdapter adapter;
    ArrayList<String> arrayKindProduct;
    ArrayAdapter<String> adapterKindProduct;
    Spinner spinerKind;
    EditText txtID;
    EditText txtCodeProduct;
    EditText txtNameProduct;
    EditText txtPriceProduct;
    Button btnRefreshP;
    Button btnAddP;
    Button btnEditP;
    Button btnDeleteP;
    String[] arr;

    public ProductActivity() {
    }

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_product);
        this.addControl();
        this.createData();
        this.ShowDataListView();
        this.loadData();
        this.EvenButton();
        this.EvenListView();
    }

    @SuppressLint("ResourceType")
    public void addControl() {
        this.listProduct = (ListView)this.findViewById(R.id.ListProduct);
        this.txtID = (EditText)this.findViewById(R.id.txtID);
        this.txtCodeProduct = (EditText)this.findViewById(R.id.txtCodeProduct);
        this.txtNameProduct = (EditText)this.findViewById(R.id.txtNameProduct);
        this.txtPriceProduct = (EditText)this.findViewById(R.id.txtPriceProduct);
        this.spinerKind = (Spinner)this.findViewById(R.id.SpinnerKind);
        this.btnRefreshP = (Button)this.findViewById(R.id.btnRefreshP);
        this.btnAddP = (Button)this.findViewById(R.id.btnAddP);
        this.btnEditP = (Button)this.findViewById(R.id.btnEditP);
        this.btnDeleteP = (Button)this.findViewById(R.id.btnDeleteP);
        this.spinerKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String STTKIND=spinerKind.getItemAtPosition(position).toString();
                arr= STTKIND.split(" ");
            }


            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createData() {
        this.database = new SQLDatabase(this.context);
        this.database.queryData("CREATE TABLE IF NOT EXISTS PRODUCT(ID INTEGER PRIMARY KEY AUTOINCREMENT, CODEPRODUCT VARCHAR(8) NOT NULL, NAMEPRODUCT TEXT NOT NULL, PRICEPRODUCT INTEGER NOT NULL, STTKIND INTEGER NOT NULL)");
    }

    @SuppressLint("ResourceType")
    public void ShowDataListView() {
        arrayProduct = new ArrayList();
        arrayKindProduct = this.database.getAllData();
        adapterKindProduct = new ArrayAdapter(this.context, android.R.layout.simple_spinner_item,arrayKindProduct);
        adapterKindProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter = new ProductBaseAdapter(ProductActivity.this, R.layout.custom_list_product, this.arrayProduct);
        listProduct.setAdapter((ListAdapter) this.adapter);
       spinerKind.setAdapter(this.adapterKindProduct);
    }

    public void loadData() {
        this.arrayProduct.clear();
        Cursor data = this.database.getData("SELECT * FROM PRODUCT JOIN KINDPRODUCT ON PRODUCT.STTKIND=KINDPRODUCT.STT");

        while(data.moveToNext()) {
            int Id = data.getInt(0);
            String codeProduct = data.getString(1);
            String nameProduct = data.getString(2);
            int priceProduct = Integer.parseInt(data.getString(3));
            String NameKind = data.getString(7);
            this.arrayProduct.add(new Product(Id, codeProduct, nameProduct, priceProduct, NameKind));
        }

        adapterKindProduct.notifyDataSetChanged();
        this.defaultData();
    }

    public void defaultData() {
        String number = String.valueOf(this.arrayProduct.size() + 1);
        String NumberSTT;
        if (Integer.parseInt(number) == 1) {
            NumberSTT = "1";
        } else {
            NumberSTT = String.valueOf(((Product)this.arrayProduct.get(this.arrayProduct.size() - 1)).getID() + 1);
        }

        this.txtID.setText(NumberSTT);
        this.txtID.setEnabled(false);
        this.txtCodeProduct.setText("MSP0" + number);
        this.txtCodeProduct.setEnabled(false);
        this.txtNameProduct.setText("");
        this.txtPriceProduct.setText("");
        this.spinerKind.setSelection(0);
    }

    public void EvenButton() {
        this.btnRefreshP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductActivity.this.defaultData();
            }
        });
        this.btnAddP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductActivity.this.addProduct();
            }
        });
        this.btnEditP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductActivity.this.editProduct();
            }
        });
        this.btnDeleteP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductActivity.this.deleteProduct();
            }
        });
    }

    public void EvenListView() {
        this.listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ID = ((Product)ProductActivity.this.arrayProduct.get(position)).getID();
                String CodeProduct = ((Product)ProductActivity.this.arrayProduct.get(position)).getCodeProduct();
                String NameProduct = ((Product)ProductActivity.this.arrayProduct.get(position)).getNameProduct();
                int PriceProduct = ((Product)ProductActivity.this.arrayProduct.get(position)).getPriceProduct();
                String KindProduct = ((Product)ProductActivity.this.arrayProduct.get(position)).getKindProduct();
                ProductActivity.this.txtID.setText(String.valueOf(ID));
                ProductActivity.this.txtCodeProduct.setText(CodeProduct);
                ProductActivity.this.txtNameProduct.setText(NameProduct);
                ProductActivity.this.txtPriceProduct.setText(String.valueOf(PriceProduct));
                Cursor data = ProductActivity.this.database.getData("SELECT * FROM PRODUCT WHERE ID='" + ID + "'");

                while(data.moveToNext()) {
                    int STT = Integer.parseInt(data.getString(4));
                    ProductActivity.this.spinerKind.setSelection(STT - 1);
                }

                ProductActivity.this.txtCodeProduct.setEnabled(true);
            }
        });
    }

    @SuppressLint("WrongConstant")
    public void addProduct() {
        String CodeProduct = this.txtCodeProduct.getText().toString();
        String NameProduct = this.txtNameProduct.getText().toString();
        int PriceProduct = Integer.parseInt(this.txtPriceProduct.getText().toString());
        Product product = new Product(CodeProduct, NameProduct, PriceProduct, this.arr[0]);
        if (this.isCheckCodeProduct(CodeProduct)) {
            Toast.makeText(this.context, "Mã sản phẩm bị trùng", Toast.LENGTH_LONG).show();
        } else if (NameProduct.equals("")) {
            Toast.makeText(this.context, "Xin vui lòng nhập tên sản phẩm", Toast.LENGTH_LONG).show();
        } else {
            boolean isCheck = this.database.InsertProduct(product);
            if (isCheck) {
                Toast.makeText(this.context, "Thêm thành công", Toast.LENGTH_LONG).show();
                this.loadData();
            } else {
                Toast.makeText(this.context, "Thêm thất bại", Toast.LENGTH_LONG).show();
            }
        }

    }

    public boolean isCheckCodeProduct(String CodeProduct) {
        for(Product KP:arrayProduct)
        {
            if(KP.getCodeProduct().equals(CodeProduct))
            {
                return true;
            }
        }
        return false;

    }

    @SuppressLint("WrongConstant")
    public void editProduct() {
        int ID = Integer.parseInt(this.txtID.getText().toString());
        String CodeProduct = this.txtCodeProduct.getText().toString();
        String NameProduct = this.txtNameProduct.getText().toString();
        int PriceProduct = Integer.parseInt(this.txtPriceProduct.getText().toString());
        Product product = new Product(ID, CodeProduct, NameProduct, PriceProduct, this.arr[0]);
        boolean isCheck = this.database.updateProduct(product);
        if (isCheck) {
            Toast.makeText(this.context, "Sửa thành công", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.context, "Sửa thất bại", Toast.LENGTH_LONG).show();
        }

        this.loadData();
    }

    public void deleteProduct() {
        int ID = Integer.valueOf(this.txtID.getText().toString());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.context);
        alertDialog.setTitle("Xác nhận xóa");
        alertDialog.setMessage("Bạn chắc chắn muốn xóa loại có ID= " + ID);
        alertDialog.setPositiveButton("Có", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int ID = Integer.valueOf(ProductActivity.this.txtID.getText().toString());
                ProductActivity.this.database.queryData("DELETE FROM PRODUCT WHERE ID=" + ID);
                Toast.makeText(ProductActivity.this.context, "Xóa thành công",Toast.LENGTH_LONG).show();
                ProductActivity.this.loadData();
            }
        });
        alertDialog.setNegativeButton("Không", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }
}
