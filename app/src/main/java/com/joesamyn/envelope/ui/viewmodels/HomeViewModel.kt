package com.joesamyn.envelope.ui.viewmodels

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesamyn.envelope.R
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.repositories.EnvelopeRepository
import com.joesamyn.envelope.ui.fragment.Home
import com.joesamyn.envelope.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val envelopeRepository: EnvelopeRepository
): ViewModel() {

    private val TAG = HomeViewModel::class.java.simpleName

    private val _total = MutableLiveData<Double>(0.00)
    val total: LiveData<Double>
        get() = _total

    private val _dataState: MutableLiveData<DataState<List<Envelope>>> = MutableLiveData()
    // Accessor for the data state object above
    val dataState: LiveData<DataState<List<Envelope>>>
        get() = _dataState


    fun setStateEvent(homeStateEvent: HomeStateEvent){
        viewModelScope.launch {
            when(homeStateEvent){
                is HomeStateEvent.GetEnvelopeEvent -> {
                    envelopeRepository.getEnvelopes()
                        .onEach{ dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

    /** Functions */
    fun calculateTotal(envelopes: List<Envelope>) {
        var t = 0.0
        for(i in envelopes){
            t += i.total
        }
        _total.value = t
    }

    /**
     * Add Envelope to database
     */
    fun addEnvelope(envelopeName: String){
        try {
            viewModelScope.launch {
                envelopeRepository.addEnvelope(Envelope(0, envelopeName, getIconForEnvelope(envelopeName), 0.00))
            }
        }catch (ex: Exception){
            Log.e(TAG, ex.message!!)
        }

        setStateEvent(HomeStateEvent.GetEnvelopeEvent)
    }

    private fun getIconForEnvelope(name: String): Int{
        return when(name) {
            "Automotive" -> R.drawable.ic_auto
            "Merchandise" -> R.drawable.ic_supermarket
            "Education" -> R.drawable.ic_education
            "Gas" -> R.drawable.gas_classification
            "Department Stores" -> R.drawable.ic_deptstores
            "Restaurants" -> R.drawable.ic_restaurant
            else -> R.drawable.ic_restaurant
        }
    }
}

/**
 * Outline states that will be used in the UI/App
 * Follows MVI architecture not MVVM
 * We want to list all the events we can fire off in this view
 */
sealed class HomeStateEvent {
    // Event to listen and get all the envelopes from the Database
    object GetEnvelopeEvent: HomeStateEvent()

    // None event used to clear up state
    object None: HomeStateEvent()
}