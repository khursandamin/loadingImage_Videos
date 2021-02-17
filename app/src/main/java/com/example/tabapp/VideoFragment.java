package com.example.tabapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    Context context;
    TextView gallery_number_1;
    private ArrayList<DataModel> videos = new ArrayList<>();
    private static final int MY_READ_PERMISSION_CODE = 101;


    public VideoFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        context = getContext();
        videos = DataGallery.listOfVideos(getContext());
        Log.e("size of videos", String.valueOf(videos.size()));
        gallery_number_1 = v.findViewById(R.id.gallery_number_video);
        recyclerView = v.findViewById(R.id.recyclerview_videos);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_PERMISSION_CODE);
        } else {
            loadVideos();
        }
        return v;
    }

    private void loadVideos() {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        videos = DataGallery.listOfVideos(context);
        Log.e("videos array size", String.valueOf(videos.size()));
        videoAdapter = new VideoAdapter(getContext(), videos, new VideoAdapter.VideoListener() {

            @Override
            public void onVideoClick(DataModel path) {
                Toast.makeText(getContext(), "" + path.getFile().getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(videoAdapter);
        gallery_number_1.setText("videos(" + videos.size() + ")");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_READ_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Read External Storage Permission Granted", Toast.LENGTH_SHORT).show();
                loadVideos();
            } else {
                Toast.makeText(getContext(), "Read External Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
