package com.example.administrator.myapplication.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.administrator.myapplication.Manager;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Manager;
import com.example.administrator.myapplication.R;
import java.net.URISyntaxException;

public class LoginActivity extends Activity {

    TextView txt1;
    EditText editname, editpass;
    Button btnlogin, btncacel;
    ImageView imageView;
    int counter = 3;



    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControl();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editname.getText().toString().equals("admin") &&
                        editpass.getText().toString().equals("123")) {
                    Toast.makeText(LoginActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Manager.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();

                    txt1.setVisibility(View.VISIBLE);
                    txt1.setBackgroundColor(Color.RED);
                    counter--;
                    txt1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        btnlogin.setEnabled(false);
                    }
                }
            }
        });
        btncacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void addControl() {
        txt1 = (TextView) findViewById(R.id.textview);
        editname = (EditText) findViewById(R.id.editname);
        editpass = (EditText) findViewById(R.id.editpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btncacel = (Button) findViewById(R.id.btncancel);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    private void evenClick() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(LoginActivity.this, Manager.class);
                startActivity(intent);
            }
        });
    }

}