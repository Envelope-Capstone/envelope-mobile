package com.joesamyn.envelope.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.repositories.TransactionRepository
import com.joesamyn.envelope.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository): ViewModel() {

    // Live data object to hold transaction data being loaded from DB
    private val _transactions: MutableLiveData<DataState<List<ClassifiedTransaction>>> = MutableLiveData()
    val transaction: LiveData<DataState<List<ClassifiedTransaction>>>
        get() = _transactions

    /**
     * Set state for UI. Used to get transactions from the database for a specific envelope
     */
    fun setStateEvent(transactionStateEvent: TransactionStateEvent, envelope: String) {
        viewModelScope.launch {
            when(transactionStateEvent){
                is TransactionStateEvent.GetTransactionsEvent -> {
                    transactionRepository.getTransactionsForEnvelope(envelope).onEach { transactions ->
                        _transactions.value = transactions
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    private fun addTransaction() {
        viewModelScope.launch {
            transactionRepository.addTransaction(ClassifiedTransaction(0, Date(), Date(), "Test Transaction 1", 23.65, "Restaurants"))
        }
    }
}

/**
 * Class used to hold states of the transaction view
 * i.e. fetching data or standing idle waiting for user interaction
 */
sealed class TransactionStateEvent {
    object GetTransactionsEvent: TransactionStateEvent()

    object None: TransactionStateEvent()
}