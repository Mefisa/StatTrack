package com.heavylift.stattrack.stattrack;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceExercises;
    private List<Exercise> exercises  = new ArrayList<>();

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceExercises = mDatabase.getReference("exercises");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Exercise> exercises, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readExercises(final DataStatus dataStatus){
        mReferenceExercises.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exercises.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Exercise exercise = keyNode.getValue(Exercise.class);
                    exercises.add(exercise);
                }
                dataStatus.DataIsLoaded(exercises, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addExercise (Exercise exercise, final DataStatus dataStatus){
        String key = mReferenceExercises.push().getKey();
        mReferenceExercises.child(key).setValue(exercise)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void updateExercise(String key, Exercise exercise, final DataStatus datastatus){
        mReferenceExercises.child(key).setValue(exercise)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        datastatus.DataIsUpdated();
                    }
                });
    }

    public void deleteExercise(String key, final DataStatus datastatus) {
        mReferenceExercises.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                datastatus.DataIsDeleted();
            }
        });
    }
}
