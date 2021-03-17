package com.joesamyn.envelope.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joesamyn.envelope.R
import com.joesamyn.envelope.adapters.EnvelopeAdapter
import com.joesamyn.envelope.databinding.FragmentHomeBinding
import com.joesamyn.envelope.interfaces.services.IEnvelopeService
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
    private lateinit var envelopeService: IEnvelopeService

    // Variables
    private val linearLayoutManager = LinearLayoutManager(context)
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Get bound layout
        binding = FragmentHomeBinding.inflate(layoutInflater)

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
        recView.setHasFixedSize(true)
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