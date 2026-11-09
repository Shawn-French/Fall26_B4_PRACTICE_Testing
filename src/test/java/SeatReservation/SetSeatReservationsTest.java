package SeatReservation;

import cpsc2150.SeatReservation.models.ArraySeatReservations;
import cpsc2150.SeatReservation.models.ISeatReservations;
import cpsc2150.SeatReservation.models.SetSeatReservations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetSeatReservationsTest {

    //Case origin: US-01-AC-01:
    //Input: 10
    //Input State: NA
    //Output: 10 from getseatcount();
    //Output State: 10 seats, all available
    @Test
    public void IfPosSeatCountReportSameNumOfSeats()
    {
        ISeatReservations sr = new ArraySeatReservations(10);

        assertEquals(10, sr.getSeatCount());
    }

    //Case origin: US-01-AC-02
    //Input: 10, 1-10 for inspection
    //Input State: NA
    //Output: 10 from isReserved();
    //Output State: 10 seats, all available
    @Test
    public void AllSeatsAvailableOnNew()
    {
        ISeatReservations sr = new ArraySeatReservations(10);

        for(int i=0;i<10;i++)
        {
            assertFalse(sr.isReserved(i));
        }
    }

    //Case origin: US-01-AC-03
    //Input: 1
    //Input State: NA
    //Output: seat count of 1 with the seat at 0 being nonreserved
    //Output State: 1 available seat
    @Test
    public void Given1Seat1SeatIsAvailable()
    {
        ISeatReservations sr = new ArraySeatReservations(1);

        assertEquals(1, sr.getSeatCount());
        assertFalse(sr.isReserved(0));
    }

    //Case origin: US-02-AC-01
    //Input: 5
    //Input State: NA
    //Output: seat count of 5, with seat 3 being reserved
    //Output State: 4 available seats with seat 2 being reserved
    @Test
    public void ReserveSeatForValidSeat()
    {
        ISeatReservations sr = new ArraySeatReservations(5);

        sr.reserveSeat(3);

        assertTrue(sr.isReserved(3));
    }

    //Case origin: US-02-AC-02
    //Input: 5
    //Input State: NA
    //Output: seat count of 5, with seat 2 being reserved
    //Output State: 4 available seats with seat 2 being reserved
    @Test
    public void ReservedSeatCalledTwice()
    {
        ISeatReservations sr = new ArraySeatReservations(5);

        sr.reserveSeat(2);
        sr.reserveSeat(2);

        assertTrue(sr.isReserved(2));
    }

    //Case origin: US-02-AC-03
    //Input: 10
    //Input State: NA
    //Output: seat count of 10 with 4 being reserved
    //Output State: 9 available seats
    @Test
    public void ReservedSeatOnlyChangesSeatReserved()
    {
        ISeatReservations sr = new ArraySeatReservations(10);

        sr.reserveSeat(4);

        assertFalse(sr.isReserved(2));
        assertEquals(10, sr.getSeatCount());
        assertEquals(9, sr.getAvailableSeatCount());
    }

    //Case origin: US-04-AC-01
    //Input: 10
    //Input State: NA
    //Output: seat count of 10
    //Output State: 10 available seats
    @Test
    public void CancleReservedSeatCanclesTheReservedSeat()
    {
        ISeatReservations sr = new ArraySeatReservations(10);

        sr.reserveSeat(3);
        sr.cancelReservation(3);

        assertFalse(sr.isReserved(3));
    }

    //Case origin: US-02-AC-02
    //Input: 10
    //Input State: NA
    //Output: seat count of 8
    //Output State: 8 available seats
    @Test
    public void CancleReservationDoesNotChangeAvailableSeat()
    {
        ISeatReservations sr = new ArraySeatReservations(8);

        sr.cancelReservation(3);

        assertFalse(sr.isReserved(3));
    }

    //Case origin: US-04-AC-03
    //Input: 10
    //Input State: NA
    //Output: seat count of 10 with 4 being reserved
    //Output State: 9 available seats, total seat count = 10
    @Test
    public void CancleReservationDoesNotChangeOtherSeats()
    {
        ISeatReservations sr = new ArraySeatReservations(10);

        sr.cancelReservation(5);
        sr.reserveSeat(4);

        assertFalse(sr.isReserved(2));
        assertTrue(sr.isReserved(4));
        assertEquals(10, sr.getSeatCount());
    }






}