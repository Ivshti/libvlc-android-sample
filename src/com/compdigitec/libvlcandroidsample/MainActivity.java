package com.compdigitec.libvlcandroidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.File;

public class MainActivity extends Activity {
    public final static String TAG = "LibVLCAndroidSample/MainActivity";

    DirectoryAdapter mAdapter;
    LibVLC mLibVLC = null;
    MediaPlayer mMediaPlayer = null;

    boolean mPlayingVideo = false; // Don't destroy libVLC if the video activity is playing.

    /**
     * Demonstrates how to play a certain media at a given path.
     * TODO: demonstrate other LibVLC features like media lists, etc.
     */
    private void playMediaAtPath(String path) {
        // To play with LibVLC, we need a media player object.
        // Let's get one, if needed.
        if(mMediaPlayer == null)
            mMediaPlayer = new MediaPlayer(mLibVLC);

        // Create a new Media object for the file.
        // Each media - a song, video, or stream is represented by a Media object for LibVLC.
        Media m = new Media(mLibVLC, path);

        // Tell the media player to play the new Media.
        mMediaPlayer.setMedia(m);

        // Finally, play it!
        mMediaPlayer.play();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the LibVLC multimedia framework.
        // This is required before doing anything with LibVLC.
        try {
            mLibVLC = new LibVLC();
        } catch(IllegalStateException e) {
            Toast.makeText(MainActivity.this,
                    "Error initializing the libVLC multimedia framework!",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        //intent.putExtra(VideoActivity.LOCATION, "http://dl.strem.io/BigBuckBunny_512kb.mp4");
        intent.putExtra(VideoActivity.LOCATION, "http://dl.strem.io/bbb_sunflower_1080p_30fps_normal.mp4");
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.action_settings:
            Log.d(TAG, "Setting item selected.");
            return true;
        case R.id.action_refresh:
            mAdapter.refresh();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
