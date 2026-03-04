package com.talento.patronesdearquitecturaandroid.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talento.patronesdearquitecturaandroid.databinding.ItemTaskBinding;
import com.talento.patronesdearquitecturaandroid.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public interface OnTaskCheckedChangeListener {
        void onTaskCheckedChanged(int position, boolean isChecked);
    }

    private final OnTaskCheckedChangeListener listener;
    private List<Task> taskList = new ArrayList<>();

    public TaskAdapter(OnTaskCheckedChangeListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTaskBinding binding = ItemTaskBinding.inflate(inflater, parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void submitList(List<Task> updatedList) {
        taskList = updatedList == null ? new ArrayList<>() : new ArrayList<>(updatedList);
        notifyDataSetChanged();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final ItemTaskBinding binding;

        TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Task task) {
            binding.setTask(task);
            binding.completedCheckBox.setOnCheckedChangeListener(null);
            binding.completedCheckBox.setChecked(task.isCompleted());
            binding.completedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int adapterPosition = getBindingAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && listener != null) {
                    listener.onTaskCheckedChanged(adapterPosition, isChecked);
                }
            });
            binding.executePendingBindings();
        }
    }
}
