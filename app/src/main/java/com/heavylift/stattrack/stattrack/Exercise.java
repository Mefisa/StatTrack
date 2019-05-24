package com.heavylift.stattrack.stattrack;

public class Exercise {
    private String name;
    private String reps;
    private String sets;
    private String rest_time;

    public Exercise() {
    }

    public Exercise(String name, String reps, String sets, String rest_time) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.rest_time = rest_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getRest_time() {
        return rest_time;
    }

    public void setRest_time(String rest_time) {
        this.rest_time = rest_time;
    }
}
