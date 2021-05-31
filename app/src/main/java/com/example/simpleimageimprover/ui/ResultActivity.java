package com.example.simpleimageimprover.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.simpleimageimprover.R;
import com.example.simpleimageimprover.utilities.HelperClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {
    @BindView(R.id.image_real)
    ImageView realImage;
    @BindView(R.id.image_modified)
    ImageView newImage;

    Uri realUri = null, modifiedUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ButterKnife.bind(this);

        List<String> uriArr = new ArrayList<>();

        uriArr.add(getIntent().getStringExtra(HelperClass.REAL_URI));
        uriArr.add(getIntent().getStringExtra(HelperClass.MODIFIED_URI));

        if(uriArr.get(0) != null) {
            realUri = Uri.parse(uriArr.get(0));
            realImage.setImageURI(null);
            realImage.setImageURI(realUri);
        }

        if(uriArr.get(1) != null) {
            modifiedUri = Uri.parse(uriArr.get(1));
            newImage.setImageURI(null);
            newImage.setImageURI(modifiedUri);
        }
    }
}