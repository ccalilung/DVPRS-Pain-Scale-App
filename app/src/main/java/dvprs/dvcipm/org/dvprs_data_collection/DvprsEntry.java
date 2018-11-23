package dvprs.dvcipm.org.dvprs_data_collection;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class DvprsEntry extends AppCompatActivity {

    private static final String LOG_TAG = "Log: ";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int count = 0;
    private long startMillis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();

        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
                Log.w(LOG_TAG, "Main Activity is not the root.  Finishing Main Activity instead of launching.");
                finish();
                return;
            }
        }

        firstQuestion();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    Integer currentScreen = -99, dvprs = -99, Activity = -99, Sleep = -99, Mood = -99, Stress = -99, question1 = -99, question2 = -99, question3 = -99, question4 = -99, question5 = 99,
            dvprsLanguageId = 11, dvprsImageId = 12, dvprsGroupId = 13, submitButtonId = 14, dvprsRadio0Id = 0, dvprsRadio1Id = 1,
            dvprsRadio2Id = 2, dvprsRadio3Id = 3, dvprsRadio4Id = 4, dvprsRadio5Id = 5, dvprsRadio6Id = 6, dvprsRadio7Id = 7,
            dvprsRadio8Id = 8, dvprsRadio9Id = 9, dvprsRadio10Id = 10;

    DBHandler DBHandler;

    private void firstQuestion() {
        currentScreen = 0;
        final RelativeLayout dvprsSecondLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams dvprsSupTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams dvprsSupGroupLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        final RelativeLayout.LayoutParams dvprsSubmitLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //Widgets to be displayed
        TextView dvprsLanguage = new TextView(this);
        RadioGroup dvprsGroup = new RadioGroup(this);
        final Button submitButton = new Button(this);
        RadioButton dvprsRadio0 = new RadioButton(this);
        RadioButton dvprsRadio1 = new RadioButton(this);
        RadioButton dvprsRadio2 = new RadioButton(this);
        RadioButton dvprsRadio3 = new RadioButton(this);
        RadioButton dvprsRadio4 = new RadioButton(this);

        //Rules & Params for various Widgets
        dvprsSupTextLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        dvprsSupGroupLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSupGroupLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dvprsSubmitLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSubmitLayout.addRule(RelativeLayout.RIGHT_OF, dvprsGroupId);
        dvprsLanguage.setText(R.string.questionOne);
        dvprsRadio0.setText(R.string.veryDissatisfied);
        dvprsRadio1.setText(R.string.dissatisfied);
        dvprsRadio2.setText(R.string.neutral);
        dvprsRadio3.setText(R.string.satisfied);
        dvprsRadio4.setText(R.string.verySatisfied);
        dvprsRadio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsGroup.setPadding(0, 20, 0, 300);
        dvprsSupTextLayout.setMargins(100, 100, 0, 0);
        submitButton.setText("Submit");
        submitButton.setHeight(150);
        submitButton.setWidth(150);
        dvprsSubmitLayout.setMargins(20, 0, 0, 300);
        dvprsLanguage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        //Widget IDs
        dvprsLanguage.setId(11);
        dvprsGroup.setId(13);
        submitButton.setId(14);
        dvprsRadio0.setId(0);
        dvprsRadio1.setId(1);
        dvprsRadio2.setId(2);
        dvprsRadio3.setId(3);
        dvprsRadio4.setId(4);


        //Add Widgets and Params to layout
        dvprsSecondLayout.addView(dvprsLanguage, dvprsSupTextLayout);
        dvprsGroup.addView(dvprsRadio0, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio1, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio2, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio3, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio4, dvprsSupGroupLayout);
        dvprsSecondLayout.addView(dvprsGroup, dvprsSupGroupLayout);

        //Start the layout
        setContentView(dvprsSecondLayout);


        //Listeners for First View
        dvprsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup dvprsGroup, int checkedId) {
                if (findViewById(14) == null) {
                    dvprsSecondLayout.addView(submitButton, dvprsSubmitLayout);
                    setContentView(dvprsSecondLayout);
                }
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedDvprs = dvprsGroup.getCheckedRadioButtonId();
                        question1 = selectedDvprs;
                        Log.d("Checked", " : " + selectedDvprs);
                        // Perform action on click
                        ((ViewGroup) dvprsSecondLayout.getParent()).removeView(dvprsSecondLayout);
                        secondQuestion();
                    }
                });
            }
        });
    }

    private void secondQuestion() {
        currentScreen = 1;
        final RelativeLayout dvprsSecondLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams dvprsSupTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams dvprsSupGroupLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        final RelativeLayout.LayoutParams dvprsSubmitLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //Widgets to be displayed
        TextView dvprsLanguage = new TextView(this);
        RadioGroup dvprsGroup = new RadioGroup(this);
        final Button submitButton = new Button(this);
        RadioButton dvprsRadio0 = new RadioButton(this);
        RadioButton dvprsRadio1 = new RadioButton(this);
        RadioButton dvprsRadio2 = new RadioButton(this);
        RadioButton dvprsRadio3 = new RadioButton(this);
        RadioButton dvprsRadio4 = new RadioButton(this);

        //Rules & Params for various Widgets
        dvprsSupTextLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        dvprsSupGroupLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSupGroupLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dvprsSubmitLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSubmitLayout.addRule(RelativeLayout.RIGHT_OF, dvprsGroupId);
        dvprsLanguage.setText(R.string.questionTwo);
        dvprsRadio0.setText(R.string.notAtAll);
        dvprsRadio1.setText(R.string.aLittleBit);
        dvprsRadio2.setText(R.string.somewhat);
        dvprsRadio3.setText(R.string.moderately);
        dvprsRadio4.setText(R.string.very);
        dvprsRadio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsGroup.setPadding(0, 20, 0, 300);
        dvprsSupTextLayout.setMargins(100, 100, 0, 0);
        submitButton.setText("Submit");
        submitButton.setHeight(150);
        submitButton.setWidth(150);
        dvprsSubmitLayout.setMargins(20, 0, 0, 300);
        dvprsLanguage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        //Widget IDs
        dvprsLanguage.setId(11);
        dvprsGroup.setId(13);
        submitButton.setId(14);
        dvprsRadio0.setId(0);
        dvprsRadio1.setId(1);
        dvprsRadio2.setId(2);
        dvprsRadio3.setId(3);
        dvprsRadio4.setId(4);


        //Add Widgets and Params to layout
        dvprsSecondLayout.addView(dvprsLanguage, dvprsSupTextLayout);
        dvprsGroup.addView(dvprsRadio0, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio1, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio2, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio3, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio4, dvprsSupGroupLayout);
        dvprsSecondLayout.addView(dvprsGroup, dvprsSupGroupLayout);

        //Start the layout
        setContentView(dvprsSecondLayout);


        //Listeners for First View
        dvprsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup dvprsGroup, int checkedId) {
                if (findViewById(14) == null) {
                    dvprsSecondLayout.addView(submitButton, dvprsSubmitLayout);
                    setContentView(dvprsSecondLayout);
                }
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedDvprs = dvprsGroup.getCheckedRadioButtonId();
                        question2 = selectedDvprs;
                        Log.d("Checked", " : " + selectedDvprs);
                        // Perform action on click
                        ((ViewGroup) dvprsSecondLayout.getParent()).removeView(dvprsSecondLayout);
                        thirdQuestion();
                    }
                });
            }
        });
    }
   
    private void thirdQuestion() {
        currentScreen = 2;
        final RelativeLayout dvprsSecondLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams dvprsSupTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams dvprsSupGroupLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        final RelativeLayout.LayoutParams dvprsSubmitLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //Widgets to be displayed
        TextView dvprsLanguage = new TextView(this);
        RadioGroup dvprsGroup = new RadioGroup(this);
        final Button submitButton = new Button(this);
        RadioButton dvprsRadio0 = new RadioButton(this);
        RadioButton dvprsRadio1 = new RadioButton(this);
        // RadioButton dvprsRadio2 = new RadioButton(this);
        // RadioButton dvprsRadio3 = new RadioButton(this);
        // RadioButton dvprsRadio4 = new RadioButton(this);

        //Rules & Params for various Widgets
        dvprsSupTextLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        dvprsSupGroupLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSupGroupLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dvprsSubmitLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSubmitLayout.addRule(RelativeLayout.RIGHT_OF, dvprsGroupId);
        dvprsLanguage.setText(R.string.questionThree);
        dvprsRadio0.setText(R.string.no);
        dvprsRadio1.setText(R.string.yes);
        // dvprsRadio2.setText(R.string.neutral);
        // dvprsRadio3.setText(R.string.satisfied);
        // dvprsRadio4.setText(R.string.verySatisfied);
        dvprsRadio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsGroup.setPadding(0, 20, 0, 300);
        dvprsSupTextLayout.setMargins(100, 100, 0, 0);
        submitButton.setText("Submit");
        submitButton.setHeight(150);
        submitButton.setWidth(150);
        dvprsSubmitLayout.setMargins(20, 0, 0, 300);
        dvprsLanguage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        //Widget IDs
        dvprsLanguage.setId(11);
        dvprsGroup.setId(13);
        submitButton.setId(14);
        dvprsRadio0.setId(0);
        dvprsRadio1.setId(1);
        // dvprsRadio2.setId(2);
        // dvprsRadio3.setId(3);
        // dvprsRadio4.setId(4);


        //Add Widgets and Params to layout
        dvprsSecondLayout.addView(dvprsLanguage, dvprsSupTextLayout);
        dvprsGroup.addView(dvprsRadio0, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio1, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio2, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio3, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio4, dvprsSupGroupLayout);
        dvprsSecondLayout.addView(dvprsGroup, dvprsSupGroupLayout);

        //Start the layout
        setContentView(dvprsSecondLayout);


        //Listeners for First View
        dvprsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup dvprsGroup, int checkedId) {
                if (findViewById(14) == null) {
                    dvprsSecondLayout.addView(submitButton, dvprsSubmitLayout);
                    setContentView(dvprsSecondLayout);
                }
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedDvprs = dvprsGroup.getCheckedRadioButtonId();
                        question3 = selectedDvprs;
                        Log.d("Checked", " : " + selectedDvprs);
                        // Perform action on click
                        ((ViewGroup) dvprsSecondLayout.getParent()).removeView(dvprsSecondLayout);
                        fourthQuestion();
                    }
                });
            }
        });
    }

    private void fourthQuestion() {
        currentScreen = 3;
        final RelativeLayout dvprsSecondLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams dvprsSupTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams dvprsSupGroupLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        final RelativeLayout.LayoutParams dvprsSubmitLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //Widgets to be displayed
        TextView dvprsLanguage = new TextView(this);
        RadioGroup dvprsGroup = new RadioGroup(this);
        final Button submitButton = new Button(this);
        RadioButton dvprsRadio0 = new RadioButton(this);
        RadioButton dvprsRadio1 = new RadioButton(this);
        // RadioButton dvprsRadio2 = new RadioButton(this);
        // RadioButton dvprsRadio3 = new RadioButton(this);
        // RadioButton dvprsRadio4 = new RadioButton(this);

        //Rules & Params for various Widgets
        dvprsSupTextLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        dvprsSupGroupLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSupGroupLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dvprsSubmitLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSubmitLayout.addRule(RelativeLayout.RIGHT_OF, dvprsGroupId);
        dvprsLanguage.setText(R.string.questionFour);
        dvprsRadio0.setText(R.string.no);
        dvprsRadio1.setText(R.string.yes);
        // dvprsRadio2.setText(R.string.neutral);
        // dvprsRadio3.setText(R.string.satisfied);
        // dvprsRadio4.setText(R.string.verySatisfied);
        dvprsRadio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsGroup.setPadding(0, 20, 0, 300);
        dvprsSupTextLayout.setMargins(100, 100, 0, 0);
        submitButton.setText("Submit");
        submitButton.setHeight(150);
        submitButton.setWidth(150);
        dvprsSubmitLayout.setMargins(20, 0, 0, 300);
        dvprsLanguage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        //Widget IDs
        dvprsLanguage.setId(11);
        dvprsGroup.setId(13);
        submitButton.setId(14);
        dvprsRadio0.setId(0);
        dvprsRadio1.setId(1);
        // dvprsRadio2.setId(2);
        // dvprsRadio3.setId(3);
        // dvprsRadio4.setId(4);


        //Add Widgets and Params to layout
        dvprsSecondLayout.addView(dvprsLanguage, dvprsSupTextLayout);
        dvprsGroup.addView(dvprsRadio0, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio1, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio2, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio3, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio4, dvprsSupGroupLayout);
        dvprsSecondLayout.addView(dvprsGroup, dvprsSupGroupLayout);

        //Start the layout
        setContentView(dvprsSecondLayout);


        //Listeners for First View
        dvprsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup dvprsGroup, int checkedId) {
                if (findViewById(14) == null) {
                    dvprsSecondLayout.addView(submitButton, dvprsSubmitLayout);
                    setContentView(dvprsSecondLayout);
                }
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedDvprs = dvprsGroup.getCheckedRadioButtonId();
                        question4 = selectedDvprs;
                        Log.d("Checked", " : " + selectedDvprs);
                        // Perform action on click
                        ((ViewGroup) dvprsSecondLayout.getParent()).removeView(dvprsSecondLayout);
                        fifthQuestion();
                    }
                });
            }
        });
    }
    private void fifthQuestion() {
        currentScreen = 4;
        final RelativeLayout dvprsSecondLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams dvprsSupTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams dvprsSupGroupLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        final RelativeLayout.LayoutParams dvprsSubmitLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //Widgets to be displayed
        TextView dvprsLanguage = new TextView(this);
        RadioGroup dvprsGroup = new RadioGroup(this);
        final Button submitButton = new Button(this);
        RadioButton dvprsRadio0 = new RadioButton(this);
        RadioButton dvprsRadio1 = new RadioButton(this);
        // RadioButton dvprsRadio2 = new RadioButton(this);
        // RadioButton dvprsRadio3 = new RadioButton(this);
        // RadioButton dvprsRadio4 = new RadioButton(this);

        //Rules & Params for various Widgets
        dvprsSupTextLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        dvprsSupGroupLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSupGroupLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dvprsSubmitLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dvprsSubmitLayout.addRule(RelativeLayout.RIGHT_OF, dvprsGroupId);
        dvprsLanguage.setText(R.string.questionFive);
        dvprsRadio0.setText(R.string.no);
        dvprsRadio1.setText(R.string.yes);
        // dvprsRadio2.setText(R.string.neutral);
        // dvprsRadio3.setText(R.string.satisfied);
        // dvprsRadio4.setText(R.string.verySatisfied);
        dvprsRadio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsRadio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        // dvprsRadio4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        dvprsGroup.setPadding(0, 20, 0, 300);
        dvprsSupTextLayout.setMargins(100, 100, 0, 0);
        submitButton.setText("Submit");
        submitButton.setHeight(150);
        submitButton.setWidth(150);
        dvprsSubmitLayout.setMargins(20, 0, 0, 300);
        dvprsLanguage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        //Widget IDs
        dvprsLanguage.setId(11);
        dvprsGroup.setId(13);
        submitButton.setId(14);
        dvprsRadio0.setId(0);
        dvprsRadio1.setId(1);
        // dvprsRadio2.setId(2);
        // dvprsRadio3.setId(3);
        // dvprsRadio4.setId(4);


        //Add Widgets and Params to layout
        dvprsSecondLayout.addView(dvprsLanguage, dvprsSupTextLayout);
        dvprsGroup.addView(dvprsRadio0, dvprsSupGroupLayout);
        dvprsGroup.addView(dvprsRadio1, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio2, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio3, dvprsSupGroupLayout);
        // dvprsGroup.addView(dvprsRadio4, dvprsSupGroupLayout);
        dvprsSecondLayout.addView(dvprsGroup, dvprsSupGroupLayout);

        //Start the layout
        setContentView(dvprsSecondLayout);


        //Listeners for First View
        dvprsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup dvprsGroup, int checkedId) {
                if (findViewById(14) == null) {
                    dvprsSecondLayout.addView(submitButton, dvprsSubmitLayout);
                    setContentView(dvprsSecondLayout);
                }
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedDvprs = dvprsGroup.getCheckedRadioButtonId();
                        question5 = selectedDvprs;
                        Log.d("Checked", " : " + selectedDvprs);
                        // Perform action on click
                        ((ViewGroup) dvprsSecondLayout.getParent()).removeView(dvprsSecondLayout);
                        dvprsEND();
                    }
                });
            }
        });
    }
    private void dvprsEND() {
        currentScreen = 5;
        final RelativeLayout dvprsSecondLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams dvprsSupTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams dvprsScoreTextLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //Widgets to be displayed
        TextView dvprsLanguage = new TextView(this);
        TextView dvprsScore = new TextView(this);

        //Rules & Params for various Widgets
        dvprsSupTextLayout.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);
        dvprsScore.setGravity(Gravity.RIGHT);
       
        dvprsLanguage.setText(R.string.endJpep);
        dvprsSupTextLayout.setMargins(100, 120, 100, 0);
        dvprsScoreTextLayout.setMargins(100, 220, 100, 0);
        dvprsLanguage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
        dvprsScore.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        //Widget IDs
        dvprsLanguage.setId(11);

        //Add Widgets and Params to layout
        dvprsSecondLayout.addView(dvprsLanguage, dvprsSupTextLayout);
        dvprsSecondLayout.addView(dvprsScore, dvprsScoreTextLayout);

        //Start the layout
        setContentView(dvprsSecondLayout);


    }

    public void addScores() {
        DBHandler = new DBHandler(this, null, null, 1);
        Scores scores = new Scores(question1, question2, question3, question4, question5);
        DBHandler.addScore(scores);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventaction = event.getAction();
        if (eventaction == MotionEvent.ACTION_UP) {

            //get system current milliseconds
            long time= System.currentTimeMillis();


            //if it is the first time, or if it has been more than 3 seconds since the first tap ( so it is like a new try), we reset everything
            if (startMillis==0 || (time-startMillis> 3000) ) {
                startMillis=time;
                count=1;
            }
            //it is not the first, and it has been  less than 3 seconds since the first
            else{ //  time-startMillis< 3000
                count++;
            }

            if (count==5) {
                finish();
                Intent mainIntent = new Intent(DvprsEntry.this, MainActivity.class);
                DvprsEntry.this.startActivity(mainIntent);
                //do whatever you need
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent objEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, objEvent);
    }

    public void onBackPressed() {
        goBack();
    }

    public void goBack() {
        if (Activity == -99) {
            switch (currentScreen) {
                case 0:
                    firstQuestion();
                    break;
                case 1:
                    secondQuestion();
                    break;
                case 2:
                    thirdQuestion();
                    break;
                case 3:
                    fourthQuestion();
                    break;
                case 4:
                    fifthQuestion();
                    break;
               
               
            }
        } else {
            switch (currentScreen) {
                case 0:
                    firstQuestion();
                    break;
                case 1:
                    secondQuestion();
                    break;
                case 2:
                    thirdQuestion();
                    break;
                case 3:
                    fourthQuestion();
                    break;
                case 4:
                    fifthQuestion();
                    break;
                }
        }

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("DvprsEntry Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

