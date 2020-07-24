package com.example.vitea.ui.home

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
import com.example.vitea.models.timetable.Lecture
import kotlinx.android.synthetic.main.fragment_day.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

private const val ARG_DAY_INDEX = "day"

/**
 * A simple [Fragment] subclass.
 * Use the [DayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DayFragment : Fragment() {
    private var day: Int? = null
    private val viewModel by sharedViewModel<MainViewModel>()
    private val timeTableAdapter by lazy {
        TimeTableAdapter(requireContext()) {
            val action =
                TimeTableFragmentDirections.actionTimeTableFragmentToAttendanceFragment(
                    it.getAttendanceDetails.toTypedArray()
                )
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getInt(ARG_DAY_INDEX)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timeTableRecyclerview.apply {
            adapter = timeTableAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        (requireActivity() as MainActivity).showProgressDialog()
        viewModel.timeTable.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ApiResult.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                ApiResult.Status.SUCCESS -> {
                    (requireActivity() as MainActivity).hideProgressDialog()
                    if (!it.data?.timeTable?.MON.isNullOrEmpty()) {
                        val dayData: List<Lecture> = when (day) {
                            0 -> it.data?.timeTable?.MON!!
                            1 -> it.data?.timeTable?.TUE!!
                            2 -> it.data?.timeTable?.WED!!
                            3 -> it.data?.timeTable?.THU!!
                            4 -> it.data?.timeTable?.FRI!!
                            else -> it.data?.timeTable?.MON!!
                        }

                        timeTableAdapter.updateData(dayData)
                    }
                }
                else -> {
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param day Parameter 1.
         * @return A new instance of fragment DayFragment.
         */
        @JvmStatic
        fun newInstance(day: Int) =
            DayFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DAY_INDEX, day)
                }
            }
    }
}