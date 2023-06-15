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

//        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(MainActivity.this, recyclerView);
//
//                // Inflating popup menu from popup_menu.xml file
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        // Toast message on menu item clicked
//                        Toast.makeText(MainActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//                // Showing the popup menu
//                popupMenu.show();
//                return true;
//            }
//        });
    }

    private void Displayeddata() {
        data_base = new Data_Base(MainActivity.this);
        Cursor cursor = data_base.Displayed();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            Contact_Modal contactModal = new Contact_Modal(id,name,number);
            contactlist.add(contactModal);
        }
        adapter = new Recyclerview_Adapter(MainActivity.this,contactlist,data_base);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}