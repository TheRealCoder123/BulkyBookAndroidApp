package com.nextup.bulkybookapp.ui.fragments.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.Utils.AndroidUtils.hideLoadingDialog
import com.nextup.bulkybookapp.Utils.AndroidUtils.initLoadingDialog
import com.nextup.bulkybookapp.Utils.AndroidUtils.showLoadingDialog
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivity
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivityFinishBackground
import com.nextup.bulkybookapp.Utils.AndroidUtils.toast
import com.nextup.bulkybookapp.Utils.DataStore
import com.nextup.bulkybookapp.Utils.UiState
import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.databinding.FragmentSignInBinding
import com.nextup.bulkybookapp.di.App
import com.nextup.bulkybookapp.ui.activities.Auth.AuthActivity
import com.nextup.bulkybookapp.ui.activities.Main.MainActivity
import com.nextup.bulkybookapp.ui.view_models.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var authVm: AuthViewModel

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoadingDialog()

        authVm = (requireActivity() as AuthActivity).authVm

        collectSignInResponse()

        val isGuest = dataStore.readBoolean(dataStore.tags().CONTINUE_AS_GUEST_TAG)

        if (isGuest){
            binding.CountinueAsGuest.visibility = View.GONE
        }else{
            binding.CountinueAsGuest.visibility = View.VISIBLE
        }

        binding.CountinueAsGuest.setOnClickListener {
            startActivity(MainActivity())
            requireActivity().finish()
            dataStore.saveBoolean(dataStore.tags().CONTINUE_AS_GUEST_TAG, true)
        }

        binding.SignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.SignIn.setOnClickListener {
            if (binding.Email.text.toString().isEmpty()){
                toast(
                    "Please enter your email address",
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

            LoginParams(
                binding.Email.text.toString(),
                binding.Password.text.toString()
            ).let {
                authVm.login(it)
            }

        }

    }

    private fun collectSignInResponse() = lifecycleScope.launchWhenStarted {
        authVm.loginState.collectLatest {
            when(it){
                is UiState.Error -> {
                    hideLoadingDialog()
                    toast(
                        it.message.toString(),
                        Gravity.CENTER,
                        10
                    )
                    Log.e("login repsonse", "${it.message}")
                }
                is UiState.Loading -> {
                    showLoadingDialog()
                }
                is UiState.Success -> {
                    hideLoadingDialog()
                    startActivityFinishBackground(MainActivity())
                    dataStore.saveBoolean(dataStore.tags().CONTINUE_AS_GUEST_TAG, false)
                    requireActivity().finish()
                }
                is UiState.Idle -> {}
            }
        }
    }

}