package dvprs.dvcipm.org.dvprs_data_collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DvprsLast extends AppCompatActivity {

    private static final String LOG_TAG = "Log: ";

    DBHandler DBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dvprs_last);
        DBHandler = new DBHandler(this, null, null, 1);
        String dbString = DBHandler.dbToString();
        updateTextView(dbString);

    }

    public void updateTextView(String toThis) {
        TextView textView = (TextView) findViewById(R.id.textViewLast);
        textView.setText(toThis);
    }

}
