package com.example.vitea.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vitea.R
import com.example.vitea.databinding.FragmentTimeTableBinding
import com.example.vitea.models.ApiResult
import com.example.vitea.utils.PreferenceHelper.get
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TimeTableFragment : Fragment() {

    private var dayList =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }
    @Inject lateinit var prefs: SharedPreferences
    private var _binding: FragmentTimeTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class DayAdapter(
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 7
        override fun createFragment(position: Int) = DayFragment.newInstance(position)
    }
}