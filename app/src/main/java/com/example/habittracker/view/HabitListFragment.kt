package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.databinding.FragmentHabitListBinding
import com.example.habittracker.viewmodel.HabitListViewModel

class HabitListFragment : Fragment() {
    private lateinit var viewModel: HabitListViewModel
    private val habitListAdapter = HabitListAdapter(arrayListOf())
    private lateinit var binding: FragmentHabitListBinding

    override fun onCreateView(
        LayoutInflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitListViewModel::class.java)
        viewModel.refresh()

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitListAdapter

        binding.fabAddHabit.setOnClickListener {
            val action = HabitListFragmentDirections.actionHabitListFragmentToAddHabitFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer { habits ->
            habits?.let {
                binding.recViewHabit.visibility = View.VISIBLE
                habitListAdapter.updateHabitList(it)
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                binding.recViewHabit.visibility = View.GONE
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.habitLoadErrorLD.observe(viewLifecycleOwner, Observer { isError ->
            binding.txtError.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }
}