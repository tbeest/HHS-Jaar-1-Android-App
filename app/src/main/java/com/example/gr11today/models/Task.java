package com.example.gr11today.models;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gr11today.Converters;
import com.example.gr11today.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String title;
    private String description;

    @TypeConverters({Converters.class})
    private Date date;
    private Boolean done;

    @Ignore
    private User user;
    @Ignore
    private Label label;

    @Ignore
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

    public static void addTask(Task task, Context context) {
        if (task != null) {
            Database.getDatabase(context).taskDao().insert(task);
        }
    }

    public static void updateTask(Task task, Context context) {
        if (task != null) {
            Database.getDatabase(context).taskDao().update(task);
        }
    }

    public static void deleteTask(Task task, Context context) {
        if (task.getId() > 0) {
            Database.getDatabase(context).taskDao().delete(task);
        }
    }

    public static List<Task> getAll(Context context) {
        return Database.getDatabase(context).taskDao().getAll();
    }

    public static List<Task> getAllOpen(Context context) {
        return Database.getDatabase(context).taskDao().getAllOpen();
    }

    public static List<Task> getAllClosed(Context context) {
        return Database.getDatabase(context).taskDao().getAllClosed();
    }

    public Task() {
    }

    @Ignore
    public Task(String title) {
        this.title = title;
        this.done = false;
    }

    @Ignore
    public Task(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    @Ignore
    public Task(String title, String description, boolean done, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.done = done;
    }

    @Ignore
    public Task(String title, Date date, Label label, boolean done) {
        this.title = title;
        this.date = date;
        this.label = label;
        this.done = done;
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
        String strDate = (this.date != null) ? sdf.format(this.date) : null;
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
