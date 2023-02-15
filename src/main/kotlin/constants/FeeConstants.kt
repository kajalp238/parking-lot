package constants

import models.VehicleType
import models.VehicleType.*

object FeeConstants {

    val mallFeeModel: Map<VehicleType, Long> = mapOf(
        MOTORCYCLE to 10,
        CAR to 20,
        SUV to 20,
        BUS to 50,
        TRUCK to 50
    )

}