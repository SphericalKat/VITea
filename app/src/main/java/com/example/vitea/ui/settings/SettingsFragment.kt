package com.example.vitea.ui.settings

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.vitea.R
import com.example.vitea.models.ApiResult
import com.example.vitea.receivers.AlarmReceiver
import com.example.vitea.ui.home.MainViewModel
import com.example.vitea.utils.PreferenceHelper.get
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    @Inject lateinit var alarmManager: AlarmManager
    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }
    private lateinit var intent: Intent

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_prefs, rootKey)
        intent = Intent(requireContext(), AlarmReceiver::class.java)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
//        if (prefs?.get("alarms", false)!!) {
//            viewModel.timeTable.observe(viewLifecycleOwner, Observer {
//                when (it.status) {
//                    ApiResult.Status.SUCCESS -> {
////                                if (!it.data?.timeTable?.MON.isNullOrEmpty()) {
////                                    it.data?.timeTable?.MON?.forEach { lec ->
////                                        lec.
////                                    }
////                                }
//                        val pendingIntent =
//                            PendingIntent.getBroadcast(requireContext(), 0, intent, 0)
//                        Toast.makeText(requireContext(), "SETTING ALARM", Toast.LENGTH_SHORT).show()
//                        alarmManager.setRepeating(
//                            AlarmManager.RTC_WAKEUP,
//                            TimeUnit.SECONDS.toMillis(10),
//                            TimeUnit.SECONDS.toMillis(5),
//                            pendingIntent
//                        )
//                    }
//                    else -> {
//                    }
//                }
//            })
//        }
//    }
}