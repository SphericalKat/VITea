package com.example.vitea.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.vitea.R
import com.example.vitea.databinding.TimetableItemBinding
import com.example.vitea.models.timetable.Lecture

class TimeTableAdapter(
    private val listener: (Lecture) -> Unit
) : RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder>() {
    private var timeTable: List<Lecture> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding = TimetableItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeTableViewHolder(binding, listener)
    }

    override fun getItemCount() = timeTable.size

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.bind(timeTable[position])
    }

    class TimeTableViewHolder(
        private val binding: TimetableItemBinding,
        private val listener: (Lecture) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lecture: Lecture) {
            binding.root.setOnClickListener {
                listener(lecture)
            }


            binding.subjectText.text = lecture.course
            binding.timeText.text = "${lecture.inTime} - ${lecture.outTime}"
            binding.slotText.text = lecture.slot
            binding.percentText.text = "${lecture.attendance}%"
            when (lecture.courseType) {
                "ELA" -> {
                    binding.lectureType.setImageResource(R.drawable.ic_lab)
                }
                else -> {
                    binding.lectureType.setImageResource(R.drawable.ic_theory)
                }
            }

            when (lecture.attendance) {
                in 90..100 -> {
                    binding.percentText.setTextColor(
                        ContextCompat.getColor(
                            binding.percentText.context,
                            R.color.neon_green
                        )
                    )
                }
                in 75..90 -> {
                    binding.percentText.setTextColor(
                        ContextCompat.getColor(
                            binding.percentText.context,
                            R.color.pastel_yellow
                        )
                    )
                }
                else -> {
                    binding.percentText.setTextColor(
                        ContextCompat.getColor(
                            binding.percentText.context,
                            R.color.pastel_red
                        )
                    )
                }
            }
        }
    }

    fun updateData(newData: List<Lecture>) {
        timeTable = newData
        notifyDataSetChanged()
    }
}

