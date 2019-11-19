package com.heavylift.stattrack.stattrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ExerciseList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        mAuth = FirebaseAuth.getInstance();
        mRecyclerView = findViewById(R.id.recyclerview_exercises);
        new FirebaseDatabaseHelper().readExercises(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Exercise> exercises, List<String> keys) {
                new RecyclerViewConfig().setConfig(mRecyclerView, ExerciseList.this,
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
        FirebaseUser user = mAuth.getCurrentUser();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exerciselist_activity_menu, menu);
        if (user != null) {
            menu.getItem(0).setVisible(true); // new exercise
            menu.getItem(1).setVisible(false); // sign in
            menu.getItem(2).setVisible(true); // sign out
        } else {
            menu.getItem(0).setVisible(false); // new exercise
            menu.getItem(1).setVisible(true); // sign in
            menu.getItem(2).setVisible(false); // sign out
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            menu.getItem(0).setVisible(true); // new exercise
            menu.getItem(1).setVisible(false); // sign in
            menu.getItem(2).setVisible(true); // sign out
        } else {
            menu.getItem(0).setVisible(false); // new exercise
            menu.getItem(1).setVisible(true); // sign in
            menu.getItem(2).setVisible(false); // sign out
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_exercise:
                startActivity(new Intent(this, NewExerciseActivity.class));
                return true;
            case R.id.sign_in:
                startActivity(new Intent(this, SignupPage.class));
                return true;
            case R.id.sign_out:
                mAuth.signOut();
                invalidateOptionsMenu();
                RecyclerViewConfig.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
