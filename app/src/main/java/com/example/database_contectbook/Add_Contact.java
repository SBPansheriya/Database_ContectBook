package com.example.database_contectbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Add_Contact extends AppCompatActivity {

    Data_Base data_base;
    Button btnadd,btnupdate;
    EditText newname,newnumber;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contect);
        data_base = new Data_Base(Add_Contact.this);
        btnadd = findViewById(R.id.btnadd);
        btnupdate = findViewById(R.id.btnupdate);
        newname = findViewById(R.id.newname);
        newnumber = findViewById(R.id.newnumber);

        String btn = getIntent().getStringExtra("button");

        if(btn.equals("add")){
            btnadd.setVisibility(View.VISIBLE);
        }
        else if (btn.equals("update")){
            btnupdate.setVisibility(View.VISIBLE);
        }

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_base.addContact(newname.getText().toString(),newnumber.getText().toString());
                Intent intent = new Intent(Add_Contact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        int position = getIntent().getIntExtra("pos",0);

        String namelist = getIntent().getStringExtra("name");
        String numberlist = getIntent().getStringExtra("number");
        Log.d("TTT", "onCreate: namelist"+namelist);

        newname.setText(""+namelist);
        newnumber.setText(""+numberlist);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_base.updateContact((position+1),newname.getText().toString(),newnumber.getText().toString());
                Intent intent = new Intent(Add_Contact.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
