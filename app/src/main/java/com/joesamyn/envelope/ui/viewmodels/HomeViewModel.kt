package com.joesamyn.envelope.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joesamyn.envelope.adapters.EnvelopeAdapter
import com.joesamyn.envelope.interfaces.services.IEnvelopeService
import com.joesamyn.envelope.models.Envelope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    lateinit var adapter: EnvelopeAdapter

    init {

    }

    // UI Properties
    private val _envelopes = MutableLiveData<List<Envelope>>()
    val envelopes: LiveData<List<Envelope>>
        get() = _envelopes

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double>
        get() = _total

    /**
     * Initialize the list for the recycler view
     */
    private fun initializeRecyclerView(){

    }
}