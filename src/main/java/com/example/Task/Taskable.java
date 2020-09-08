package com.example.Task;

public interface Taskable
{
    String showStatus();
    String showName();
    String showDescription();
    String showDate();
    void changeStatusToReady();
    void changeStatusToNowInWork();
    void changeStatusToNotReady();
    void changeName(String newName);
    boolean changeData(String newDate);
    void changeDescription(String newDescription);
}