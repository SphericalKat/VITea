package com.example.vitea.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vitea.R
import com.example.vitea.models.ApiResult
import com.example.vitea.utils.PreferenceHelper.get
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_time_table.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TimeTableFragment : Fragment() {

    private var dayList = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }
    @Inject lateinit var prefs: SharedPreferences

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
                    viewModel.getTimeTable(prefs["reg_no", "17BEE0001"].toString())
                }

                else -> {
//                    Toast.makeText(context, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        pager.post {
            val sdf = SimpleDateFormat("EEEE")
            val d = Date()
            val dayOfTheWeek: String = sdf.format(d)
            pager.setCurrentItem(dayList.indexOf(dayOfTheWeek), true)
        }
    }

    class DayAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 5
        override fun createFragment(position: Int) = DayFragment.newInstance(position)
    }
}