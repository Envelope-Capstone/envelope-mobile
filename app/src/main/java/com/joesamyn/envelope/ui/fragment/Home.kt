package com.joesamyn.envelope.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.joesamyn.envelope.R
import com.joesamyn.envelope.adapters.EnvelopeAdapter
import com.joesamyn.envelope.databinding.FragmentHomeBinding
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.ui.viewmodels.HomeStateEvent
import com.joesamyn.envelope.ui.viewmodels.HomeViewModel
import com.joesamyn.envelope.util.DataState
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Home : Fragment() {

    // Constants
    private val LOG = Home::class.java.simpleName

    // Variables
    private val linearLayoutManager = LinearLayoutManager(context)
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout and get an instance
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = viewModel


        // Init recycler view
        subscribeObservers()
        viewModel.setStateEvent(HomeStateEvent.GetEnvelopeEvent)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Subscribes to all necessary observables in the ViewModel
     */
    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            // Handle proper state events
            when(dataState) {
                // Handle success state (data returned)
                is DataState.Success<List<Envelope>> -> {
                    displayProgressBar(false)
                    displayEnvelopes(dataState.data)
                }

                // Handle exception thrown
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                // Handle loading state (show activity icon)
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    /**
     * Handle error and display proper message
     */
    private fun displayError(message: String?){
        if(message != null){
            Log.e(LOG, message)
        }
        else {
            Log.e(LOG, "unknown error occured")
        }
    }

    /**
     * Handle progress bar view for when items are loading
     */
    private fun displayProgressBar(isDisplay: Boolean){
        if(isDisplay)
            Log.d(LOG, "Display the progress bar")
        else
            Log.d(LOG, "Stop displaying Progress bar")
    }

    /**
     * Display the envelopes from repository get method
     */
    private fun displayEnvelopes(envelopes: List<Envelope>){
        binding.envelopesListView.adapter = EnvelopeAdapter(requireContext(), envelopes)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment Home.
         */
        @JvmStatic
        fun newInstance(): Home {
            return Home()
        }
    }
}