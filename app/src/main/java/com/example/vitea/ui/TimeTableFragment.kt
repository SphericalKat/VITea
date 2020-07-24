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
    private val linearLayoutManager by lazy { LinearLayoutManager(requireContext()) }
    private val timeTableAdapter by lazy {
        TimeTableAdapter(requireContext()) {
            val action = TimeTableFragmentDirections.actionTimeTableFragmentToAttendanceFragment()
            findNavController().navigate(action)
        }
    }
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timeTableRecyclerview.apply {
            adapter = timeTableAdapter
            layoutManager = linearLayoutManager
        }
        viewModel.getTimeTable("17BEE0054")
        (requireActivity() as MainActivity).showProgressDialog()
        viewModel.timeTable.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ApiResult.Status.LOADING -> {

                }

                ApiResult.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                }

                ApiResult.Status.SUCCESS -> {
                    (requireActivity() as MainActivity).hideProgressDialog()
                    timeTableAdapter.updateData(it.data?.timeTable?.FRI!!)
                }
            }
        })
    }
}