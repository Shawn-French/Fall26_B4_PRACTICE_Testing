package SeatReservation;

import cpsc2150.SeatReservation.models.ArraySeatReservations;
import cpsc2150.SeatReservation.models.ISeatReservations;
import cpsc2150.SeatReservation.models.SetSeatReservations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OriginalTestsDoNotDoWhatTheseDo {

    enum Implementation {
        ARRAY,
        SET
    }

    private ISeatReservations createVenue(
            Implementation implementation, int seatCount) {
        return implementation == Implementation.ARRAY
                ? new ArraySeatReservations(seatCount)
                : new SetSeatReservations(seatCount);
    }

    /**
     * Checks every seat's reservation status.
     * The first boolean describes seat 0, the second describes seat 1, etc.
     */
    private void assertSeats(
            ISeatReservations venue, boolean... expectedReserved) {
        assertEquals(
                expectedReserved.length,
                venue.getSeatCount(),
                "The total number of seats should remain unchanged.");

        for (int seatNumber = 0;
             seatNumber < expectedReserved.length;
             seatNumber++) {
            assertEquals(
                    expectedReserved[seatNumber],
                    venue.isReserved(seatNumber),
                    "Unexpected reservation status for seat " + seatNumber);
        }
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void constructorUsesSpecifiedSeatCount(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 7);

        assertEquals(7, venue.getSeatCount());
        assertEquals(7, venue.getAvailableSeatCount());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void newVenueHasEverySeatAvailable(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);

        assertSeats(venue, false, false, false, false, false);
        assertEquals(5, venue.getAvailableSeatCount());
        assertFalse(venue.isSoldOut());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void reservingAvailableSeatChangesOnlyThatSeat(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);

        assertTrue(venue.reserveSeat(2));

        assertSeats(venue, false, false, true, false, false);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void firstAndLastSeatsCanBeReserved(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);

        assertTrue(venue.reserveSeat(0));
        assertTrue(venue.reserveSeat(4));

        assertSeats(venue, true, false, false, false, true);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void reservingReservedSeatReturnsFalseAndPreservesState(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);
        venue.reserveSeat(1);
        venue.reserveSeat(3);

        assertFalse(venue.reserveSeat(1));

        assertSeats(venue, false, true, false, true, false);
        assertEquals(3, venue.getAvailableSeatCount());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void cancelingReservationChangesOnlyThatSeat(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);
        venue.reserveSeat(0);
        venue.reserveSeat(2);
        venue.reserveSeat(4);

        assertTrue(venue.cancelReservation(2));

        assertSeats(venue, true, false, false, false, true);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void cancelingAvailableSeatReturnsFalseAndPreservesState(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);
        venue.reserveSeat(1);
        venue.reserveSeat(4);

        assertFalse(venue.cancelReservation(3));

        assertSeats(venue, false, true, false, false, true);
        assertEquals(3, venue.getAvailableSeatCount());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void canceledSeatCanBeReservedAgain(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 3);
        venue.reserveSeat(1);

        assertTrue(venue.cancelReservation(1));
        assertFalse(venue.cancelReservation(1));
        assertSeats(venue, false, false, false);

        assertTrue(venue.reserveSeat(1));
        assertSeats(venue, false, true, false);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void availableCountTracksSuccessfulChangesOnly(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);
        assertEquals(5, venue.getAvailableSeatCount());

        venue.reserveSeat(1);
        assertEquals(4, venue.getAvailableSeatCount());

        venue.reserveSeat(3);
        assertEquals(3, venue.getAvailableSeatCount());

        venue.reserveSeat(3);
        assertEquals(
                3, venue.getAvailableSeatCount(),
                "Reserving an already reserved seat must not reduce availability.");

        venue.cancelReservation(1);
        assertEquals(4, venue.getAvailableSeatCount());

        venue.cancelReservation(1);
        assertEquals(
                4, venue.getAvailableSeatCount(),
                "Canceling an available seat must not increase availability.");

        venue.reserveSeat(4);
        assertEquals(3, venue.getAvailableSeatCount());
        assertSeats(venue, false, false, false, true, true);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void totalSeatCountNeverChanges(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 4);

        venue.reserveSeat(3);
        assertEquals(4, venue.getSeatCount());

        venue.reserveFirstAvailable();
        assertEquals(4, venue.getSeatCount());

        venue.cancelReservation(3);
        assertEquals(4, venue.getSeatCount());

        venue.cancelReservation(3);
        assertEquals(4, venue.getSeatCount());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void soldOutTracksFullAndReopenedVenue(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 3);
        assertFalse(venue.isSoldOut());

        venue.reserveSeat(0);
        venue.reserveSeat(1);
        assertFalse(venue.isSoldOut());

        venue.reserveSeat(2);
        assertTrue(venue.isSoldOut());

        venue.cancelReservation(1);
        assertFalse(venue.isSoldOut());
        assertSeats(venue, true, false, true);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void queryMethodsDoNotChangeReservations(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);
        venue.reserveSeat(1);
        venue.reserveSeat(3);

        for (int repetition = 0; repetition < 3; repetition++) {
            assertEquals(5, venue.getSeatCount());
            assertEquals(3, venue.getAvailableSeatCount());
            assertFalse(venue.isSoldOut());
            assertSeats(venue, false, true, false, true, false);
        }
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void firstAvailableReservesSeatZeroInEmptyVenue(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);

        assertEquals(0, venue.reserveFirstAvailable());

        assertSeats(venue, true, false, false, false, false);
        assertEquals(4, venue.getAvailableSeatCount());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void firstAvailableChoosesLowestGap(Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);
        venue.reserveSeat(0);
        venue.reserveSeat(2);
        venue.reserveSeat(4);

        assertEquals(1, venue.reserveFirstAvailable());

        assertSeats(venue, true, true, true, false, true);
        assertEquals(1, venue.getAvailableSeatCount());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void repeatedFirstAvailableCallsFillVenueInOrder(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 4);

        for (int expectedSeat = 0; expectedSeat < 4; expectedSeat++) {
            assertEquals(expectedSeat, venue.reserveFirstAvailable());
            assertTrue(venue.isReserved(expectedSeat));
            assertEquals(3 - expectedSeat, venue.getAvailableSeatCount());
        }

        assertSeats(venue, true, true, true, true);
        assertTrue(venue.isSoldOut());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void firstAvailableReturnsMinusOneWhenFullAndPreservesState(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 3);
        venue.reserveSeat(0);
        venue.reserveSeat(1);
        venue.reserveSeat(2);

        assertEquals(-1, venue.reserveFirstAvailable());
        assertEquals(-1, venue.reserveFirstAvailable());

        assertSeats(venue, true, true, true);
        assertEquals(0, venue.getAvailableSeatCount());
        assertTrue(venue.isSoldOut());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void firstAvailableReusesLowestCanceledSeat(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 5);

        for (int seat = 0; seat < 5; seat++) {
            venue.reserveSeat(seat);
        }

        // Cancel in reverse order to distinguish seat order from cancellation order.
        venue.cancelReservation(3);
        venue.cancelReservation(1);

        assertEquals(1, venue.reserveFirstAvailable());
        assertSeats(venue, true, true, true, false, true);

        assertEquals(3, venue.reserveFirstAvailable());
        assertSeats(venue, true, true, true, true, true);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void oneSeatVenueSupportsCompleteReservationCycle(
            Implementation implementation) {
        ISeatReservations venue = createVenue(implementation, 1);
        assertSeats(venue, false);
        assertEquals(1, venue.getAvailableSeatCount());

        assertTrue(venue.reserveSeat(0));
        assertFalse(venue.reserveSeat(0));
        assertSeats(venue, true);
        assertEquals(0, venue.getAvailableSeatCount());
        assertTrue(venue.isSoldOut());
        assertEquals(-1, venue.reserveFirstAvailable());

        assertTrue(venue.cancelReservation(0));
        assertFalse(venue.cancelReservation(0));
        assertSeats(venue, false);
        assertEquals(1, venue.getAvailableSeatCount());
        assertFalse(venue.isSoldOut());

        assertEquals(0, venue.reserveFirstAvailable());
        assertSeats(venue, true);
        assertEquals(-1, venue.reserveFirstAvailable());
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(Implementation.class)
    void separateVenuesHaveIndependentReservations(
            Implementation implementation) {
        ISeatReservations first = createVenue(implementation, 3);
        ISeatReservations second = createVenue(implementation, 3);

        first.reserveSeat(0);
        assertSeats(first, true, false, false);
        assertSeats(second, false, false, false);

        second.reserveSeat(2);
        assertSeats(first, true, false, false);
        assertSeats(second, false, false, true);

        first.cancelReservation(0);
        assertSeats(first, false, false, false);
        assertSeats(second, false, false, true);
    }
}