package com.example.gr11today.tasks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gr11today.Database;
import com.example.gr11today.R;
import com.example.gr11today.TaskValidator;
import com.example.gr11today.models.Label;
import com.example.gr11today.models.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    int day, month, year, hour, minute;
    int myDay, myMonth, myYear, myHour, myMinute;
    String strDate, title, description;
    int taskId;
    boolean editTask = false;

    EditText titleET, descriptionET;
    CheckBox taskCB;
    TaskValidator tv = new TaskValidator();
    Button dateB;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        spinner = findViewById(R.id.add_task_label_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Label.getAllNames(this));
        System.out.println("Adapter: " + adapter.getClass());
        spinner.setAdapter(adapter);

        System.out.println("OnCreate AddTaskActivity");

        Intent intent = getIntent();
        String taskIdStr = intent.getStringExtra("ID");
        System.out.println("taskIdStr: " + taskIdStr);

        if (tv.stringNotEmpty(taskIdStr)) {
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

        title = titleET.getText().toString();
        description = descriptionET.getText().toString();
        boolean status = taskCB.isChecked();

        if (!tv.stringNotEmpty(title)) {
            Toast.makeText(this, R.string.errorTitleRequired, Toast.LENGTH_SHORT).show();
        } else {
            if (tv.stringNotEmpty(dateB.getText().toString())) {
                String dateString = dateB.getText().toString();
                System.out.println("DateB: " + dateString);
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
                Date date = df.parse(dateString);

                System.out.println(date);

                System.out.println("Adding date");
                Task task = new Task(title, description, status, date);
                saveTask(task, editTask);
            } else {
                System.out.println("Not adding date");
                Task task = new Task(title, description, status);
                saveTask(task, editTask);
            }
            finish();
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

    public void deleteTask(View view) {
        if (taskId > 0) {
            Task task = Database.getDatabase(getApplicationContext()).taskDao().getById(taskId);
            Task.deleteTask(task, this);
        }
        Toast.makeText(this, R.string.addTaskTaskDelete, Toast.LENGTH_SHORT).show();
        finish();
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
        strDate = String.format("%02d-%02d-%04d  %02d:%02d", myDay, myMonth, myYear, myHour, myMinute);
        dateB.setText(strDate);
    }
}

