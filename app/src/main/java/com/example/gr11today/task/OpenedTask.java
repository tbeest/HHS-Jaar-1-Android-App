package com.example.gr11today.task;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gr11today.R;
import com.example.gr11today.tasks.ToDoTasks;

import java.util.Calendar;


public class OpenedTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button button;
    int day, month, year, hour, minute;
    int myDay, myMonth, myYear, myHour, myMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        button = findViewById(R.id.task_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(OpenedTask.this, OpenedTask.this, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void save(View view) {
        EditText titleET = findViewById(R.id.task_title);
        EditText descriptionET = findViewById(R.id.task_description);

        String title = titleET.getText().toString();

        if (title == null || title.isEmpty()) {
            Toast.makeText(this, R.string.errorTitleRequired, Toast.LENGTH_SHORT).show();
        }

        System.out.println(title);
    }

    public void cancel(View view) {
        startActivity(new Intent(OpenedTask.this, ToDoTasks.class));
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myDay = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(OpenedTask.this, OpenedTask.this, hour, minute, true);
        System.out.println(hour + minute);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        button.setText(String.format("%02d/%02d/%d %02d:%02d", myDay, myMonth, myYear, myHour, myMinute));
    }
}

