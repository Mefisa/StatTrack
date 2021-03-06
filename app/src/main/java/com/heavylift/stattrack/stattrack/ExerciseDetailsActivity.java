package com.heavylift.stattrack.stattrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ExerciseDetailsActivity extends AppCompatActivity {

    private EditText mName_editTxt;
    private EditText mReps_editTxt;
    private EditText mSets_editTxt;
    private EditText mWeight_editTxt;
    private EditText mRestTime_editTxt;
    private Spinner mExercise_categories_spinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String name;
    private String reps;
    private String sets;
    private String restTime;
    private String weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        reps = getIntent().getStringExtra("reps");
        sets = getIntent().getStringExtra("sets");
        restTime = getIntent().getStringExtra("restTime");
        weight = getIntent().getStringExtra("weight");

        mName_editTxt = (EditText) findViewById(R.id.name_editTxt);
        mReps_editTxt = (EditText) findViewById(R.id.reps_editTxt);
        mSets_editTxt = (EditText) findViewById(R.id.sets_editTxt);
        mRestTime_editTxt = (EditText) findViewById(R.id.restTime_editTxt);
        mWeight_editTxt = (EditText) findViewById(R.id.weight_editTxt);
        mExercise_categories_spinner = (Spinner) findViewById(R.id.exercise_categories_spinner);
        //mExercise_categories_spinner.setSelection(getIndex_SpinnerItem(mExercise_categories_spinner,
        //        ));

        sets = sets.replace("Sets: ", "");
        reps = reps.replace("Reps: ", "");
        restTime = restTime.replace("Rest Time: ", "");
        weight = weight.replace("Weight: ", "");

        mName_editTxt.setText(name);
        mReps_editTxt.setText(reps);
        mSets_editTxt.setText(sets);
        mRestTime_editTxt.setText(restTime);
        mWeight_editTxt.setText(weight);

        mUpdate_btn = (Button) findViewById(R.id.update_btn);
        mDelete_btn = (Button) findViewById(R.id.delete_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise exercise = new Exercise();
                exercise.setName(mName_editTxt.getText().toString());
                exercise.setReps(mReps_editTxt.getText().toString());
                exercise.setSets(mSets_editTxt.getText().toString());
                exercise.setRest_time(mRestTime_editTxt.getText().toString());
                exercise.setWeight(mWeight_editTxt.getText().toString());


                new FirebaseDatabaseHelper().updateExercise(key, exercise, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Exercise> exercises, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(ExerciseDetailsActivity.this, "Exercise has been" +
                        " updated successfully.", Toast.LENGTH_LONG).show();
                        finish();
                        return;

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteExercise(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Exercise> exercises, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(ExerciseDetailsActivity.this,
                                "Exercise record has been deleted successfully", Toast.LENGTH_LONG).show();
                        finish();
                        return;
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
