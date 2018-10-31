package dvprs.dvcipm.org.dvprs_data_collection;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button; 
import android.widget.ImageButton;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VideoSelectActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Log: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_select);

//        File path = getFilesDir();
//        // Executes DownloadTask in background
//        Context context = getApplicationContext();
//        final DownloadTask downloadTask = new DownloadTask(context, VideoSelectActivity.this);
//        String[] check = {"Chronification.mp4", "dvprs_video.mp4", "medtakeback.mp4",
//                 "pastor.mp4"};
////                    "essentials.mp4", "safeopioid.mp4", "stepped.mp4", "understandpain.mp4"};
//        // declare the dialog as a member field of your activity
//        ArrayList<String> checker = new ArrayList<>();
//        for(String i : check) {
//            Boolean c = fileExistance(i);
//            if (c == Boolean.TRUE){
//                checker.add(i);
//            }
//        }
//        String[] videos = new String[checker.size()];
//        videos = checker.toArray(videos);

//        if (isExternalStorageReadable() == true) {
//            String albumName = "DVPRSVideos/pastor.mp4";
//            File file = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_MOVIES), albumName);
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(context, Uri.fromFile(file));
//            String hasVideo = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO);
//            boolean isVideo = "yes".equals(hasVideo);
//        }

//        downloadTask.execute(videos);

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start1 = (ImageButton) findViewById(R.id.video1);
        start1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 1);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start2 = (ImageButton) findViewById(R.id.video2);
        start2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 2);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start3 = (ImageButton) findViewById(R.id.video3);
        start3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 3);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start4 = (ImageButton) findViewById(R.id.video4);
        start4.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 4);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start5 = (ImageButton) findViewById(R.id.video5);
        start5.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 5);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start6 = (ImageButton) findViewById(R.id.video6);
        start6.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 6);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start7 = (ImageButton) findViewById(R.id.video7);
        start7.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 7);
                        startActivity(videoIntent);
                    }

                }
        );

        //Listener for Video Button -> DvprsVideoActivity
        ImageButton start8 = (ImageButton) findViewById(R.id.video8);
        start8.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(VideoSelectActivity.this, VideoActivity.class);
                        videoIntent.putExtra("video", 8);
                        startActivity(videoIntent);
                    }

                }
        );
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}
