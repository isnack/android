package com.example.desenvolvimento.challengemarvel.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Desenvolvimento on 04/09/2017.
 */

public class ImageLoadService extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
// constructor set Imageview
    public ImageLoadService(ImageView bmImage) {
        this.bmImage = bmImage;
    }
// Function run in background
protected Bitmap doInBackground(String... urls) {
    String urldisplay = urls[0];
    Bitmap mIcon11 = null;
    try {
        InputStream in = new java.net.URL(urldisplay).openStream();
        mIcon11 = BitmapFactory.decodeStream(in);
    } catch (Exception e) {
        Log.e("Error", e.getMessage());
        e.printStackTrace();
    }
    return mIcon11;
}

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
