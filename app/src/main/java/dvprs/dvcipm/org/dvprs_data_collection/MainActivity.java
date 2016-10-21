package dvprs.dvcipm.org.dvprs_data_collection;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    private static final int FIVE_SECONDS = 5 * 1000; // 5s * 1000 ms/s
    private long fiveFingerDownTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Listener for Video Button -> DvprsVideoActivity
        Button startVideo = (Button) findViewById(R.id.dvprsVideo);
        startVideo.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent videoIntent = new Intent(MainActivity.this, DvprsVideoActivity.class);
                        MainActivity.this.startActivity(videoIntent);
                    }

                }
        );

        //Need to add Listener for DVPRS Data Entry Activity
        //Listener for Video Button -> DvprsVideoActivity
        Button startDvprs = (Button) findViewById(R.id.dvprsSurvey);
        startDvprs.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent surveyIntent = new Intent(MainActivity.this, DvprsEntry.class);
                        MainActivity.this.startActivity(surveyIntent);
                    }
                }
        );

        ImageView dvprsImage = (ImageView) findViewById(R.id.imageView);
        dvprsImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                final int action = ev.getAction();
                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (ev.getPointerCount() == 5) {
                            // We have five fingers touching, so start the timer
                            fiveFingerDownTime = System.currentTimeMillis();
                        }
                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                        if (ev.getPointerCount() < 5) {
                            // Fewer than five fingers, so reset the timer
                            fiveFingerDownTime = -1;
                        }
                        final long now = System.currentTimeMillis();
                        if (now - fiveFingerDownTime > FIVE_SECONDS && fiveFingerDownTime != -1) {
                            try {
                                copyDataBase();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        break;
                }

                return true;

            }
        });
    }
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    public static void copyDataBase() throws IOException {
        //Open your local db as the input stream
        String inFileName = "/data/data/dvprs.dvcipm.org.dvprs_data_collection/databases/scores.db";
        File dbFile = new File(inFileName);
        FileInputStream fis = new FileInputStream(dbFile);

        String outFileName = Environment.getExternalStorageDirectory()+"/MYDB.db";
        //Open the empty db as the output stream
        OutputStream output = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        //Close the streams
        output.flush();
        output.close();
        fis.close();
    }
}
