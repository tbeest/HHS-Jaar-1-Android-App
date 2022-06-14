package com.example.gr11today.task;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gr11today.R;
import com.example.gr11today.models.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button button;
    int day, month, year, hour, minute;
    int myDay, myMonth, myYear, myHour, myMinute;
    String strDate;

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this, AddTaskActivity.this, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void save(View view) throws ParseException {
        EditText titleET = findViewById(R.id.task_title);
        EditText descriptionET = findViewById(R.id.task_description);

        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();

        if (title == null || title.isEmpty()) {
            Toast.makeText(this, R.string.errorTitleRequired, Toast.LENGTH_SHORT).show();
        } else {
            Date date = null;
            if (myYear > 0) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                date = df.parse(strDate);

                System.out.println(date);
            }


            System.out.println(description);
            System.out.println(title);

            Task task = new Task(title, description, date);
            task.addTask(task, this);

            System.out.println(task);
            Toast.makeText(this, "Added Task", Toast.LENGTH_SHORT).show();
            finish();
//            startActivity(new Intent(AddTaskActivity.this, ToDoTasks.class));
        }
    }

    public void setStatus(View view) {
        CheckBox taskCB = findViewById(R.id.taskCheckBox);
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
        button.setText(strDate);
    }
}

