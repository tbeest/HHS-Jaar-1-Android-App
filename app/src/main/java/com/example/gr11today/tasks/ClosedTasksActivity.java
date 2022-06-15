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

import com.example.gr11today.MainActivity;
import com.example.gr11today.R;
import com.example.gr11today.adapters.TaskRowAdapter;
import com.example.gr11today.models.Task;
import com.example.gr11today.task.AddTaskActivity;

public class ClosedTasksActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;

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

        startActivity(intent);
    }

    public void setStatus(View view) {
        CheckBox taskCB = findViewById(R.id.task_checkBox);
        boolean status = taskCB.isChecked();


        System.out.println(status);
    }

    public void alreadyOpen(View view) {
        Toast.makeText(this, R.string.errorAlreadyHere, Toast.LENGTH_SHORT).show();
    }

    public void addTask(View view) {
        startActivity(new Intent(ClosedTasksActivity.this, AddTaskActivity.class));
    }

    public void signOut(View view) {
        startActivity(new Intent(ClosedTasksActivity.this, MainActivity.class));
    }
}