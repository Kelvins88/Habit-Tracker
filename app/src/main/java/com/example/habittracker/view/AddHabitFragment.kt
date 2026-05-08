package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitListViewModel
import java.util.UUID

class AddHabitFragment : Fragment() {
    private lateinit var binding: FragmentAddHabitBinding
    private lateinit var viewModel: HabitListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HabitListViewModel::class.java)

        val iconList = arrayOf("Baca", "Fitness", "Meditasi", "Water")

        val dropdownAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, iconList)
        binding.txtAddIcon.setAdapter(dropdownAdapter)

        binding.btnAddHabitSubmit.setOnClickListener {
            val title = binding.txtAddTitle.text.toString()
            val desc = binding.txtAddDesc.text.toString()
            val goalStr = binding.txtAddGoal.text.toString()
            val unit = binding.txtAddUnit.text.toString()
            val selectedIcon = binding.txtAddIcon.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty() && goalStr.isNotEmpty() && unit.isNotEmpty() && selectedIcon.isNotEmpty()) {
                val newHabit = Habit(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = desc,
                    goal = goalStr.toInt(),
                    unit = unit,
                    currentProgress = 0,
                    photoUrl = selectedIcon
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