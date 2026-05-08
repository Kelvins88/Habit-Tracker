package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.databinding.FragmentHabitListBinding
import com.example.habittracker.viewmodel.HabitListViewModel

class HabitListFragment : Fragment() {
    private lateinit var viewModel: HabitListViewModel
    private lateinit var habitListAdapter: HabitListAdapter
    private lateinit var binding: FragmentHabitListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHabitListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HabitListViewModel::class.java)

        habitListAdapter = HabitListAdapter(arrayListOf(), viewModel)
        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitListAdapter

        binding.fabAddHabit.setOnClickListener {
            val action = HabitListFragmentDirections.actionHabitListFragmentToAddHabitFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        observeViewModel()
        viewModel.refresh()
    }

    private fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner) { habits ->
            habits?.let {
                binding.recViewHabit.visibility = View.VISIBLE
                habitListAdapter.updateHabitList(it)
            }
        }
        viewModel.loadingLD.observe(viewLifecycleOwner) { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE

            if (!isLoading) {
                binding.refreshLayout.isRefreshing = false
            }
        }
    }
}