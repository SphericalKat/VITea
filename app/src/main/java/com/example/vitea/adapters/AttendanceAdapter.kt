package com.example.vitea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.vitea.R
import com.example.vitea.models.timetable.GetAttendanceDetails
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.attendance_item.*

class AttendanceAdapter :
    RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {
    private var attendanceDetails: Array<GetAttendanceDetails> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AttendanceViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.attendance_item, parent, false)
    )

    override fun getItemCount() = attendanceDetails.size

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val item = attendanceDetails[position]
        holder.attendanceSlotText.text = item.attendanceSlot
        holder.attendanceDateText.text = item.attendanceDate
        holder.attendanceDayText.text = item.attendanceDay
        holder.attendanceTimeText.text = item.classTime
        holder.isPresentText.apply {
            text = item.attendanceStatus
            val color =
                if (item.attendanceStatus == "Present") R.color.neon_green else R.color.pastel_red
            setTextColor(ContextCompat.getColor(context, color))
        }
    }

    fun updateData(newData: Array<GetAttendanceDetails>) {
        attendanceDetails = newData
        notifyDataSetChanged()
    }

    class AttendanceViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer
}