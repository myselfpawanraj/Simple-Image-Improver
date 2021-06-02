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
import com.example.simpleimageimprover.utilities.RecyclerAdapter;
import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements RecyclerAdapter.OnClickListener {
    @BindView(R.id.image_real)
    ImageView realImage;
    @BindView(R.id.image_modified)
    ImageView newImage;
    @BindView((R.id.recyclerview))
    RecyclerView recyclerView;

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    Uri realUri = null, modifiedUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ButterKnife.bind(this);
        String titles[] = {"BrightenFilter","BlueMessFilter","NightFilter","StarLitFilter","AwestruckFilter","LimeStutterFilter"};
        RecyclerAdapter adapter = new RecyclerAdapter(titles, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        List<String> uriArr = new ArrayList<>();
        Uri uri = Uri.parse(getIntent().getStringExtra(HelperClass.REAL_URI));
        realImage.setImageURI(uri);




    }

    Toast mtoast;

    @Override
    public void selectItem(int position) {
//        if(mtoast != null) {
//            mtoast.cancel();
//        }
//        String text = "Hello Hello" + position;
//        mtoast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
//        mtoast.show();

        if(position == 0) {
            Filter myFilter = new Filter();
            myFilter.addSubFilter(new BrightnessSubFilter(30));
            myFilter.addSubFilter(new ContrastSubFilter(1.1f));
            BitmapDrawable drawable = (BitmapDrawable) realImage.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);
            Bitmap outputImage = myFilter.processFilter(image);
            newImage.setImageBitmap(outputImage);
        }
        else if(position == 1) {
            Filter fooFilter = SampleFilters.getBlueMessFilter();
            BitmapDrawable drawable2 = (BitmapDrawable) realImage.getDrawable();
            Bitmap bitmap2 = drawable2.getBitmap();
            Bitmap image2 = bitmap2.copy(Bitmap.Config.ARGB_8888,true);
            Bitmap outputImage2 = fooFilter.processFilter(image2);
            newImage.setImageBitmap(outputImage2);
        }
        else if(position == 2) {
            Filter fooFilter3 = SampleFilters.getNightWhisperFilter();
            BitmapDrawable drawable3 = (BitmapDrawable) realImage.getDrawable();
            Bitmap bitmap3 = drawable3.getBitmap();
            Bitmap image3 = bitmap3.copy(Bitmap.Config.ARGB_8888,true);
            Bitmap outputImage3 = fooFilter3.processFilter(image3);
            newImage.setImageBitmap(outputImage3);
        }
        else if(position == 3) {
            Filter fooFilter3 = SampleFilters.getStarLitFilter();
            BitmapDrawable drawable3 = (BitmapDrawable) realImage.getDrawable();
            Bitmap bitmap3 = drawable3.getBitmap();
            Bitmap image3 = bitmap3.copy(Bitmap.Config.ARGB_8888,true);
            Bitmap outputImage3 = fooFilter3.processFilter(image3);
            newImage.setImageBitmap(outputImage3);
        }
        else if(position == 4) {
            Filter fooFilter3 = SampleFilters.getAweStruckVibeFilter();
            BitmapDrawable drawable3 = (BitmapDrawable) realImage.getDrawable();
            Bitmap bitmap3 = drawable3.getBitmap();
            Bitmap image3 = bitmap3.copy(Bitmap.Config.ARGB_8888,true);
            Bitmap outputImage3 = fooFilter3.processFilter(image3);
            newImage.setImageBitmap(outputImage3);
        }
        else if(position == 5) {
            Filter fooFilter3 = SampleFilters.getLimeStutterFilter();
            BitmapDrawable drawable3 = (BitmapDrawable) realImage.getDrawable();
            Bitmap bitmap3 = drawable3.getBitmap();
            Bitmap image3 = bitmap3.copy(Bitmap.Config.ARGB_8888,true);
            Bitmap outputImage3 = fooFilter3.processFilter(image3);
            newImage.setImageBitmap(outputImage3);
        }
    }
}