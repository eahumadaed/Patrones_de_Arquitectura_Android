package com.talento.patronesdearquitecturaandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talento.patronesdearquitecturaandroid.databinding.ItemTaskBinding
import com.talento.patronesdearquitecturaandroid.model.Task

class TaskAdapter(
    private val onTaskCheckedChanged: (position: Int, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var taskList: List<Task> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    fun submitList(updatedList: List<Task>?) {
        taskList = updatedList?.toList() ?: emptyList()
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task = task
            binding.completedCheckBox.setOnCheckedChangeListener(null)
            binding.completedCheckBox.isChecked = task.isCompleted
            binding.completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                val adapterPosition = bindingAdapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onTaskCheckedChanged(adapterPosition, isChecked)
                }
            }
            binding.executePendingBindings()
        }
    }
}
