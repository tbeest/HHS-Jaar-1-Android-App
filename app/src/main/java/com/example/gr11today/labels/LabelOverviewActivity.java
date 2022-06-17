package com.example.gr11today.labels;

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

import com.example.gr11today.Database;
import com.example.gr11today.Login.LoginActivity;
import com.example.gr11today.R;
import com.example.gr11today.adapters.LabelRowAdapter;
import com.example.gr11today.models.Label;
import com.example.gr11today.models.User;
import com.example.gr11today.tasks.TaskOverviewActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LabelOverviewActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private List<Label> labels;

    private Button openTasksButtonId, closedTasksButtonId, labelButtonId;
    private TextView titleId;
    private FloatingActionButton addLabelButtonId, filterItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        openTasksButtonId = findViewById(R.id.openTasksButton);
        closedTasksButtonId = findViewById(R.id.closedTasksButton);
        labelButtonId = findViewById(R.id.labelButton);
        addLabelButtonId = findViewById(R.id.addTaskButton);
        filterItemButton = findViewById((R.id.filterTasksButton));
        filterItemButton.setVisibility(View.GONE);
        titleId = findViewById(R.id.taskTitleId);

        addLabelButtonId.setTooltipText("Add new label");
        addLabelButtonId.setContentDescription("Add new label");
//        For some reason setTooltipText doesn't work with R.string, whereas setText does. I'm keeping this here to let you know why this text is hardcoded.
//        addLabelButtonId.setTooltipText(R.string.toDoTitleLabel);
//        addLabelButtonId.setContentDescription(R.string.toDoTitleLabel);

        titleId.setText(R.string.toDoTitleLabel);

        labels = Label.getAll(this);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                labels.clear();
                labels.addAll(Label.getAll(getApplicationContext()));
                recyclerView.getAdapter().notifyDataSetChanged();
                System.out.println("Refresh");
            }
        });

        recyclerView = findViewById(R.id.tasks_list);
        LabelRowAdapter adapter = new LabelRowAdapter(labels, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        closedTasksButtonId.setOnClickListener(v -> goToTasks(true));
        openTasksButtonId.setOnClickListener(v -> goToTasks(false));
        addLabelButtonId.setOnClickListener(v -> launcher.launch(new Intent(this, AddLabelActivity.class)));
    }

    public void gotoEditLabel(View view) {
        Intent intent = new Intent(this, AddLabelActivity.class);

        Integer labelId = (Integer) view.getTag();
        String labelIdStr = labelId + "";

        intent.putExtra("ID", labelIdStr);

        launcher.launch(intent);
    }

    public void goToTasks(boolean done) {
        Intent intent = new Intent(this, TaskOverviewActivity.class);

        intent.putExtra("DONE", done);

        startActivity(intent);
    }

    public void deleteLabel(View view) {
        Integer labelId = (Integer) view.getTag();

        Label label = Database.getDatabase(getApplicationContext()).labelDao().getById(labelId, User.getActiveUser().getUserId());

        Label.deleteLabel(label, this);
        Toast.makeText(this, R.string.addLabelLabelDelete, Toast.LENGTH_SHORT).show();

        labels.clear();
        labels.addAll(Label.getAll(getApplicationContext()));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void signOut(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
