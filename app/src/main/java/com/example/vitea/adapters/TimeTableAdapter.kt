package com.example.vitea.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.vitea.R
import com.example.vitea.models.timetable.Lecture
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.timetable_item.*

class TimeTableAdapter(
    private val listener: (Lecture) -> Unit
) : RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder>() {
    private var timeTable: List<Lecture> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TimeTableViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
    )

    override fun getItemCount() = timeTable.size

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        val lecture = timeTable[position]

        holder.containerView.setOnClickListener {
            listener(lecture)
        }

        holder.subjectText.text = lecture.course
        holder.timeText.text = "${lecture.inTime} - ${lecture.outTime}"
        holder.slotText.text = lecture.slot
        holder.percentText.text = "${lecture.attendance}%"
        when (lecture.courseType) {
            "ELA" -> {
                holder.lectureType.setImageResource(R.drawable.ic_lab)
            }
            else -> {
                holder.lectureType.setImageResource(R.drawable.ic_theory)
            }
        }

        when (lecture.attendance) {
            in 90..100 -> {
                holder.percentText.setTextColor(ContextCompat.getColor(holder.percentText.context, R.color.neon_green))
            }
            in 75..90 -> {
                holder.percentText.setTextColor(
                    ContextCompat.getColor(
                        holder.percentText.context,
                        R.color.pastel_yellow
                    )
                )
            }
            else -> {
                holder.percentText.setTextColor(ContextCompat.getColor(holder.percentText.context, R.color.pastel_red))
            }
        }
    }

    class TimeTableViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer

    fun updateData(newData: List<Lecture>) {
        timeTable = newData
        notifyDataSetChanged()
    }
}

