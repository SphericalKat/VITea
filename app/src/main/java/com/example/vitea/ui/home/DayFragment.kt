package com.example.vitea.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitea.R
import com.example.vitea.adapters.TimeTableAdapter
import com.example.vitea.databinding.FragmentDayBinding
import com.example.vitea.models.ApiResult
import com.example.vitea.models.timetable.Lecture
import com.example.vitea.utils.hide
import com.example.vitea.utils.show
import com.example.vitea.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_DAY_INDEX = "day"

@AndroidEntryPoint
class DayFragment : Fragment(R.layout.fragment_day) {
    private var day: Int? = null
    private val viewModel: MainViewModel by activityViewModels()
    private val timeTableAdapter by lazy {
        TimeTableAdapter {
            val action =
                TimeTableFragmentDirections.actionTimeTableFragmentToAttendanceFragment(
                    it.getAttendanceDetails.toTypedArray()
                )
            findNavController().navigate(action)
        }
    }
    private val binding by viewBinding(FragmentDayBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getInt(ARG_DAY_INDEX)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timeTableRecyclerview.apply {
            adapter = timeTableAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        (requireActivity() as MainActivity).showProgressDialog()
        viewModel.timeTable.observe(viewLifecycleOwner, {
            when (it.status) {
                ApiResult.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                ApiResult.Status.SUCCESS -> {
                    (requireActivity() as MainActivity).hideProgressDialog()
                    val dayData: List<Lecture> = when (day) {
                        0 -> it.data?.timeTable?.MON ?: listOf()
                        1 -> it.data?.timeTable?.TUE ?: listOf()
                        2 -> it.data?.timeTable?.WED ?: listOf()
                        3 -> it.data?.timeTable?.THU ?: listOf()
                        4 -> it.data?.timeTable?.FRI ?: listOf()
                        5 -> it.data?.timeTable?.SAT ?: listOf()
                        else -> it.data?.timeTable?.SUN ?: listOf()
                    }
                    if (dayData.isNullOrEmpty()) {
                        binding.timeTableRecyclerview.hide()
                        binding.noItemsText.show()
                    }
                    timeTableAdapter.updateData(dayData)

                }
                else -> {
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(day: Int) =
            DayFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DAY_INDEX, day)
                }
            }
    }
}