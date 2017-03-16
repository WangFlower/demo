package com.example.momo.myapplication.media;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.momo.myapplication.R;

import static android.provider.MediaStore.MediaColumns.MIME_TYPE;

/**
 * Created by MOMO on 17/2/10.
 */

public class ScanMediaActivity extends Activity {
    static final String[] MEDIA_PROJECTION = {
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.DATE_ADDED,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.HEIGHT,
            MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.SIZE,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,

            MediaStore.Video.Media.DURATION,
            //            MediaStore.Video.Media.BUCKET_ID,
            //            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path);
        View view = findViewById(R.id.rt);
        view.setVisibility(View.VISIBLE);
//        this.getLoaderManager().initLoader(1212, null, new VideoDirLoaderCallbacks(this));
        //        getRecentlyPhotoId(this);
    }

    public static String getRecentlyPhotoId(Context context) {
        String searchPath = MediaStore.Files.FileColumns.DATA + " LIKE '%" + "/DCIM/Camera/" +
                "%' ";
        Uri uri = MediaStore.Files.getContentUri("external");
        Cursor cursor = context.getContentResolver().query(
                uri, new String[]{MediaStore.Files.FileColumns.DATA}, searchPath, null, MediaStore
                        .Files.FileColumns.DATE_ADDED + " DESC");
        String filePath = "";
        if (cursor == null) {
            Log.i("wangrenguang", "null");
        }
        while (cursor.moveToNext()) {
            //看这里我们取id了
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
            Log.i("wangrenguang", filePath);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return filePath;
    }

    static public class PhotoDirectoryLoader extends CursorLoader {

        public PhotoDirectoryLoader(Context context) {
            super(context);
            setProjection(ScanMediaActivity.MEDIA_PROJECTION);
            setUri(MediaStore.Files.getContentUri("external"));
            setSortOrder(MediaStore.Images.Media.DATE_ADDED+ " DESC LIMIT " + 10);
            setSelectionAndArgs();
        }

        private void setSelectionAndArgs() {
            setSelection(MIME_TYPE + "=? or " +
                    MIME_TYPE + "=? or " + MIME_TYPE + "=? or " + MIME_TYPE + "=? or " +
                            MIME_TYPE + "=? ");
            String[] selectionArgs;
            selectionArgs = new String[]{"video/mp4","image/jpg", "image/jpeg", "image/png",
                    "image/webp"};
            setSelectionArgs(selectionArgs);
        }
    }


    static class VideoDirLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
        private Context context;

        public VideoDirLoaderCallbacks(Context context) {
            this.context = context;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new PhotoDirectoryLoader(context);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data == null)
                return;
            while (data.moveToNext()) {
                int id = data.getInt(data.getColumnIndexOrThrow(MediaStore.MediaColumns._ID));
                String path = data.getString(data.getColumnIndexOrThrow(MediaStore.MediaColumns
                        .DATA));
                String mimeType = data.getString(data.getColumnIndexOrThrow(MediaStore.Images
                        .ImageColumns.MIME_TYPE));


                //                int width = data.getInt(data.getColumnIndexOrThrow(MediaStore
                // .Video.Media.WIDTH));
                //                int height = data.getInt(data.getColumnIndexOrThrow(MediaStore
                // .Video.Media.HEIGHT));
                //                String bucketId = data.getString(data.getColumnIndexOrThrow
                // (MediaStore.Video.VideoColumns.BUCKET_ID));
                //                String name = data.getString(data.getColumnIndexOrThrow
                // (MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME));


                Log.i("wangrenguang", "" + id);
                Log.i("wangrenguang", "" + path);
                Log.i("wangrenguang", "mimeType" + mimeType);

//                if (mimeType.equals("video/mp4")) {
//                    int duration = data.getInt(data.getColumnIndexOrThrow
//                            (MediaStore.Video.Media.DURATION));
//                    Log.i("wangrenguang", "mimeType" + duration);
//                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }

}
