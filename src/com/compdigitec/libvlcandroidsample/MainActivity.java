package com.compdigitec.libvlcandroidsample;

import java.io.File;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.LibVlcException;
import org.videolan.libvlc.MediaList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    public final static String TAG = "LibVLCAndroidSample/MainActivity";

    DirectoryAdapter mAdapter;
    LibVLC mLibVLC = null;

    View.OnClickListener mSimpleListener = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // Build the path to the media file
            String amp3 = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/a.mp3";
            if(!new File(amp3).exists()) {
                Toast.makeText(
                        MainActivity.this,
                        Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/a.mp3 does not exist!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // LibVLC manages playback with media lists.
            // Let's get the primary default list that comes with it.
            MediaList list = mLibVLC.getPrimaryMediaList();

            // Clear the list for demonstration purposes.
            list.clear();

            // Add the file. Notice that paths _must_ be converted to locations.
            list.add(LibVLC.PathToURI(amp3));

            // Finally, play it!
            mLibVLC.playIndex(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the LibVLC multimedia framework.
        // This is required before doing anything with LibVLC.
        try {
            mLibVLC = LibVLC.getInstance();
            mLibVLC.init(MainActivity.this);
        } catch(LibVlcException e) {
            Toast.makeText(MainActivity.this,
                    "Error initializing the libVLC multimedia framework!",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the UI elements.
        mAdapter = new DirectoryAdapter();
        Button load_a_mp3 = (Button) findViewById(R.id.load_a_mp3);
        load_a_mp3.setOnClickListener(mSimpleListener);
        final ListView mediaView = (ListView) findViewById(R.id.mediaView);
        mediaView.setAdapter(mAdapter);
        mediaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
                if (mAdapter.isAudioMode()) {
                    mLibVLC.getMediaList().insert(0,
                            (String) mAdapter.getItem(position));
                    mLibVLC.playIndex(0);
                } else {
                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    intent.putExtra(VideoActivity.LOCATION, (String) mAdapter.getItem(position));
                    startActivity(intent);
                }
            }
        });
        RadioButton radioAudio = (RadioButton)findViewById(R.id.radioAudio);
        radioAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setAudioMode(true);
                mAdapter.refresh();
            }
        });
        RadioButton radioVideo = (RadioButton)findViewById(R.id.radioVideo);
        radioVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setAudioMode(false);
                mAdapter.refresh();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mLibVLC.closeAout();
        mLibVLC.destroy();
        mLibVLC = null;
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
