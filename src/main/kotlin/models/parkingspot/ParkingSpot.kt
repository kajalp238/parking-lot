package models.parkingspot

import models.Ticket

class ParkingSpot(private val totalSpots: Int) {

    private var spots: MutableList<Boolean> = MutableList(totalSpots+1){false}
    private var availableSpots: Int = totalSpots

    fun assignSpot(): Int {
        if (isSlotAvailable()) {
            for (i in 1..totalSpots) {
                if (!spots[i]) {
                    reserveSlot(i)
                    return i
                }
            }
            availableSpots--
        }
        return -1
    }

    fun releaseSpot(ticket: Ticket) {
        spots[ticket.getParkingSpotId()] = false
        availableSpots++
    }

    private fun isSlotAvailable(): Boolean {
        return availableSpots > 0
    }

    private fun reserveSlot(slotId: Int){
        spots[slotId] = true
    }

}