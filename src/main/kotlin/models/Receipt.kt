package models

import java.time.LocalDateTime

class Receipt(ticket: Ticket) {

    private var receiptNumber: Int = generateReceiptNumber()
    private var entryTime = ticket.getEntryTime()
    private var exitTime = LocalDateTime.now()
    private var parkingFee: Long = 0

    fun getReceiptNumber(): Int {
        return receiptNumber
    }

    private fun generateReceiptNumber(): Int {
        return receiptNumber++
    }

    fun generateReceipt(exitTime: LocalDateTime, parkingFee: Long): Receipt {
        this.exitTime = exitTime
        this.parkingFee = parkingFee
        return this
    }

    fun getParkingFee(): Long {
        return parkingFee
    }

}