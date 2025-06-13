package com.example.assignment_4.taskapp.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_4.databinding.ActivityMainBinding
import com.example.assignment_4.taskapp.adapter.TaskAdapter
import com.example.assignment_4.taskapp.viewmodel.TaskViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TaskAdapter(emptyList()) { taskId ->
            viewModel.removeTask(taskId)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val taskText = binding.etTask.text.toString()
            viewModel.addTask(taskText)
            binding.etTask.text.clear()
        }

        binding.btnClearCompleted.setOnClickListener {
            viewModel.clearCompleted()
        }

        viewModel.tasks.observe(this) { taskList ->
            adapter.updateTasks(taskList)
            val total = taskList.size
            binding.taskStats.text = "$total tasks | ${taskList.count()} remaining"

            binding.tvEmptyState.visibility =
                if (taskList.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }
}
