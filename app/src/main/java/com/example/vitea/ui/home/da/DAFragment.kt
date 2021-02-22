package com.example.vitea.ui.home.da

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.vitea.R
import com.example.vitea.models.ApiResult
import com.example.vitea.ui.home.MainActivity
import com.example.vitea.ui.home.MainViewModel
import com.example.vitea.ui.themes.ViteaTheme
import com.example.vitea.ui.themes.typography
import com.example.vitea.utils.PreferenceHelper.get
import com.example.vitea.utils.StringDateComparator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DAFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    @Inject lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = requireActivity() as MainActivity

        return ComposeView(requireContext()).apply {
            setContent {
                DaFragmentView(prefs, mainActivity, viewModel)
            }
        }
    }
}

@Composable
fun DaFragmentView(prefs: SharedPreferences, mainActivity: MainActivity, viewModel: MainViewModel) {
    val regNo = prefs["reg_no", "20BCE0001"].toString()
    LaunchedEffect(prefs) {
        viewModel.getDa(regNo)
//        Log.d("TAG", "OWOOWOWOWOW")
    }

    ViteaTheme {
        when (viewModel.da.status) {
            ApiResult.Status.LOADING -> {
                mainActivity.showProgressDialog()
            }

            ApiResult.Status.SUCCESS -> {
                mainActivity.hideProgressDialog()
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(viewModel.da.data!!.assignments.sortedWith(StringDateComparator)) { item ->
                        Card(
                            Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 8.dp),
                            contentColor = Color(0xFF262845)
                        ) {
                            Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Column(Modifier.fillMaxWidth(0.5f)) {
                                    Text(
                                        text = item.courseName,
                                        style = typography.caption.copy(color = Color(0xFFA8A9B5))
                                    )
                                    Text(text = item.title)
                                }
                                Column(
                                    Modifier.fillMaxWidth().fillMaxHeight(),
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "Due on",
                                        style = typography.caption.copy(color = Color(0xFFA8A9B5))
                                    )
                                    Text(
                                        text = item.lastDate.split("-").toMutableList()
                                            .apply { remove(last()) }.joinToString("-")
                                    )
                                }
                            }
                        }
                    }
                }
            }
            ApiResult.Status.ERROR -> {
                Toast.makeText(mainActivity, viewModel.da.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}