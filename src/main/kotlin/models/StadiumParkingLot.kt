package models

import models.VehicleType.*
import java.time.Duration
import java.time.LocalDateTime

class StadiumParkingLot(private var vehicleParkingSpots: MutableMap<VehicleType, ParkingSpot>): ParkingLot(vehicleParkingSpots) {
    override fun calculateFee(entryTime: LocalDateTime, exitTime: LocalDateTime, vehicleType: VehicleType): Long {
        val duration = Duration.between(entryTime, exitTime).toHours()
        return when(vehicleType){
            MOTORCYCLE -> {
                calculateFeeForMotorcycle(duration)
            }
            CAR, SUV -> {
                calculateFeeForCarSuv(duration)
            }
            else -> 0
        }
    }

    private fun calculateFeeForCarSuv(duration: Long): Long {
        var totalFee = 0L
        if (duration in 0..3) totalFee = 60
        if (duration in 4..11) totalFee = 180
        if (duration >= 12) totalFee = (duration-11) * 200 + 240
        return totalFee
    }

    private fun calculateFeeForMotorcycle(duration: Long): Long {
        var totalFee = 0L
        if (duration in 0..3) totalFee = 30
        if (duration in 4..11) totalFee = 60
        if (duration >= 12) totalFee = (duration-11) * 100 + 90
        return totalFee
    }
}