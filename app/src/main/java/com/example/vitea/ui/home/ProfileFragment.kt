package com.example.vitea.ui.home

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.example.vitea.R
import com.example.vitea.databinding.FragmentProfileBinding
import com.example.vitea.models.ApiResult
import com.example.vitea.utils.PreferenceHelper.get
import com.example.vitea.utils.hide
import com.example.vitea.utils.show
import com.example.vitea.utils.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    @Inject lateinit var prefs: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).showProgressDialog()
        binding.root.hide()
        viewModel.profile.observe(viewLifecycleOwner) { resp ->
            when (resp.status) {
                ApiResult.Status.LOADING -> {
                    viewModel.getProfile(prefs["reg_no", "17BCE0001"].toString())
                }

                ApiResult.Status.SUCCESS -> {
                    resp.data?.let {
                        (requireActivity() as MainActivity).hideProgressDialog()
                        binding.apply {
                            val decodedString: ByteArray =
                                Base64.decode(it.img, Base64.DEFAULT)
                            val decodedBytes =
                                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

                            profilePic.load(decodedBytes)
                            profileName.text = it.studentName
                            profileRegno.text = it.regNo
                            profileGender.text = it.gender
                            profileSchool.text = it.school
                            profileBranch.text = it.program
                            profileDegree.text = it.description
                            profileEmail.text = it.email
                            profileMobile.text = it.mobileNo
                            root.show()
                        }
                    }
                }

                ApiResult.Status.ERROR -> {
                    Snackbar.make(binding.root, resp.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}