package com.joesamyn.envelope.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.joesamyn.envelope.R
import com.joesamyn.envelope.databinding.FragmentLoginBinding
import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.User
import com.joesamyn.envelope.models.UserLogin
import com.joesamyn.envelope.ui.activity.MainActivity
import com.joesamyn.envelope.ui.viewmodels.LoginStateEvent
import com.joesamyn.envelope.ui.viewmodels.LoginViewModel
import com.joesamyn.envelope.util.DataState
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Set onclick listeners
        setOnClickListeners()

        // Observe Live Data
        subscribeObservers()

        return binding.root

    }


    /**
     * Private Functions
     */
    private fun setOnClickListeners(){
        // Login clicked
        binding.loginButton.setOnClickListener {
            if(validateInput())
                viewModel.setStateEvent(LoginStateEvent.LoginEvent)
        }
    }

    private fun validateInput(): Boolean {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if(username == ""){
            binding.username.error = getString(R.string.usernameNullError)
            return false
        }

        if(password == ""){
            binding.password.error = getString(R.string.passwordNullError)
            return false
        }

        if(password.length < 8){
            binding.password.error = getString(R.string.passwordLengthError)
            return false
        }

        binding.username.error = null
        binding.password.error = null
        return true
    }

    private fun subscribeObservers() {

        // State Observer
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<AuthResp> -> {
                    showProgressBar(false)
                    showErrorUi(false)
                    loggedIn()
                }

                is DataState.Loading -> {
                    showProgressBar(true)
                    showErrorUi(false)
                }

                is DataState.Failed -> {
                    showErrorUi(true)
                    showProgressBar(false)
                }
            }
        })

    }

    /**
     * Handle successful login navigation
     */
    private fun loggedIn() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    /**
     * Show or hide progress bar
     */
    private fun showProgressBar(isVisible: Boolean) {
        if(isVisible)
            binding.loadingIndicator.visibility = View.VISIBLE
        else
            binding.loadingIndicator.visibility = View.GONE
    }

    private fun showErrorUi(isVisible: Boolean) {
        if(isVisible)
            binding.invalidLoginText.visibility = View.VISIBLE
        else
            binding.invalidLoginText.visibility = View.GONE
    }

    // Static class used to get instance of LoginFragment
    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}