package com.example.gr11today.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private Integer id;
    private String title;
    private String description;
    private Date date;
    private User user;
    private Label label;
    private Boolean done;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", label=" + label +
                '}';
    }

    public static ArrayList<Task> tasks = new ArrayList<Task>() {{
        add(new Task("Task1", Calendar.getInstance().getTime(), new Label("Label1")));
        add(new Task("Task2", Calendar.getInstance().getTime(), new Label("Label2")));
        add(new Task("Task3", Calendar.getInstance().getTime(), new Label("Label3")));
        add(new Task("Task4", Calendar.getInstance().getTime(), new Label("Label4")));
        add(new Task("Task5", Calendar.getInstance().getTime(), new Label("Label5")));
        add(new Task("Task6", Calendar.getInstance().getTime(), new Label("Label6")));
    }};

    public static ArrayList<Task> getAll() {
        return tasks;
    }

    public Task(String title, Date date, Label label) {
        this.title = title;
        this.date = date;
        this.label = label;
        this.done = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        String strDate = "" + date;
        return strDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
