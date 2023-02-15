package models

import java.time.LocalDateTime

class Ticket(private var parkingSpotId: Int) {

    private var ticketNumber: Int = generateTicketNumber()
    private var entryTime: LocalDateTime? = LocalDateTime.now()

    private fun generateTicketNumber(): Int {
        return ticketNumber++
    }

    fun getEntryTime(): LocalDateTime? {
        return entryTime
    }

    fun generateTicket(): Ticket {
        return this
    }

    fun getParkingSpotId(): Int {
        return parkingSpotId
    }

    fun getTicketNumber():Int{
        return ticketNumber
    }

}