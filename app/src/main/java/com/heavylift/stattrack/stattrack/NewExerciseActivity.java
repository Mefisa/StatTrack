package com.heavylift.stattrack.stattrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewExerciseActivity extends AppCompatActivity {

    private EditText mName_editTxt;
    private EditText mSets_editTxt;
    private EditText mReps_editTxt;
    private EditText mWeight_editTxt;
    private EditText mRestTime_editTxt;

    private Spinner mExercise_categories_spinner;

    private Button mAdd_btn;
    private Button mBack_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        mName_editTxt = (EditText) findViewById(R.id.name_editTxt);
        mSets_editTxt = (EditText) findViewById(R.id.sets_editTxt);
        mReps_editTxt = (EditText) findViewById(R.id.reps_editTxt);
        mRestTime_editTxt = (EditText) findViewById(R.id.restTime_editTxt);
        mWeight_editTxt = (EditText) findViewById(R.id.weight_editTxt);

        mExercise_categories_spinner = (Spinner) findViewById(R.id.exercise_categories_spinner);

        mAdd_btn = (Button) findViewById(R.id.add_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise exercise = new Exercise();
                exercise.setName(mName_editTxt.getText().toString());
                exercise.setSets(mSets_editTxt.getText().toString());
                exercise.setReps(mReps_editTxt.getText().toString());
                exercise.setRest_time(mRestTime_editTxt.getText().toString());
                exercise.setWeight(mWeight_editTxt.getText().toString());
                new FirebaseDatabaseHelper().addExercise(exercise, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Exercise> exercises, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewExerciseActivity.this, "The exercise has been inserted successfully",
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

    }
}
