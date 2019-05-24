package com.heavylift.stattrack.stattrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ExerciseDetailsActivity extends AppCompatActivity {

    private EditText mName_editTxt;
    private EditText mReps_editTxt;
    private EditText mSets_editTxt;
    private EditText mRestTime_editText;
    private Spinner mExercise_categories_spinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String name;
    private String reps;
    private String sets;
    private String restTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        reps = getIntent().getStringExtra("reps");
        sets = getIntent().getStringExtra("sets");
        restTime = getIntent().getStringExtra("restTime");

        mName_editTxt = (EditText) findViewById(R.id.name_editTxt);
        mReps_editTxt = (EditText) findViewById(R.id.reps_editTxt);
        mSets_editTxt = (EditText) findViewById(R.id.sets_editTxt);
        mRestTime_editText = (EditText) findViewById(R.id.restTime_editTxt);
        mExercise_categories_spinner = (Spinner) findViewById(R.id.exercise_categories_spinner);
        //mExercise_categories_spinner.setSelection(getIndex_SpinnerItem(mExercise_categories_spinner,
        //        ));

        mUpdate_btn = (Button) findViewById(R.id.update_btn);
        mDelete_btn = (Button) findViewById(R.id.delete_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);
    }

    private int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for (int i = 0; i<spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }
}
