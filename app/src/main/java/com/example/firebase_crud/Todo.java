package com.example.firebase_crud;

import java.util.Date;

public class Todo {
    String TaskID;
    String Task;
    boolean isComplete;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    String UserID;

    public Todo(){}
    public Todo(String taskID, String task,  boolean isComplete, String userID) {
        TaskID = taskID;
        Task = task;
        this.isComplete = isComplete;
        UserID = userID;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }


    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
