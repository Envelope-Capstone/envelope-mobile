package com.joesamyn.envelope.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joesamyn.envelope.R
import com.joesamyn.envelope.databinding.FragmentLoginBinding
import com.joesamyn.envelope.ui.activity.MainActivity
import com.joesamyn.envelope.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        (activity as MainActivity).hideNavigationBar(true)
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Set onclick listeners
        setOnClickListeners()

        return binding.root

    }


    /**
     * Private Functions
     */
    private fun setOnClickListeners(){
        // Login clicked
        binding.loginButton.setOnClickListener { onLogin() }
    }

    private fun onLogin() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }


    // Static class used to get instance of LoginFragment
    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}