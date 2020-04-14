package com.example.azcar.activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.azcar.R;
import com.example.azcar.service.Utilities;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaPlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {


    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.link)
    TextView link;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.mSeekBar)
    SeekBar mSeekBar;
    @BindView(R.id.soundHigh)
    ImageView soundHigh;
    @BindView(R.id.soundLow)
    ImageView soundLow;
    AudioManager audioManager;
    MediaPlayer mediaPlayer;

    Handler mHandler;
    Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        ButterKnife.bind(this);

        utilities = new Utilities();
        mHandler = new Handler();
        mediaPlayer = new MediaPlayer();


        mSeekBar.setOnSeekBarChangeListener(this);
        mediaPlayer.setOnCompletionListener(this);

        mediaPlayer = MediaPlayer.create(MediaPlayerActivity.this, R.raw.a6);

        String soraName = getIntent().getExtras().getString("soraName");
        description.setText("سورة " + soraName);
        playAndPauseBtns();
//        volumeControl();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        finish();
    }

    private void volumeControl() {
//        audioManager = getApplicationContext().getSystemService( MediaPlayerActivity.this , AUDIO_SERVICE);
//
//        soundHigh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                audioManager.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_PLAY_SOUND);
//            }
//        });
//        soundLow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                audioManager.adjustVolume(AudioManager.ADJUST_LOWER , AudioManager.FLAG_PLAY_SOUND);
//            }
//        });
    }

    private void playAndPauseBtns() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    if (mediaPlayer != null) {
                        mediaPlayer.pause();
                        updateSeekBar();
                        play.setImageResource(R.drawable.btnplay);

                    }
                } else if (mediaPlayer != null) {
                    mediaPlayer.start();
                    updateSeekBar();
                    play.setImageResource(R.drawable.btnpause);

                }
            }

        });
    }


    public void updateSeekBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            long totalDuration = mediaPlayer.getDuration();
            long currentDuration = mediaPlayer.getCurrentPosition();

            int progress = utilities.getProgressPercentage(currentDuration, totalDuration);

            mSeekBar.setProgress(progress);
            mHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onCompletion(MediaPlayer mp) {

        mp.release();
        play.setImageResource(R.drawable.btnplay);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mediaPlayer.getDuration();

        try {
            int currentDuration = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(currentDuration);
        } catch (IllegalStateException e) {
            Log.d("MUUUU", "onStopTrackingTouch: ");
        }


        updateSeekBar();

    }
}
