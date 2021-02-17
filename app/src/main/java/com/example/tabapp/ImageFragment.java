package com.example.tabapp;

import android.Manifest;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment {
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    Context context;
    private ArrayList<DataModel> images = new ArrayList<>();
    TextView gallery_number;
    //  private static final String file_1 = "file_1";
    //  private static final String type_1 = "type_1";


    private static final int MY_READ_PERMISSION_CODE = 101;

    public ImageFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        context = getContext();
        images = DataGallery.listOfImages(getContext());
        Log.e("size of images", String.valueOf(images.size()));
        gallery_number = v.findViewById(R.id.gallery_number_image);
        recyclerView = v.findViewById(R.id.recyclerview_images);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_PERMISSION_CODE);
        } else {
            loadImages();
        }
        return v;


    }

    private void loadImages() {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        images = DataGallery.listOfImages(context);
        Log.e("Images array size", String.valueOf(images.size()));
        imageAdapter = new ImageAdapter(getContext(), images, new ImageAdapter.PhotoListener() {
            @Override
            public void onPhotoClick(DataModel path) {
                Toast.makeText(getContext(), "" + path.getFile().getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(imageAdapter);
        gallery_number.setText("photos(" + images.size() + ")");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_READ_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Read External Storage Permission Granted", Toast.LENGTH_SHORT).show();
                loadImages();
            } else {
                Toast.makeText(getContext(), "Read External Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
