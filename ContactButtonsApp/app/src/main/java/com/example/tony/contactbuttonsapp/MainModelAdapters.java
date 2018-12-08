package com.example.tony.contactbuttonsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainModelAdapters extends RecyclerView.Adapter<MainModelAdapters.ViewHolder> {


    // fixme: эти поля могут быть финальными
    private MainActivity activity;
    private ArrayList<MainModel> contacts;



    public MainModelAdapters(MainActivity activity, ArrayList<MainModel> contacts) {
        this.activity = activity;
        this.contacts = contacts;
    }

    @Override
    public MainModelAdapters.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_item,parent,false);
        return new MainModelAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainModelAdapters.ViewHolder holder, final int position) {
        holder.typeTv.setText(contacts.get(position).getType());
        holder.nameTv.setText(contacts.get(position).getName());
        holder.phoneNumberTv.setText(contacts.get(position).getPhone());




        if(contacts.get(position).getType().equals("sms")){
            holder.llcontact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contacts.get(position).getPhone()));
                   // intent.putExtra("sms_body", contacts.get(position).getText());
                   // activity.startActivity(intent);
                    activity.sendSmsByManager(contacts.get(position).getPhone(),contacts.get(position).getText());
                }
            });
        }else{
            holder.llcontact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacts.get(position).getPhone()));
                    activity.startActivity(intent);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTv;
        public TextView phoneNumberTv;
        private TextView typeTv;
        private LinearLayout llcontact;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name_item_button);
            phoneNumberTv = itemView.findViewById(R.id.phone_number_button);
            typeTv = itemView.findViewById(R.id.type_button_item);
            llcontact = itemView.findViewById(R.id.ll_contact_item_button);
        }
    }
}

