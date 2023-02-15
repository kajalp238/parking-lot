package models

import models.VehicleType.*
import java.time.Duration
import java.time.LocalDateTime

class AirportParkingLot(private var vehicleParkingSpots: MutableMap<VehicleType, ParkingSpot>): ParkingLot(vehicleParkingSpots) {
    override fun calculateFee(entryTime: LocalDateTime, exitTime: LocalDateTime, vehicleType: VehicleType): Long {
        val duration = Duration.between(entryTime, exitTime).toHours()
        val days = Duration.between(entryTime, exitTime).toDays()
        return when(vehicleType){
            MOTORCYCLE -> {
                calculateFeeForMotorcycle(duration, days)
            }

            CAR, SUV -> {
                calculateFeeForCarSuv(duration, days)
            }

            else -> 0
        }
    }

    private fun calculateFeeForCarSuv(duration: Long, days: Long): Long {
        if (duration in 0..11) return 60
        if (duration in 12..23) return 80
        return days * 100
    }

    private fun calculateFeeForMotorcycle(duration: Long, days: Long): Long {
        if (duration < 1) return 0
        if (duration in 1..7) return 40
        if (duration in 8..23) return 60
        return days * 80
    }

}