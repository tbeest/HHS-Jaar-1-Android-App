package com.example.gr11today.labels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr11today.R;
import com.example.gr11today.adapters.LabelRowAdapter;
import com.example.gr11today.models.Label;
import com.example.gr11today.tasks.TaskOverviewActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SelectLabelActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private List<Label> labels;

    Button deleteButtonId;
    LinearLayout bottomNavId;
    TextView titleId;
    FloatingActionButton addLabelButtonId, filterItemButtonId, signOutButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        bottomNavId = findViewById(R.id.linearLayoutBottomNav);
        bottomNavId.setVisibility(View.GONE);

        addLabelButtonId = findViewById(R.id.addTaskButton);
        addLabelButtonId.setVisibility(View.GONE);
        filterItemButtonId = findViewById((R.id.filterTasksButton));
        filterItemButtonId.setVisibility(View.GONE);
        signOutButtonId = findViewById(R.id.signOutButton);
        signOutButtonId.setVisibility(View.GONE);

        titleId = findViewById(R.id.taskTitleId);
        titleId.setText(R.string.toDoTitleSelectLabel);

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
        LabelRowAdapter adapter = new LabelRowAdapter(labels, true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        deleteButtonId = findViewById(R.id.label_delete_button);
        System.out.println(deleteButtonId);
        if (deleteButtonId != null) {
            deleteButtonId.setVisibility(View.GONE);
        }
    }

    public void gotoEditLabel(View view) {
        Intent intent = new Intent(this, TaskOverviewActivity.class);

        Integer labelId = (Integer) view.getTag();
        String labelIdStr = labelId + "";

        intent.putExtra("ID", labelIdStr);

        launcher.launch(intent);
    }
}
