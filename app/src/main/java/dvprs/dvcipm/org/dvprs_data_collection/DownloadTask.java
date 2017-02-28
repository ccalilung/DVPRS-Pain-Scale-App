package dvprs.dvcipm.org.dvprs_data_collection;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;


// Download files
public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private Activity activity;
    private WeakReference<Activity> mWeakActivity;
    private static final String LOG_TAG = "Log: ";

    public DownloadTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.linlaHeaderProgress);
        // LOAD THE SPINNER BEFORE
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... videos) {
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context.getApplicationContext(),
                "us-east-1:dc9eebc9-a87b-47ec-a3a3-43b65f6f9120", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        for(String i : videos) {
            //Start the timer
            final String x = i;
            Timer timer = new Timer();
            TimerTask timerTask;
            String s = new String();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    String y;
                    y = "Currently downloading: " + x + " to " + context.getFilesDir().toString();
                    Log.w(LOG_TAG, "What was sent to updateText: " + y);
                    updateText(y);
                }
            };
            timer.schedule(timerTask, 0 , 10000);

            File path = context.getFilesDir();
            File file = new File(path, i);

            AmazonS3 s3 = new AmazonS3Client(credentialsProvider);
            TransferUtility transferUtility = new TransferUtility(s3, context);
            final TransferObserver transferObserver = transferUtility.download("dvprsapp/videos", i, file);

            transferObserver.setTransferListener(new TransferListener(){
                @Override
                public void onStateChanged(int id, TransferState state) {
                    Log.w(LOG_TAG, "AWS S3 NEW State: " + state);
                }
                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                    int percentage = (int) (bytesCurrent/bytesTotal * 100);
                    //Display percentage transfered to user
                    Log.w(LOG_TAG, "AWS S3 Load Percent: " + percentage);
                }
                @Override
                public void onError(int id, Exception ex) {
                    // do something
                    Log.w(LOG_TAG, "AWS S3 Error: " + ex);
                }

            });
            timer.cancel();
        }

        return null;
    }

    protected void onPostExecute(Void... params) {
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.linlaHeaderProgress);
        // HIDE THE SPINNER AFTER LOADING FEEDS
        linearLayout.setVisibility(View.GONE);
    }

    private void updateText(String msg) {
        final String str = msg;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView text = (TextView) activity.findViewById(R.id.TextProgress);
                text.setText(str);
            }
        });
    }

}