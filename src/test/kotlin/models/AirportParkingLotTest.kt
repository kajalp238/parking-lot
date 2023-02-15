package models

import models.VehicleType.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AirportParkingLotTest {

    @Test
    fun `it should park the vehicle in airport parking lot`() {
        //Arrange
        val parkingSpots = mutableMapOf<VehicleType,ParkingSpot>()
        parkingSpots[CAR] = ParkingSpot(10)
        val parkingLot = AirportParkingLot(parkingSpots)

        //Act
        val actualTicket = parkingLot.parkVehicle(CAR)

        //Assert
        val expectedTicket = Ticket(1)
        assertEquals(expectedTicket.getTicketNumber(), actualTicket.getTicketNumber())
    }

    @Test
    fun `it should not park the second vehicle`() {
        //Arrange
        val parkingSpots = mutableMapOf<VehicleType,ParkingSpot>()
        parkingSpots[CAR] = ParkingSpot(1)
        val parkingLot = AirportParkingLot(parkingSpots)
        parkingLot.parkVehicle(CAR)

        //Assert
        assertThrows(Exception::class.java) {
            parkingLot.parkVehicle(CAR)
        }
    }

    @Test
    fun `it should unpark vehicle from airport parking lot`() {
        //Arrange
        val parkingSpots = mutableMapOf(CAR to ParkingSpot(10))
        val parkingLot = AirportParkingLot(parkingSpots)
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
        val parkingLot = AirportParkingLot(parkingSpots)
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
        val parkingLot = AirportParkingLot(parkingSpots)

        val fee = parkingLot.calculateFee(LocalDateTime.of(2023, 2, 14, 8, 0, 0),LocalDateTime.of(2023, 2, 14, 10, 0, 0), CAR)

        assertEquals(60, fee)
    }

    @Test
    fun `it should calculate fee for motorcycle`() {
        //Arrange
        val parkingSpots = mutableMapOf(MOTORCYCLE to ParkingSpot(10))
        val parkingLot = AirportParkingLot(parkingSpots)

        val fee = parkingLot.calculateFee(LocalDateTime.of(2023, 2, 14, 8, 0, 0),LocalDateTime.of(2023, 2, 14, 10, 0, 0), MOTORCYCLE)

        assertEquals(40, fee)
    }

    @Test
    fun `it should calculate fee for motorcycle when duration is 24 hrs`() {
        //Arrange
        val parkingSpots = mutableMapOf(MOTORCYCLE to ParkingSpot(10))
        val parkingLot = AirportParkingLot(parkingSpots)

        val fee = parkingLot.calculateFee(LocalDateTime.of(2023, 2, 14, 0, 0, 0),LocalDateTime.of(2023, 2, 15, 0, 0, 0), MOTORCYCLE)

        assertEquals(80, fee)
    }

}