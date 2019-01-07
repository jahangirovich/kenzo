package com.example.ainurbayanova.myappfortomorrow.Model;

public class ModelTask implements Item{
    public String title;
    protected int date;

    public ModelTask(){

    }

    public ModelTask(String title,int date){
        this.title = title;
        this.date = date;
    }

    @Override
    public boolean isTask() {
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
