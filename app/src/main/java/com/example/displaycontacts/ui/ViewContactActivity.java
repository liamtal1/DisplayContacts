package com.example.displaycontacts.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.displaycontacts.R;
import com.example.displaycontacts.databinding.ActivityViewContactBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ViewContactActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityViewContactBinding binding = ActivityViewContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME);
        String phoneNumber = intent.getStringExtra(PHONE_NUMBER);
        binding.setName(getString(R.string.name_line, name));
        binding.setPhoneNumber(phoneNumber);

        TextView phoneNumberTextView = findViewById(R.id.phoneNumber);
        phoneNumberTextView.setText(phoneNumber);
    }
}