package com.example.vitea.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.vitea.R
import com.example.vitea.databinding.AttendanceItemBinding
import com.example.vitea.models.timetable.GetAttendanceDetails

class AttendanceAdapter :
    RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {
    private var attendanceDetails: Array<GetAttendanceDetails> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val binding = AttendanceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttendanceViewHolder(binding)
    }

    override fun getItemCount() = attendanceDetails.size

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        holder.bind(attendanceDetails[position])
    }

    fun updateData(newData: Array<GetAttendanceDetails>) {
        attendanceDetails = newData
        notifyDataSetChanged()
    }

    class AttendanceViewHolder(private val binding: AttendanceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: GetAttendanceDetails) {
                binding.attendanceSlotText.text = item.attendanceSlot
                binding.attendanceDateText.text = item.attendanceDate
                binding.attendanceDayText.text = item.attendanceDay
                binding.attendanceTimeText.text = item.classTime
                binding.isPresentText.apply {
                    text = item.attendanceStatus

                    val color = when (item.attendanceStatus) {
                        "Present" -> R.color.neon_green
                        "On Duty" -> R.color.pastel_yellow
                        else -> R.color.pastel_red
                    }

                    setTextColor(ContextCompat.getColor(context, color))
                }
            }
        }
}