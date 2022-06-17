package com.example.gr11today.labels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gr11today.Database;
import com.example.gr11today.R;
import com.example.gr11today.TaskValidator;
import com.example.gr11today.models.Label;

public class AddLabelActivity extends AppCompatActivity {

    private int taskId;
    private boolean editLabel = false;
    private int labelId;
    private String labelIdStr, name;

    private TaskValidator tv = new TaskValidator();
    private EditText nameET, descriptionET;
    private Button cancelB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        Intent intent = getIntent();
        labelIdStr = intent.getStringExtra("ID");

        if (tv.stringNotEmpty(labelIdStr)) {
            nameET = findViewById(R.id.label_name);

            labelId = Integer.parseInt(labelIdStr);

            Label label = Database.getDatabase(getApplicationContext()).labelDao().getById(labelId);

            nameET.setText(label.getName());
            editLabel = true;
        }

        cancelB = findViewById(R.id.label_cancel_button);
        cancelB.setOnClickListener(v -> finish());
    }

    public void save(View view) {
        nameET = findViewById(R.id.label_name);

        name = nameET.getText().toString();

        if (!tv.stringNotEmpty(name)) {
            Toast.makeText(this, R.string.errorLabelNameRequired, Toast.LENGTH_SHORT).show();
        } else {
            Label label = new Label(name);
            saveLabel(label, editLabel);
        }
    }

    public void saveLabel(Label label, boolean editLabel) {
        if (editLabel) {
            label.setLabelId(labelId);
            label.updateLabel(label, this);
            Toast.makeText(this, R.string.addLabelLabelEdited, Toast.LENGTH_SHORT).show();

        } else {
            Label.addLabel(label, this);
            Toast.makeText(this, R.string.addLabelLabelAdded, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void deleteLabel(View view) {
        if (labelId > 0) {
            Label label = Database.getDatabase(getApplicationContext()).labelDao().getById(labelId);
            Label.deleteLabel(label, this);
        }
        Toast.makeText(this, R.string.addLabelLabelDelete, Toast.LENGTH_SHORT).show();
        finish();
    }
}
