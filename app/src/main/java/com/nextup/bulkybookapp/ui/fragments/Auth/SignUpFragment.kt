package com.nextup.bulkybookapp.ui.fragments.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.data.Models.auth.RegisterParams
import com.nextup.bulkybookapp.databinding.FragmentSignUpBinding
import com.nextup.bulkybookapp.ui.activities.Auth.AuthActivity
import com.nextup.bulkybookapp.ui.view_models.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        authVm = (requireActivity() as AuthActivity).authVm

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.SignIn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.SignUp.setOnClickListener {
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

}