package com.example.simpleimageimprover.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simpleimageimprover.R;
import com.example.simpleimageimprover.utilities.HelperClass;
import com.example.simpleimageimprover.Adapter.RecyclerAdapter;
import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements RecyclerAdapter.OnClickListener {
    @BindView(R.id.image_real)
    ImageView realImage;
    @BindView(R.id.image_modified)
    ImageView newImage;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle("Edit Image");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        String[] titles = {"BrightenFilter","BlueMessFilter","NightFilter","StarLitFilter","AwestruckFilter","LimeStutterFilter"};
        RecyclerAdapter adapter = new RecyclerAdapter(titles, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        List<String> uriArr = new ArrayList<>();
        Uri uri = Uri.parse(getIntent().getStringExtra(HelperClass.REAL_URI));
        realImage.setImageURI(uri);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void selectItem(int position) {
        Filter filter = new Filter();;

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
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        Bitmap outputImage = filter.processFilter(image);
        newImage.setImageBitmap(outputImage);
    }
}