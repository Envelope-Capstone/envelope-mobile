package com.joesamyn.envelope.models

import java.sql.Date


object TempTransactions {

    /**
     * List of transactions to use for demo
     */
    private val transactions: List<Transaction> = listOf(
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "KOHLS #0638 PHOENIX AZ", 128.14),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "CIRCLE K 03744 PHOENIX AZ00481R", 38.95),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "GCU STUDENT UNION PHOENIX AZ", 9.75),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "GCU STUDENT UNION PHOENIX AZ", 	8.85),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "TST* STATE 48 BREWERY - PHOENIX AZ", 12.77),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "CHEVRON 0352103 SCOTTSDALE AZ00352103 0384142", 3.05),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "CAMELBACK VW MAZDA SUB PHOENIX AZ727366", 100.43),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "FRYS-FOOD-DRG #129 PHOENIX AZ", 18.03),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "LUCI'SHEALTHYMARKETPLACE PHOENIX AZ", 	7.05),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "GCU STUDENT UNION PHOENIX AZ", 9.75),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "TST* ZOOKZ - E CAMELBACK PHOENIX AZ", 14.86),
            Transaction(Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), "FRYS-FOOD-DRG #129 PHOENIX AZ", 10.85)
    )

    /**
     * Get random transaction from list of transactions
     */
    @JvmStatic
    fun getTransaction(): Transaction {
        return transactions.random()
    }
}