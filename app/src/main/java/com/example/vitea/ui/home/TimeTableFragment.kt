package com.example.vitea.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vitea.R
import com.example.vitea.databinding.FragmentTimeTableBinding
import com.example.vitea.models.ApiResult
import com.example.vitea.utils.PreferenceHelper.get
import com.example.vitea.utils.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TimeTableFragment : Fragment(R.layout.fragment_time_table) {

    private var dayList =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val viewModel: MainViewModel by activityViewModels()
    @Inject lateinit var prefs: SharedPreferences
    private val binding by viewBinding(FragmentTimeTableBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settingsPage -> {
                val action = TimeTableFragmentDirections.actionTimeTableFragmentToSettingsFragment()
                findNavController().navigate(action)
                true
            }
            else -> false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter =
            DayAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = dayList[position]
        }.attach()

        viewModel.timeTable.observe(viewLifecycleOwner, {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    viewModel.getTimeTable(prefs["reg_no", "17BEE0001"].toString())
                }

                else -> {
//                    Toast.makeText(context, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.pager.post {
            if (viewModel.doOnce) {
                viewModel.doOnce = false
                val sdf = SimpleDateFormat("EEEE")
                val d = Date()
                val dayOfWeek = sdf.format(d)
                binding.pager.setCurrentItem(dayList.indexOf(dayOfWeek), true)
            }
        }
    }

    class DayAdapter(
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 7
        override fun createFragment(position: Int) = DayFragment.newInstance(position)
    }
}