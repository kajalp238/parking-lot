package models

class ParkingSpot(private val totalSpots: Int) {

    private var spots: MutableList<Boolean> = MutableList(totalSpots+1){false}
    private var availableSpots: Int = totalSpots

    fun parkVehicle(): Int {
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

    fun unParkVehicle(ticket: Ticket) {
        freeSlot(ticket.getParkingSpotId())
        availableSpots++
    }

    private fun isSlotAvailable(): Boolean {
        return availableSpots > 0
    }

    private fun reserveSlot(slotId: Int){
        spots[slotId] = true
    }

    private fun freeSlot(slotId: Int){
        spots[slotId] = false
    }

}