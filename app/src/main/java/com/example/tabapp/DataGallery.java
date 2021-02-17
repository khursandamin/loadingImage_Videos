package com.example.tabapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class DataGallery {

    public static ArrayList<DataModel> listOfImages(Context context) {
        Uri uri;
        Cursor cursor;
        String type = "images";
        int column_index_data;
        ArrayList<DataModel> ListOfAllImages = new ArrayList<>();
        File absolutePathOfImage;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        String orderBy=MediaStore.Video.Media.DATE_TAKEN;
        cursor = context.getContentResolver().query(uri, projection, null, null, null);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        //column_index_folder_name=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = new File(cursor.getString(column_index_data));
            ListOfAllImages.add(new DataModel(absolutePathOfImage, type));
        }
        Log.e("List of all images", String.valueOf(ListOfAllImages.size()));
        return ListOfAllImages;
    }

    public static ArrayList<DataModel> listOfVideos(Context context) {
        Uri uri;
        Cursor cursor;
        String type = "videos";
        int column_index_data;
        ArrayList<DataModel> ListOfAllVideos = new ArrayList<>();
        File absolutePathOfVideo;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME};

        cursor = context.getContentResolver().query(uri, projection, null, null, null);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        //column_index_folder_name=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfVideo = new File(cursor.getString(column_index_data));
            ListOfAllVideos.add(new DataModel(absolutePathOfVideo, type));
        }
        return ListOfAllVideos;
    }

}

