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

import com.example.gr11today.MainActivity;
import com.example.gr11today.R;
import com.example.gr11today.adapters.TaskRowAdapter;
import com.example.gr11today.models.Task;
import com.example.gr11today.task.AddTaskActivity;

import java.util.List;

public class ToDoTasks extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private List<Task> tasks;

    Button openTasksButtonId, closedTasksButtonId;
    TextView titleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        openTasksButtonId = findViewById(R.id.openTasksButton);
        closedTasksButtonId = findViewById(R.id.closedTasksButton);
        titleId = findViewById(R.id.taskTitleId);

        titleId.setText(R.string.toDoTitleToDo);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                tasks.clear();
                tasks.addAll(Task.getAll(getApplicationContext()));
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        tasks = Task.getAll(this);

        recyclerView = findViewById(R.id.tasks_list);
        TaskRowAdapter adapter = new TaskRowAdapter(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        closedTasksButtonId.setOnClickListener(v -> startActivity(new Intent(ToDoTasks.this, ClosedTasks.class)));
    }

    public void setStatus(View view) {
        CheckBox taskCB = findViewById(R.id.taskCheckBox);
        String status = taskCB.getText().toString();


        System.out.println(status);
    }

    public void alreadyOpen(View view) {
        Toast.makeText(this, R.string.errorAlreadyHere, Toast.LENGTH_SHORT).show();
    }

    public void addTask(View view) {
        startActivity(new Intent(ToDoTasks.this, AddTaskActivity.class));
    }

    public void signOut(View view) {
        startActivity(new Intent(ToDoTasks.this, MainActivity.class));
    }
}