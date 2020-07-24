package com.example.vitea.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vitea.R
import com.example.vitea.models.ApiResult
import com.example.vitea.utils.PreferenceHelper.get
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_time_table.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TimeTableFragment : Fragment() {

    private var dayList = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    private val viewModel by sharedViewModel<MainViewModel>()
    private val prefs by inject<SharedPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_time_table, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter =
            DayAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = dayList[position]
        }.attach()

        viewModel.timeTable.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    viewModel.getTimeTable(prefs["reg_no", "17BEE0001"]!!)
                }

                else -> {}
            }
        })
    }

    class DayAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 5
        override fun createFragment(position: Int) = DayFragment.newInstance(position)
    }
}