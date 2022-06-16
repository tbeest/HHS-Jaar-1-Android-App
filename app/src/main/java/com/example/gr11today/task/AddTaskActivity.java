package com.example.gr11today.task;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gr11today.Database;
import com.example.gr11today.R;
import com.example.gr11today.models.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button dateB;
    int day, month, year, hour, minute;
    int myDay, myMonth, myYear, myHour, myMinute;
    String taskIdStr, strDate;
    int taskId;
    Boolean editTask = false;
    EditText titleET, descriptionET;
    CheckBox taskCB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        System.out.println("OnCreate AddTaskActivity");

        Intent intent = getIntent();
        String taskIdStr = intent.getStringExtra("ID");
        System.out.println("taskIdStr: " + taskIdStr);

        if (taskIdStr != null) {
            titleET = findViewById(R.id.task_title);
            descriptionET = findViewById(R.id.task_description);
            taskCB = findViewById(R.id.task_checkBox);
            dateB = findViewById(R.id.task_date);

            taskId = Integer.parseInt(taskIdStr);

            Task task = Database.getDatabase(getApplicationContext()).taskDao().getById(taskId);

            titleET.setText(task.getTitle());
            descriptionET.setText(task.getDescription());
            dateB.setText(task.getDateString());
            taskCB.setChecked(task.getDone());
            editTask = true;
        }

        dateB = findViewById(R.id.task_date);
        dateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this, AddTaskActivity.this, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void save(View view) throws ParseException {
        titleET = findViewById(R.id.task_title);
        descriptionET = findViewById(R.id.task_description);
        taskCB = findViewById(R.id.task_checkBox);

        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();
        boolean status = taskCB.isChecked();

        if (title == null || title.isEmpty()) {
            Toast.makeText(this, R.string.errorTitleRequired, Toast.LENGTH_SHORT).show();
        } else {
            if (dateB.getText().toString().isEmpty()) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = df.parse(strDate);

                System.out.println(date);

                Task task = new Task(title, description, date, status);
                saveTask(task, editTask);
            } else {
                Task task = new Task(title, description, status);
                saveTask(task, editTask);
            }
            finish();
//            startActivity(new Intent(AddTaskActivity.this, ToDoTasks.class));
        }
    }

    public void saveTask(Task task, boolean editTask) {
        if (editTask) {
            task.setId(taskId);
            Task.updateTask(task, this);
            Toast.makeText(this, R.string.addTaskTaskEdited, Toast.LENGTH_SHORT).show();

        } else {
            Task.addTask(task, this);
            Toast.makeText(this, R.string.addTaskTaskAdded, Toast.LENGTH_SHORT).show();
        }
    }

    public void setStatus(View view) {
        CheckBox taskCB = findViewById(R.id.task_checkBox);
        boolean status = taskCB.isChecked();


        System.out.println(status);
    }

    public void cancel(View view) {
        finish();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myDay = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddTaskActivity.this, AddTaskActivity.this, hour, minute, true);
        System.out.println(hour + minute);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        strDate = String.format("%02d/%02d/%04d %02d:%02d", myDay, myMonth, myYear, myHour, myMinute);
        dateB.setText(strDate);
    }
}

