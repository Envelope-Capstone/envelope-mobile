package com.joesamyn.envelope.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joesamyn.envelope.R
import com.joesamyn.envelope.adapters.EnvelopeAdapter
import com.joesamyn.envelope.databinding.FragmentHomeBinding
import com.joesamyn.envelope.interfaces.services.IEnvelopeService
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Home : Fragment() {

    /**
     * Private DI variables
     */
    @Inject
    lateinit var envelopeService: IEnvelopeService

    // Variables
    private val linearLayoutManager = LinearLayoutManager(context)
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout and get an instance
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = viewModel


        // Init recycler view
        initEnvelopeRecView()

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Initializes the recycler view for the home page that displays the list of envelopes
     */
    fun initEnvelopeRecView(){
        val envelopes = envelopeService.getEnvelopes()
        val recView = binding.envelopesListView
        recView.adapter = EnvelopeAdapter(requireContext(), envelopes)
        observeEnvelopesList()
    }

    /**
     * Observe the envelopes list and update recycler view with any changes
     */
    fun observeEnvelopesList(){
        viewModel.envelopes.observe(viewLifecycleOwner, Observer { envelopes ->
            binding.envelopesListView.adapter = EnvelopeAdapter(requireContext(), envelopes)
        })
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