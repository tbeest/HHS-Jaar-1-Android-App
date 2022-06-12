package com.example.gr11today.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr11today.MainActivity;
import com.example.gr11today.R;
import com.example.gr11today.RegisterActivity;
import com.example.gr11today.adapters.TaskRowAdapter;
import com.example.gr11today.models.Task;
import com.example.gr11today.task.OpenedTask;

public class ClosedTasks extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;

    Button openTasksButtonId,closedTasksButtonId;
    TextView titleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        openTasksButtonId = findViewById(R.id.openTasksButton);
        closedTasksButtonId = findViewById(R.id.closedTasksButton);
        titleId = findViewById(R.id.taskTitleId);

        titleId.setText(R.string.toDoTitleDone);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });

        recyclerView = findViewById(R.id.tasks_list);
        TaskRowAdapter adapter = new TaskRowAdapter(Task.getAllClosed());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        openTasksButtonId.setOnClickListener(v -> startActivity(new Intent(ClosedTasks.this, ToDoTasks.class)));
    }

    public void alreadyOpen(View view){
        Toast.makeText(this, R.string.errorAlreadyHere, Toast.LENGTH_SHORT).show();
    }

    public void addTask(View view) {
        startActivity(new Intent(ClosedTasks.this, OpenedTask.class));
    }

    public void signOut(View view) {
        startActivity(new Intent(ClosedTasks.this, MainActivity.class));
    }
}