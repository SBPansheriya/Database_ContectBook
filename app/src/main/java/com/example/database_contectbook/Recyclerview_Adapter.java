package com.example.database_contectbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.RecyclerviewHolder> {

    Context context;
    ArrayList<Contact_Modal> contactlist;

    public Recyclerview_Adapter(Context context, ArrayList<Contact_Modal> contactlist) {
        this.context = context;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public Recyclerview_Adapter.RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        RecyclerviewHolder holder = new RecyclerviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, @SuppressLint("RecyclerView") int position){
            holder.name.setText("" + contactlist.get(position).getName());
            holder.number.setText("" + contactlist.get(position).getNumber());
            loadImageFromStorage(contactlist.get(position).getImgpath(),holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Add_Contact.class);
                    intent.putExtra("button","update");
                    intent.putExtra("id",contactlist.get(position).getId());
                    intent.putExtra("name",contactlist.get(position).getName());
                    intent.putExtra("number",contactlist.get(position).getNumber());
                    intent.putExtra("image",contactlist.get(position).getImgpath());
                    context.startActivity(intent);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Data_Base data_base = new Data_Base(context);
                    PopupMenu popupMenu = new PopupMenu(context,v);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                        data_base.deleteContact(contactlist.get(position).getId());
                        contactlist.remove(position);
                        notifyDataSetChanged();
                        return false;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder {
        TextView name,number;
        ImageView imageView;
        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            imageView = itemView.findViewById(R.id.main_image);
        }
    }

    private void loadImageFromStorage(String path,ImageView imageView)
    {

        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}