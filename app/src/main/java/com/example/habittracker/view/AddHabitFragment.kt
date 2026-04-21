package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitListViewModel
import java.util.UUID

class AddHabitFragment : Fragment() {
    private lateinit var binding: FragmentAddHabitBinding
    private lateinit var viewModel: HabitListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitListViewModel::class.java)

        binding.btnAddHabitSubmit.setOnClickListener {
            val title = binding.txtAddTitle.text.toString()
            val desc = binding.txtAddDesc.text.toString()
            val pointsStr = binding.txtAddPoints.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty() && pointsStr.isNotEmpty()) {
                val newHabit = Habit(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = desc,
                    points = pointsStr.toInt()
                )

                viewModel.addHabit(newHabit)

                Toast.makeText(context, "Habit Added!", Toast.LENGTH_SHORT).show()

                Navigation.findNavController(it).popBackStack()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}