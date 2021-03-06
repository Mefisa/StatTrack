package com.heavylift.stattrack.stattrack;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RecyclerViewConfig {
    FirebaseAuth mAuth;
    private static FirebaseUser user;

    private Context mContext;
    private ExerciseAdapter mExerciseAdapter;


    public void setConfig(RecyclerView recyclerView, Context context, List<Exercise> exercises,
                          List<String> keys) {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        mContext = context;
        mExerciseAdapter = new ExerciseAdapter(exercises, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mExerciseAdapter);
    }


    class ExerciseItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mReps;
        private TextView mSets;
        private TextView mRestTime;
        private TextView mWeight;

        private String key;


        public ExerciseItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.exercise_list_item, parent, false));

            mName = itemView.findViewById(R.id.name_txtView);
            mReps = itemView.findViewById(R.id.reps_txtView);
            mSets = itemView.findViewById(R.id.sets_txtView);
            mRestTime = itemView.findViewById(R.id.restTime_txtView);
            mWeight = itemView.findViewById(R.id.weight_txtView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user != null) {
                        Intent intent = new Intent(mContext, ExerciseDetailsActivity.class);
                        intent.putExtra("key", key);
                        intent.putExtra("name", mName.getText().toString());
                        intent.putExtra("reps", mReps.getText().toString());
                        intent.putExtra("sets", mSets.getText().toString());
                        intent.putExtra("restTime", mRestTime.getText().toString());
                        intent.putExtra("weight", mWeight.getText().toString());
                        mContext.startActivity(intent);
                    } else {
                        mContext.startActivity(new Intent(mContext, SignupPage.class));
                    }


                }
            });
        }

        public void bind(Exercise exercise, String key) {
            mName.setText(exercise.getName());
            mReps.setText("Reps: " + exercise.getReps());
            mSets.setText("Sets: " + exercise.getSets());
            mRestTime.setText("Rest Time: " + exercise.getRest_time());
            mWeight.setText("Weight: " + exercise.getWeight());
            this.key = key;
        }



    }


    class ExerciseAdapter extends RecyclerView.Adapter<ExerciseItemView> {
        private List<Exercise> mExerciseList;
        private List<String> mKeys;

        public ExerciseAdapter(List<Exercise> mExerciseList, List<String> mKeys) {
            this.mExerciseList = mExerciseList;
            this.mKeys = mKeys;
        }

        @Override
        public ExerciseItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ExerciseItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ExerciseItemView exerciseItemView, int i) {
            exerciseItemView.bind(mExerciseList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mExerciseList.size();
        }
    }

    public static void logout(){
        user = null;
    }
}
