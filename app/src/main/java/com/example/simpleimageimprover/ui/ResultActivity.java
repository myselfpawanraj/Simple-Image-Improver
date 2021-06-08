package com.example.simpleimageimprover.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simpleimageimprover.R;
import com.example.simpleimageimprover.utilities.HelperClass;
import com.example.simpleimageimprover.Adapter.RecyclerAdapter;
import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;

import org.wysaid.myUtils.ImageUtil;
import org.wysaid.nativePort.CGENativeLibrary;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class ResultActivity extends AppCompatActivity implements RecyclerAdapter.OnClickListener {
    @BindView(R.id.image_real)
    ImageView realImage;
    @BindView(R.id.image_modified)
    ImageView newImage;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.button_save)
    Button saveButton;

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    Bitmap outputImage = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle("Edit Image");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        String[] titles = {"BrightenFilter", "BlueMessFilter", "NightFilter", "StarLitFilter", "AwestruckFilter", "LimeStutterFilter"};
        RecyclerAdapter adapter = new RecyclerAdapter(titles, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List< String > uriArr = new ArrayList<>();
        Uri uri = Uri.parse(getIntent().getStringExtra(HelperClass.REAL_URI));
        realImage.setImageURI(uri);

        saveButton.setOnClickListener(v -> {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/SampleEditor");
            myDir.mkdirs();
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            String fName = "Image-" + n + ".jpg";
            File file = new File(myDir, fName);
            Log.i(TAG, "" + file);
            if (file.exists())
                file.delete();
            Log.i(TAG, "startttttt");
            try {
                FileOutputStream out = new FileOutputStream(file);
                outputImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
                Log.i(TAG, "enddddd");
                Toast.makeText(this, "Saved! " + file.getPath(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void selectItem(int position) {
        Filter filter = new Filter();

        switch (position) {
            case 0:
                filter.addSubFilter(new BrightnessSubFilter(30));
                filter.addSubFilter(new ContrastSubFilter(1.1f));
                break;
            case 1:
                filter = SampleFilters.getBlueMessFilter();
                break;
            case 2:
                filter = SampleFilters.getNightWhisperFilter();
                break;
            case 3:
                filter = SampleFilters.getStarLitFilter();
                break;
            case 4:
                filter = SampleFilters.getAweStruckVibeFilter();
                break;
            case 5:
                filter = SampleFilters.getLimeStutterFilter();
                break;
        }

        BitmapDrawable drawable = (BitmapDrawable) realImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        outputImage = filter.processFilter(image);
        newImage.setImageBitmap(outputImage);
        saveButton.setVisibility(View.VISIBLE);
    }
}