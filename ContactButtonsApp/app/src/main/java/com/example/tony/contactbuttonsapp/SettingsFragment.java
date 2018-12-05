package com.example.tony.contactbuttonsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.Set;


public class SettingsFragment extends Fragment {
    String name;
    String phoneNumber;

    private LinearLayout smsContent;
    private RadioButton smsBtn;
    private RadioButton callBtn;
    private EditText textMessage;
    private MainModel model;
    private Button createBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        smsBtn = view.findViewById(R.id.smsBtn);
        callBtn = view.findViewById(R.id.callBtn);
        smsContent = view.findViewById(R.id.sms_content);
        createBtn = view.findViewById(R.id.createBtn);
        textMessage = view.findViewById(R.id.textMessage);

        phoneNumber = getArguments().getString("phoneNumber");
        name = getArguments().getString("name");



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsContent.setVisibility(View.VISIBLE);
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsContent.setVisibility(View.GONE);
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model = new MainModel();
                model.setName(name);
                model.setPhone(phoneNumber);
                if(callBtn.isChecked()){
                    model.setType("call");
                }else{
                    model.setType("sms");
                }
                if(textMessage.getText().toString().equals("")){

                }else{
                    model.setText(textMessage.getText().toString());
                }


                ((MainActivity) getActivity()).setData(model);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.remove(SettingsFragment.this).commit();
            }
        });
    }
}
