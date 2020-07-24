package com.example.vitea.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitea.R
import com.example.vitea.adapters.TimeTableAdapter
import com.example.vitea.models.ApiResult
import kotlinx.android.synthetic.main.fragment_time_table.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TimeTableFragment : Fragment() {
    private val timeTableAdapter by lazy {
        TimeTableAdapter(requireContext()) {
            val action =
                TimeTableFragmentDirections
                    .actionTimeTableFragmentToAttendanceFragment(it.getAttendanceDetails.toTypedArray())
            findNavController().navigate(action)
        }
    }
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_time_table, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timeTableRecyclerview.apply {
            adapter = timeTableAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        (requireActivity() as MainActivity).showProgressDialog()
        viewModel.timeTable.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    viewModel.getTimeTable("17BEE0054")
                }

                ApiResult.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                ApiResult.Status.SUCCESS -> {
                    (requireActivity() as MainActivity).hideProgressDialog()
                    timeTableAdapter.updateData(it.data?.timeTable?.FRI!!)
                }
            }
        })
    }
}