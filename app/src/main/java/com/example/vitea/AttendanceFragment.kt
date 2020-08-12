package com.example.vitea

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitea.adapters.AttendanceAdapter
import com.example.vitea.models.timetable.GetAttendanceDetails
import kotlinx.android.synthetic.main.fragment_attendance.*

class AttendanceFragment : Fragment() {
    private val args by navArgs<AttendanceFragmentArgs>()
    private val attendanceAdapter by lazy { AttendanceAdapter() }
    private lateinit var attendanceDetails: Array<GetAttendanceDetails>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_attendance, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.attendance_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reverseList -> {
                attendanceDetails.reverse()
                attendanceAdapter.updateData(attendanceDetails)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attendanceDetails = args.attendanceDetails
        attendanceRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = attendanceAdapter
        }
        attendanceAdapter.updateData(attendanceDetails)
    }
}