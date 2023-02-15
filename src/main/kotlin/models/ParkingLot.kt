package models

import java.time.LocalDateTime

abstract class ParkingLot(private var vehicleParkingSpots: MutableMap<VehicleType, ParkingSpot>) {

    private var allValidTickets = mutableMapOf<Int,Int>()

    fun parkVehicle(vehicleType: VehicleType): Ticket {
        val vehicleParkingSpots = vehicleParkingSpots[vehicleType]!!

        val spotId = vehicleParkingSpots.parkVehicle()
        if (spotId == -1)
            throw Exception("Parking Lot is full")

        val ticket = Ticket(spotId).generateTicket()
        allValidTickets[ticket.getTicketNumber()] = spotId
        return ticket
    }

    fun unParkVehicle(ticket: Ticket, vehicleType: VehicleType): Receipt {

        if(!allValidTickets.containsKey(ticket.getTicketNumber()))
            throw Exception("$vehicleType is not parked")

        val vehicleParkingSpots = vehicleParkingSpots[vehicleType]!!
        val exitTime = LocalDateTime.now()

        vehicleParkingSpots.unParkVehicle(ticket)

        allValidTickets.remove(ticket.getTicketNumber())

        val parkingFee:Long = calculateFee(ticket.getEntryTime()!!, exitTime,vehicleType)
        return Receipt(ticket).generateReceipt(exitTime, parkingFee)
    }

    abstract fun calculateFee(entryTime: LocalDateTime, exitTime: LocalDateTime, vehicleType: VehicleType):Long

}