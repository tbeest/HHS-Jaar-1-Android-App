package com.example.gr11today.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr11today.R;
import com.example.gr11today.TaskValidator;
import com.example.gr11today.models.Task;

import java.util.List;

public class TaskRowAdapter extends RecyclerView.Adapter<TaskRowAdapter.ViewHolder> {
    private List<Task> data;
    private TaskValidator tv = new TaskValidator();

    public TaskRowAdapter(List<Task> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = data.get(position);
        holder.taskTitle.setText(task.getTitle());
        String date = task.getDateString();
        holder.taskDate.setText(date);
        holder.taskDone.setChecked(task.getDone());
        if (task.getLabel() != null) {
            holder.label.setText(task.getLabel().getName());
        }
        holder.row.setTag(task.getTaskId());
        holder.taskDone.setTag(task.getTaskId());

        System.out.println("Task ID of row:" + task.getTaskId());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView taskTitle;
        private TextView taskDate;
        private CheckBox taskDone;
        private LinearLayout row;
        private TextView label;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskDate = itemView.findViewById(R.id.task_date);
            taskDone = itemView.findViewById(R.id.task_checkBox);
            row = itemView.findViewById(R.id.task_row_layout_id);
            label = itemView.findViewById(R.id.task_label);

        }
    }
}