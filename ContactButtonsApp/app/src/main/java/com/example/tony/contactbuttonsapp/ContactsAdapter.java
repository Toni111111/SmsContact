package com.example.tony.contactbuttonsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tony on 30.11.2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    // fixme: эти поля могут быть финальными
    private ContactsFragment fragment;
    private ArrayList<ContactModel> contacts;



    public ContactsAdapter(ContactsFragment fragment, ArrayList<ContactModel> contacts) {
        this.fragment = fragment;
        this.contacts = contacts;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, final int position) {

        holder.nameTv.setText(contacts.get(position).getName());
        holder.phoneNumberTv.setText(contacts.get(position).getPhoneNumber());




        holder.llContactItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.replaceOnSettingsFragment(contacts.get(position));
            }
        });


    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // fixme: класс и его члены можно сделать package-local, поля — ещё и финальными
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTv;
        public TextView phoneNumberTv;
        private LinearLayout llContactItem;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name_item_tv);
            phoneNumberTv = itemView.findViewById(R.id.phone_number_item_tv);
            llContactItem = itemView.findViewById(R.id.ll_contact_item);
        }
    }
}
