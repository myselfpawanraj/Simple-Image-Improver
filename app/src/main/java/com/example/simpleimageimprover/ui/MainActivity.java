package com.example.simpleimageimprover.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simpleimageimprover.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button_insert)
    Button insertButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        insertButton.setOnClickListener(v -> {
            takeImage();
        });
    }

    private void takeImage() {
    }
}