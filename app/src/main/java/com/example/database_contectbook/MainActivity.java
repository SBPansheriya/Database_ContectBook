package com.example.database_contectbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView add;
    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    Data_Base data_base;
    Button btnadd;
    ArrayList<Contact_Modal> contactlist = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add_contact);
        btnadd = findViewById(R.id.btnadd);
        recyclerView = findViewById(R.id.recyclerview);

        Displayeddata();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_Contact.class);
                intent.putExtra("button", "add");
                startActivity(intent);
            }
        });

    }

    private void Displayeddata() {
        data_base = new Data_Base(MainActivity.this);
        Cursor cursor = data_base.Displayed();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            String imagepath = cursor.getString(3);
            Contact_Modal contactModal = new Contact_Modal(id,name,number,imagepath);
            contactlist.add(contactModal);
        }
        adapter = new Recyclerview_Adapter(MainActivity.this,contactlist);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}