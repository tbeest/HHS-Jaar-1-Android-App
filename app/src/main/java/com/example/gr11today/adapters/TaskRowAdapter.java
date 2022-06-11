package com.example.gr11today.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr11today.R;
import com.example.gr11today.models.Task;

import java.util.List;

public class TaskRowAdapter extends RecyclerView.Adapter<TaskRowAdapter.ViewHolder> {
    private List<Task> data;

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
        String date = task.getDateString() != null ?
                task.getDateString() :
                null;
        holder.taskDate.setText(date);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView taskTitle;
        private TextView taskDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskRow_task_title);
            taskDate = itemView.findViewById(R.id.taskRow_task_date);
        }
    }
}