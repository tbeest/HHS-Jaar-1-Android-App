package com.example.gr11today.models;

import static androidx.room.ForeignKey.SET_NULL;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gr11today.Converters;
import com.example.gr11today.Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Label.class,
                parentColumns = "labelId",
                childColumns = "labelId",
                onDelete = SET_NULL),
        @ForeignKey(
                entity = User.class,
                parentColumns = "userId",
                childColumns = "userId")
})

public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer taskId;

    @ColumnInfo(index = true)
    private Integer labelId;
    @ColumnInfo(index = true)
    private Integer userId;

    private String title;
    private String description;

    @TypeConverters({Converters.class})
    private Date date;
    private Boolean done;

    @Ignore
    private Label label;

    @Ignore
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm");


    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", labelId=" + labelId +
                ", userId=" + userId +
                '}';
    }


    public static void addTask(Task task, User user, Context context) {
        if (user != null) {
            task.setUserId(user.getUserId());
            Database.getDatabase(context).taskDao().insert(task);
        }
    }

    public static void updateTask(Task task, Context context) {
        if (task != null) {
            task.setUserId(User.getActiveUser().getUserId());
            Database.getDatabase(context).taskDao().update(task);
        }
    }

    public static void deleteTask(Task task, Context context) {
        if (task.getTaskId() > 0) {
            Database.getDatabase(context).taskDao().delete(task);
        }
    }

    public static List<Task> getAll(Context context, boolean done, int labelId) {
        List<Task> tasks;

        if (labelId > 0) {
            tasks = Database.getDatabase(context).taskDao().getAllLabel(done, labelId, User.getActiveUser().getUserId());
        } else {
            tasks = Database.getDatabase(context).taskDao().getAll(done, User.getActiveUser().getUserId());
        }

        for (Task task : tasks) {
            if (task.labelId != null) {
                task.label = Database.getDatabase(context).labelDao().getById(task.labelId, User.getActiveUser().getUserId());
            }
        }
        return tasks;
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
    public Task(String title, String description, Date date, Boolean done, Integer labelId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.done = done;
        this.labelId = labelId;
    }

    @Ignore
    public Task(String title, String description, Boolean done, Integer labelId) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.labelId = labelId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
