package com.example.gr11today.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr11today.R;
import com.example.gr11today.models.Label;

import java.util.List;

public class LabelRowAdapter extends RecyclerView.Adapter<LabelRowAdapter.ViewHolder> {
    private List<Label> data;
    private boolean hideDelete;

    public LabelRowAdapter(List<Label> data, boolean hideDelete) {
        if (hideDelete) {
            data.add(new Label("<Remove filter>", 0));
        }
        this.data = data;
        this.hideDelete = hideDelete;
    }

    @NonNull
    @Override
    public LabelRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.label_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Label label = data.get(position);
        System.out.println("Name: " + label.getName());
        System.out.println("ID: " + label.getLabelId());
        holder.labelName.setText(label.getName());
        holder.row.setTag(label.getLabelId());

        if (hideDelete) {
            holder.deleteLabel.setVisibility(View.GONE);
        } else {
            holder.deleteLabel.setTag(label.getLabelId());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView labelName;
        private Button deleteLabel;
        private LinearLayout row;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            labelName = itemView.findViewById(R.id.label_name);
            deleteLabel = itemView.findViewById(R.id.label_delete_button);
            row = itemView.findViewById(R.id.label_row_layout_id);

        }
    }
}
