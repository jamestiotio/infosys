package com.jamestiotio.comicscraper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class GetComic extends AsyncTask<String, String, Bitmap> {
    private final WeakReference<Activity> activityReference;

    @SuppressWarnings("deprecation")
    public GetComic(Activity context) {
        this.activityReference = new WeakReference<>(context);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        MainActivity activity = (MainActivity) activityReference.get();
        if (activity == null || activity.isFinishing()) return null;

        String comicNo = strings[0];
        Bitmap bitmapDownloaded = null;

        try {
            String imageURL = Utils.getImageURLFromXkcdApi(comicNo);
            publishProgress(imageURL); // --> onProgressUpdate
            URL url = new URL(imageURL);
            bitmapDownloaded = Utils.getBitmap(url);
        }
        catch (FileNotFoundException ex) {
            publishProgress(activity.getResources().getString(R.string.error_not_valid));
        }
        catch (MalformedURLException ex) {
            publishProgress(activity.getResources().getString(R.string.error_malformed_url));
        }
        catch (IOException ex) {
            publishProgress(activity.getResources().getString(R.string.error_https_error));
        }
        catch (JSONException ex) {
            publishProgress(activity.getResources().getString(R.string.error_bad_json));
        }

        return bitmapDownloaded;
    }

    // Runs on UI
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        String message = values[0];

        MainActivity activity = (MainActivity) activityReference.get();
        if (activity == null || activity.isFinishing()) return;

        TextView textViewTitle = activity.findViewById(R.id.textViewTitle);
        textViewTitle.setText(message);
    }

    // Runs on UI
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        MainActivity activity = (MainActivity) activityReference.get();
        if (activity == null || activity.isFinishing()) return;

        ImageView imageViewComic = activity.findViewById(R.id.imageViewComic);
        imageViewComic.setImageBitmap(bitmap);
    }
}