package com.nextup.bulkybookapp.ui.fragments.Auth

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.Utils.AndroidUtils.hideLoadingDialog
import com.nextup.bulkybookapp.Utils.AndroidUtils.initLoadingDialog
import com.nextup.bulkybookapp.Utils.AndroidUtils.showLoadingDialog
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivity
import com.nextup.bulkybookapp.Utils.AndroidUtils.toast
import com.nextup.bulkybookapp.Utils.UiState
import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.data.Models.auth.RegisterParams
import com.nextup.bulkybookapp.databinding.FragmentSignUpBinding
import com.nextup.bulkybookapp.ui.activities.Auth.AuthActivity
import com.nextup.bulkybookapp.ui.activities.Main.MainActivity
import com.nextup.bulkybookapp.ui.view_models.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.regex.Pattern

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var authVm: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoadingDialog()

        authVm = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.SignIn.setOnClickListener {
            findNavController().navigateUp()
        }


        collectSignUpResponse()

        binding.SignUp.setOnClickListener {

            if (binding.Email.text.toString().isEmpty()){
                toast(
                    "Please enter your email address",
                )
                return@setOnClickListener
            }

            if (binding.Lastname.text.toString().isEmpty()){
                toast(
                    "Please enter your lastname",
                )
                return@setOnClickListener
            }

            if (binding.Name.text.toString().isEmpty()){
                toast(
                    "Please enter your name",
                )
                return@setOnClickListener
            }

            if (binding.Password.text.toString().isEmpty()){
                toast(
                    "Please enter your password",
                )
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.Email.text.toString()).matches()){
                toast(
                    "Please enter a valid email address",
                )
                return@setOnClickListener
            }

            RegisterParams(
                binding.Email.text.toString(),
                binding.Lastname.text.toString(),
                binding.Name.text.toString(),
                binding.Password.text.toString()
            ).let { params->
                authVm.register(params)
            }
        }

    }

    private fun collectSignUpResponse() = lifecycleScope.launchWhenStarted {
        authVm.registerState.collectLatest {
            when(it){
                is UiState.Error -> {
                    hideLoadingDialog()
                    toast(
                        it.message.toString(),
                        Gravity.CENTER,
                        10
                    )
                }
                is UiState.Loading -> {
                    showLoadingDialog()
                }
                is UiState.Success -> {
                    hideLoadingDialog()
                    findNavController().navigateUp()
                    toast(
                        "You have successfully registered"
                    )
                }
                is UiState.Idle -> {}
            }
        }
    }

}