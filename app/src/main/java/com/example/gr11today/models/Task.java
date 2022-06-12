package com.example.gr11today.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String title;
    private String description;
    private Date date;
    private Boolean done;

    @Ignore
    private User user;
    @Ignore
    private Label label;


    @Ignore
    private static ArrayList<Task> tasks = new ArrayList<Task>() {{
        add(new Task("Task1", Calendar.getInstance().getTime(), new Label("Label1")));
        add(new Task("Task2", Calendar.getInstance().getTime(), new Label("Label2")));
        add(new Task("Task3", Calendar.getInstance().getTime(), new Label("Label3")));
        add(new Task("Task4", Calendar.getInstance().getTime(), new Label("Label4"), true));
        add(new Task("Task5", Calendar.getInstance().getTime(), new Label("Label5")));
        add(new Task("Task6", Calendar.getInstance().getTime(), new Label("Label6")));
        add(new Task("Task1", Calendar.getInstance().getTime(), new Label("Label1")));
        add(new Task("Task2", Calendar.getInstance().getTime(), new Label("Label2")));
        add(new Task("Task3", Calendar.getInstance().getTime(), new Label("Label3")));
        add(new Task("Task4", Calendar.getInstance().getTime(), new Label("Label4"), true));
        add(new Task("Task5", Calendar.getInstance().getTime(), new Label("Label5")));
        add(new Task("Task6", Calendar.getInstance().getTime(), new Label("Label6")));
        add(new Task("Task1", Calendar.getInstance().getTime(), new Label("Label1")));
        add(new Task("Task2", Calendar.getInstance().getTime(), new Label("Label2")));
        add(new Task("Task3", Calendar.getInstance().getTime(), new Label("Label3")));
        add(new Task("Task4", Calendar.getInstance().getTime(), new Label("Label4"), true));
        add(new Task("Task5", Calendar.getInstance().getTime(), new Label("Label5")));
        add(new Task("Task6", Calendar.getInstance().getTime(), new Label("Label6")));
        add(new Task("Task1", Calendar.getInstance().getTime(), new Label("Label1")));
        add(new Task("Task2", Calendar.getInstance().getTime(), new Label("Label2")));
        add(new Task("Task3", Calendar.getInstance().getTime(), new Label("Label3")));
        add(new Task("Task4", Calendar.getInstance().getTime(), new Label("Label4"), true));
        add(new Task("Task5", Calendar.getInstance().getTime(), new Label("Label5")));
        add(new Task("Task6", Calendar.getInstance().getTime(), new Label("Label6")));
    }};

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm");

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


    public static ArrayList<Task> getAll() {
        return tasks;
    }

    public static ArrayList<Task> getAllOpen() {
        ArrayList<Task> openTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDone() == false) {
                openTasks.add(task);
            }
        }
        return openTasks;
    }

    public static ArrayList<Task> getAllClosed() {
        ArrayList<Task> openTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDone() == true) {
                openTasks.add(task);
            }
        }
        return openTasks;
    }


    public Task(String title, Date date, Label label) {
        this.title = title;
        this.date = date;
        this.label = label;
        this.done = false;
    }

    public Task(String title, Date date, Label label, Boolean done) {
        this.title = title;
        this.date = date;
        this.label = label;
        this.done = done;
    }

    public Task() {
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
        String strDate = sdf.format(this.date);
        return strDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
