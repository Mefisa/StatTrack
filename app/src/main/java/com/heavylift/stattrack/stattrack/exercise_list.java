package com.heavylift.stattrack.stattrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class exercise_list extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        mRecyclerView = findViewById(R.id.recyclerview_exercises);
        new FirebaseDatabaseHelper().readExercises(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Exercise> exercises, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, exercise_list.this,
                        exercises, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exerciselist_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_exercise:
                startActivity(new Intent(this, NewExerciseActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
