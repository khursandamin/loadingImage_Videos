package com.example.tabapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context context;
    private List<DataModel> videos;
    protected VideoListener videoListener;

    public VideoAdapter(Context context, List<DataModel> videos, VideoListener videoListener) {
        this.context = context;
        this.videos = videos;
        this.videoListener = videoListener;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        final File file = videos.get(position).getFile();
        Glide.with(context).load(file.getAbsolutePath()).placeholder(R.mipmap.ic_launcher).into(holder.video1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoListener != null) {
                    videoListener.onVideoClick(videos.get(position));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e("Images sizeAdapter", String.valueOf(videos.size()));
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView video1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            video1 = itemView.findViewById(R.id.video1);

        }

    }

    public interface VideoListener {
        void onVideoClick(DataModel Path);
    }
}
