package com.eci.cosw.springbootsecureapi.model;

public class Todo {

    private String description;
    private int priority = 1;
    private boolean completed;

    public Todo(String description,int priority,boolean completed ){
        this.setDescription(description);
        this.setPriority(priority);
        this.setCompleted(completed);
    }

    public Todo(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
