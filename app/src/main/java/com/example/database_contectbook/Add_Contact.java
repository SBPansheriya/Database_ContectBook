package com.example.database_contectbook;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Add_Contact extends AppCompatActivity {

    Data_Base data_base;
    Button btnadd,btnupdate;
    EditText newname,newnumber;
    ImageView image;
    String btn;
    String imagename,imagepath;
    private static final int CAMERA_REQUEST = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contect);
        data_base = new Data_Base(Add_Contact.this);
        btnadd = findViewById(R.id.btnadd);
        btnupdate = findViewById(R.id.btnupdate);
        newname = findViewById(R.id.newname);
        newnumber = findViewById(R.id.newnumber);
        image = findViewById(R.id.imageview);
        int position = getIntent().getIntExtra("id",0);
        String namelist = getIntent().getStringExtra("name");
        String numberlist = getIntent().getStringExtra("number");
        String imagelist = getIntent().getStringExtra("image");
        Log.d("TTT", "onCreate: namelist"+namelist);

        if(namelist!=null){
            newname.setText(""+namelist);
            newnumber.setText(""+numberlist);
            btn = getIntent().getStringExtra("button");
        }
        btn = getIntent().getStringExtra("button");

        if(btn.equals("add")){
            btnadd.setVisibility(View.VISIBLE);
        }
        else if (btn.equals("update")){
            btnupdate.setVisibility(View.VISIBLE);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, CAMERA_REQUEST);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagepath = imagepath+"/"+imagename;
                Log.d("SSS", "onClick: new imgpath"+imagepath);
                data_base.addContact(newname.getText().toString(),newnumber.getText().toString(),imagepath);
                Intent intent = new Intent(Add_Contact.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagepath = imagepath+"/"+imagename;
                data_base.updateContact(position,newname.getText().toString(),newnumber.getText().toString(),imagepath);
                Intent intent = new Intent(Add_Contact.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    image.setImageBitmap(bitmap);
                    imagepath=saveToInternalStorage(bitmap);
                }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        imagename = "img"+new Random().nextInt(100000)+".png";
        File mypath=new File(directory,imagename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
