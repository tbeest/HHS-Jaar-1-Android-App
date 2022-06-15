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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gr11today.MainActivity;
import com.example.gr11today.R;
import com.example.gr11today.adapters.TaskRowAdapter;
import com.example.gr11today.models.Task;
import com.example.gr11today.task.AddTaskActivity;

import java.util.List;

public class OpenTasksActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private List<Task> tasks;

    Button openTasksButtonId, closedTasksButtonId;
    TextView titleId;
    LinearLayout rowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                tasks.clear();
                tasks.addAll(Task.getAllOpen(getApplicationContext()));
                recyclerView.getAdapter().notifyDataSetChanged();
                System.out.println("Refresh");
            }
        });

        LinearLayout rowId2 = findViewById(R.id.task_row_layout_id);
        if (rowId2 != null) {
            String taskIdStr = rowId2.getTag().toString();
            Integer taskId = Integer.parseInt(taskIdStr);
            System.out.println("OnCreate task ID:" + taskId);
        } else {
            System.out.println("OnCreate = null");
        }

        openTasksButtonId = findViewById(R.id.openTasksButton);
        closedTasksButtonId = findViewById(R.id.closedTasksButton);
        titleId = findViewById(R.id.taskTitleId);

        titleId.setText(R.string.toDoTitleToDo);

        tasks = Task.getAllOpen(this);

        recyclerView = findViewById(R.id.tasks_list);
        TaskRowAdapter adapter = new TaskRowAdapter(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        closedTasksButtonId.setOnClickListener(v -> startActivity(new Intent(OpenTasksActivity.this, ClosedTasksActivity.class)));
    }



    public void gotoEditTask(View view) {
        Intent intent = new Intent(OpenTasksActivity.this, AddTaskActivity.class);

        rowId = findViewById(R.id.task_row_layout_id);
        String taskIdStr = rowId.getTag().toString();
        Integer taskId = Integer.parseInt(taskIdStr);

        System.out.println("Passed task ID:" + taskId);

        titleId = findViewById(R.id.task_title);
        String title = titleId.getText().toString();

        TextView dateTV = findViewById(R.id.task_date);
        String strDate = dateTV.getText().toString();

        if (title != null || !title.isEmpty()) {
            intent.putExtra("ID", title);
        }

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
        startActivity(new Intent(OpenTasksActivity.this, AddTaskActivity.class));
    }

    public void signOut(View view) {
        startActivity(new Intent(OpenTasksActivity.this, MainActivity.class));
    }
}