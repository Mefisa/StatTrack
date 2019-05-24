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

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private ExerciseAdapter mExerciseAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Exercise> exercises,
                          List<String> keys) {
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

        private String key;

        public ExerciseItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.exercise_list_item, parent, false));

            mName = itemView.findViewById(R.id.name_txtView);
            mReps = itemView.findViewById(R.id.reps_txtView);
            mSets = itemView.findViewById(R.id.sets_txtView);
            mRestTime = itemView.findViewById(R.id.restTime_txtView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ExerciseDetailsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("name", mName.getText().toString());
                    intent.putExtra("reps", mReps.getText().toString());
                    intent.putExtra("sets", mSets.getText().toString());
                    intent.putExtra("restTime", mRestTime.getText().toString());
                }
            });
        }

        public void bind(Exercise exercise, String key) {
            mName.setText("exercise name: " + exercise.getName());
            mReps.setText("reps: " + exercise.getReps());
            mSets.setText("sets: +" + exercise.getSets());
            mRestTime.setText("rest time" + exercise.getRest_time());
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
}
