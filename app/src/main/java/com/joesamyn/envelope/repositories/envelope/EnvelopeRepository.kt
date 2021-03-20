package com.joesamyn.envelope.repositories.envelope

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.WorkerThread
import com.joesamyn.envelope.R
import com.joesamyn.envelope.entities.EnvelopeEntity
import com.joesamyn.envelope.interfaces.repositories.IEnvelopeRepository
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.repositories.dao.EnvelopeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EnvelopeRepository @Inject constructor(private val envelopeDao: EnvelopeDao): IEnvelopeRepository {

    // TODO: Change the Envelope to use Bitmap instead of int for icon
    // TODO: Add list of transactions as property to envelope

    /**
     * Get all envelopes from SQLite storage
     */
    override fun getEnvelopes(): List<Envelope> {
        return listOf(
                Envelope("Gas", R.drawable.gas_classification, 130.60),
                Envelope("Grocery", R.drawable.gas_classification, 130.60),
                Envelope("Shopping", R.drawable.gas_classification, 130.60),
                Envelope("Coffee", R.drawable.gas_classification, 130.60)
        )
    }

    val envelopes : Flow<List<EnvelopeEntity>> = envelopeDao.getAllEnvelopes()

    @WorkerThread
    suspend fun insert(envelope: EnvelopeEntity){
        envelopeDao.insert(envelope)
    }

}