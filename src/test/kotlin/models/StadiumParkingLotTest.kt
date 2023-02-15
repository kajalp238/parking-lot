package models

import models.VehicleType.*
import models.parkinglot.StadiumParkingLot
import models.parkingspot.ParkingSpot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class StadiumParkingLotTest {

    @Test
    fun `it should park the vehicle in stadium parking lot`() {
        //Arrange
        val parkingSpots = mutableMapOf<VehicleType, ParkingSpot>()
        parkingSpots[CAR] = ParkingSpot(10)
        val parkingLot = StadiumParkingLot(parkingSpots)

        //Act
        val actualTicket = parkingLot.parkVehicle(CAR)

        //Assert
        val expectedTicket = Ticket(1)
        assertEquals(expectedTicket.getTicketNumber(), actualTicket.getTicketNumber())
    }

    @Test
    fun `it should not park the second vehicle`() {
        //Arrange
        val parkingSpots = mutableMapOf<VehicleType, ParkingSpot>()
        parkingSpots[CAR] = ParkingSpot(1)
        val parkingLot = StadiumParkingLot(parkingSpots)
        parkingLot.parkVehicle(CAR)

        //Assert
        assertThrows(Exception::class.java) {
            parkingLot.parkVehicle(CAR)
        }
    }

    @Test
    fun `it should unpark vehicle from stadium parking lot`() {
        //Arrange
        val parkingSpots = mutableMapOf(CAR to ParkingSpot(10))
        val parkingLot = StadiumParkingLot(parkingSpots)
        val ticket = parkingLot.parkVehicle(CAR)

        //Act
        val actualReceipt = parkingLot.unParkVehicle(ticket, CAR)

        //Assert
        val exceptedReceipt = Receipt(ticket)
        assertEquals(exceptedReceipt.getReceiptNumber(), actualReceipt.getReceiptNumber())
        assertEquals(exceptedReceipt.getParkingFee(), 0)
    }

    @Test
    fun `it should not unpark vehicle as vehicle is not already parked`() {
        //Arrange
        val parkingSpots = mutableMapOf(CAR to ParkingSpot(10))
        val parkingLot = StadiumParkingLot(parkingSpots)
        val ticket = Ticket(1)

        //Assert
        assertThrows(Exception::class.java) {
            parkingLot.unParkVehicle(ticket,CAR)
        }
    }

    @Test
    fun `it should calculate fee for car`() {
        //Arrange
        val parkingSpots = mutableMapOf(CAR to ParkingSpot(10))
        val parkingLot = StadiumParkingLot(parkingSpots)

        val fee = parkingLot.calculateFee(LocalDateTime.of(2023, 2, 14, 8, 0, 0),LocalDateTime.of(2023, 2, 14, 10, 0, 0), CAR)

        assertEquals(60, fee)
    }

    @Test
    fun `it should calculate fee for motorcycle`() {
        //Arrange
        val parkingSpots = mutableMapOf(MOTORCYCLE to ParkingSpot(10))
        val parkingLot = StadiumParkingLot(parkingSpots)

        val fee = parkingLot.calculateFee(LocalDateTime.of(2023, 2, 14, 2, 0, 0),LocalDateTime.of(2023, 2, 14, 10, 0, 0), MOTORCYCLE)

        assertEquals(60, fee)
    }

    @Test
    fun `it should calculate fee for motorcycle when duration is 12 hrs`() {
        //Arrange
        val parkingSpots = mutableMapOf(MOTORCYCLE to ParkingSpot(10))
        val parkingLot = StadiumParkingLot(parkingSpots)

        val fee = parkingLot.calculateFee(LocalDateTime.of(2023, 2, 14, 0, 0, 0),LocalDateTime.of(2023, 2, 14, 12, 0, 0), MOTORCYCLE)

        assertEquals(190, fee)
    }

}