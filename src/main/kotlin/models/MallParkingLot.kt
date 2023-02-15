package models

import models.VehicleType.*
import java.time.Duration
import java.time.LocalDateTime

class MallParkingLot(private var vehicleParkingSpots: MutableMap<VehicleType, ParkingSpot>): ParkingLot(vehicleParkingSpots) {
    override fun calculateFee(entryTime: LocalDateTime, exitTime: LocalDateTime, vehicleType: VehicleType): Long {
        val duration = Duration.between(entryTime, exitTime).toHours()
        return when(vehicleType){
            MOTORCYCLE -> duration * 10
            CAR, SUV -> duration * 20
            BUS, TRUCK -> duration * 50
            else -> 0
        }
    }
}