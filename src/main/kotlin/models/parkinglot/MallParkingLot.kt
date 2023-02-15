package models.parkinglot

import constants.FeeConstants
import models.parkingspot.ParkingSpot
import models.VehicleType
import java.time.Duration
import java.time.LocalDateTime

class MallParkingLot(vehicleParkingSpots: MutableMap<VehicleType, ParkingSpot>): ParkingLot(vehicleParkingSpots) {
    override fun calculateFee(entryTime: LocalDateTime, exitTime: LocalDateTime, vehicleType: VehicleType): Long {
        val duration = Duration.between(entryTime, exitTime).toHours()
        return duration * FeeConstants.mallFeeModel[vehicleType]!!
    }
}