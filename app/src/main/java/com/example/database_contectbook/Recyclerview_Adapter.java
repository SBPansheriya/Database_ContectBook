package com.example.database_contectbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.RecyclerviewHolder> {

    Context context;
    ArrayList<Contact_Modal> contactlist;
    Data_Base data_base;

    public Recyclerview_Adapter(Context context, ArrayList<Contact_Modal> contactlist, Data_Base data_base) {
        this.context = context;
        this.contactlist = contactlist;
        this.data_base = data_base;
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


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Add_Contact.class);
                    intent.putExtra("button","update");
                    intent.putExtra("pos",position);
                    intent.putExtra("name",contactlist.get(position).getName());
                    intent.putExtra("number",contactlist.get(position).getNumber());
                    context.startActivity(intent);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
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
        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }
}