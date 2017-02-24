package dvprs.dvcipm.org.dvprs_data_collection;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;
import android.widget.MediaController;

public class DvprsVideoActivity extends AppCompatActivity {

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

        //start media controller
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mediaController);
        dvprsVideoView.setMediaController(mediaController);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.dvprs_video);
        dvprsVideoView.setVideoURI(uri);
        dvprsVideoView.start();
        dvprsVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                Intent surveyIntent = new Intent(DvprsVideoActivity.this, DvprsEntry.class);
                DvprsVideoActivity.this.startActivity(surveyIntent);
            }
        });


    }
}