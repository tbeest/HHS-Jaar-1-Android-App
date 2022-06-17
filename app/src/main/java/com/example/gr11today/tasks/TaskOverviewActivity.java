package com.example.gr11today.tasks;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gr11today.Database;
import com.example.gr11today.MainActivity;
import com.example.gr11today.R;
import com.example.gr11today.TaskValidator;
import com.example.gr11today.adapters.TaskRowAdapter;
import com.example.gr11today.labels.LabelOverviewActivity;
import com.example.gr11today.labels.SelectLabelActivity;
import com.example.gr11today.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskOverviewActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private List<Task> tasks;
    private int labelId;
    private boolean done = false;

    private Button openTasksButtonId, closedTasksButtonId, labelButtonId;
    private TextView titleId;
    private FloatingActionButton filterButtonId;

    private TaskValidator tv = new TaskValidator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        openTasksButtonId = findViewById(R.id.openTasksButton);
        closedTasksButtonId = findViewById(R.id.closedTasksButton);
        labelButtonId = findViewById(R.id.labelButton);
        titleId = findViewById(R.id.taskTitleId);
        filterButtonId = findViewById(R.id.filterTasksButton);


        tasks = Task.getAll(this, done, labelId);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                tasks.clear();
                tasks.addAll(Task.getAll(getApplicationContext(), done, labelId));
                recyclerView.getAdapter().notifyDataSetChanged();
                System.out.println("Refresh");
            }
        });

        recyclerView = findViewById(R.id.tasks_list);
        TaskRowAdapter adapter = new TaskRowAdapter(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle resultIntent = getIntent().getExtras();
        if(resultIntent != null) {
            done = resultIntent.getBoolean("DONE");
        }
        setDone(done);

        closedTasksButtonId.setOnClickListener(v -> setDone(true));
        openTasksButtonId.setOnClickListener(v -> setDone(false));
        labelButtonId.setOnClickListener(v -> startActivity(new Intent(this, LabelOverviewActivity.class)));
        filterButtonId.setOnClickListener(v -> selectFilter(v));
    }

    @Override
    public void onBackPressed() {
    }

    public void selectFilter(View view) {
        Intent intent = new Intent(this, SelectLabelActivity.class);

        launcher.launch(intent);
    }

    public void gotoEditTask(View view) {
        Intent intent = new Intent(TaskOverviewActivity.this, AddTaskActivity.class);

        Integer taskId = (Integer) view.getTag();
        String taskIdStr = taskId + "";

        System.out.println("Passed task ID str: " + taskIdStr);

        intent.putExtra("ID", taskIdStr);

        launcher.launch(intent);
    }

    public void setDone(boolean done) {
        this.done = done;

        if (done) {
            titleId.setText(R.string.toDoTitleDone);
        } else {
            titleId.setText(R.string.toDoTitleToDo);
        }

        tasks.clear();
        tasks.addAll(Task.getAll(getApplicationContext(), done, labelId));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void setStatus(View view) {
        CheckBox taskCB = view.findViewById(R.id.task_checkBox);
        Integer taskId = (Integer) view.getTag();
        System.out.println("taskId checkbox: " + taskId);
        Task task = Database.getDatabase(getApplicationContext()).taskDao().getById(taskId);
        Boolean done = taskCB.isChecked();
        task.setDone(done);

        if (done) {
            Toast.makeText(this, R.string.openTasksMarkedDone, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.openTasksMarkedOpen, Toast.LENGTH_SHORT).show();
        }

        Task.updateTask(task, this);

        tasks.clear();
        tasks.addAll(Task.getAll(getApplicationContext(), done, labelId));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void alreadyOpen(View view) {
        Toast.makeText(this, R.string.errorAlreadyHere, Toast.LENGTH_SHORT).show();
    }

    public void addTask(View view) {
        launcher.launch(new Intent(TaskOverviewActivity.this, AddTaskActivity.class));
    }

    public void signOut(View view) {
        startActivity(new Intent(TaskOverviewActivity.this, MainActivity.class));
    }
}