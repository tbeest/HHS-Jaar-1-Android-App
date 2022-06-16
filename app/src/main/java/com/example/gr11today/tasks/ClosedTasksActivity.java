package com.example.gr11today.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr11today.Database;
import com.example.gr11today.MainActivity;
import com.example.gr11today.R;
import com.example.gr11today.adapters.TaskRowAdapter;
import com.example.gr11today.models.Task;
import com.example.gr11today.task.AddTaskActivity;

import java.util.List;

public class ClosedTasksActivity extends AppCompatActivity {

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

        titleId.setText(R.string.toDoTitleDone);

        tasks = Task.getAllOpen(this);


        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });

        recyclerView = findViewById(R.id.tasks_list);
        TaskRowAdapter adapter = new TaskRowAdapter(Task.getAllClosed(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        openTasksButtonId.setOnClickListener(v -> startActivity(new Intent(ClosedTasksActivity.this, OpenTasksActivity.class)));
    }

    public void gotoEditTask(View view) {
        Intent intent = new Intent(ClosedTasksActivity.this, AddTaskActivity.class);

        Integer taskId = (Integer) view.getTag();
        String taskIdStr = taskId + "";

        System.out.println("Passed task ID int:" + taskId + "Passed task ID str: " + taskIdStr);

        intent.putExtra("ID", taskIdStr);

        launcher.launch(intent);
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
        tasks.addAll(Task.getAllOpen(getApplicationContext()));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void alreadyOpen(View view) {
        Toast.makeText(this, R.string.errorAlreadyHere, Toast.LENGTH_SHORT).show();
    }

    public void addTask(View view) {
        launcher.launch(new Intent(ClosedTasksActivity.this, AddTaskActivity.class));
    }

    public void signOut(View view) {
        startActivity(new Intent(ClosedTasksActivity.this, MainActivity.class));
    }
}