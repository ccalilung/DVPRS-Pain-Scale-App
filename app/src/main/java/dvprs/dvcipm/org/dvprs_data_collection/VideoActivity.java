package dvprs.dvcipm.org.dvprs_data_collection;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Log: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dvprs_video);
        //Intent intent = getIntent();
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
                Log.w(LOG_TAG, "Main Activity is not the root.  Finishing Main Activity instead of launching.");
                finish();
                return;
            }
        }
        final VideoView dvprsVideoView = (VideoView) findViewById(R.id.dvprsVideo);

//        File path = getFilesDir();

        String path = "/storage/extSdCard/Movies";

        // Get Intent info
        Bundle b = getIntent().getExtras();
        int id = b.getInt("video");
        String video;
        switch (id) {
            case 1:  video = path + "/understandpain.mp4";
                break;
            case 2:  video = path + "/medtakeback.mp4";
                break;
            case 3:  video = path + "/dvprs_video.mp4";
                break;
            case 4:  video = path + "/essentials.mp4";
                break;
            case 5:  video = path + "/safeopioid.mp4";
                break;
            case 6:  video = path + "/stepped.mp4";
                break;
            case 7:  video = path + "/pastor.mp4";
                break;
            case 8:  video = path + "/Chronification.mp4";
                break;
            default: video = path + "/dvprs_video.mp4";
                break;
        }

        //start media controller
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mediaController);
        dvprsVideoView.setMediaController(mediaController);

//        Uri uri = Uri.parse(video);
//        dvprsVideoView.setVideoURI(uri);

        dvprsVideoView.setVideoPath(video);
        dvprsVideoView.start();
        dvprsVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                Intent selectIntent = new Intent(VideoActivity.this, VideoSelectActivity.class);
                VideoActivity.this.startActivity(selectIntent);
            }
        });


    }
}