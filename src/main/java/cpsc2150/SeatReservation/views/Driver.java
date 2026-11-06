package cpsc2150.SeatReservation.views;

import cpsc2150.SeatReservation.models.ArraySeatReservations;
import cpsc2150.SeatReservation.models.ISeatReservations;
import cpsc2150.SeatReservation.models.SetSeatReservations;

/** Simple examples for checking either implementation through the same interface. */
public class Driver {

  /**
   * Use this however you see fit...
   */
  public static void main(String[] args) {
    System.out.println("-----Array Implementation Primitive Testing-----");
    ISeatReservations sr = new ArraySeatReservations(5);
    System.out.println("Initial State: " + sr);
    System.out.println("Total number of seats: " + sr.getSeatCount());
    sr.reserveSeat(2);
    sr.reserveSeat(0);
    System.out.println("State after reserving seat 2 and 0: " + sr);
    sr.reserveFirstAvailable();
    System.out.println("State after reserving first available seat: " + sr);
    System.out.println("Does the system allow double-reserving seat 2: " + sr.reserveSeat(2));
    System.out.println("Does the system allow cancelling a reservation that DNE: " + sr.cancelReservation(4));
    System.out.println("How many seats remain after 3 reservations, starting with 5 available: " + sr.getAvailableSeatCount());
    sr.cancelReservation(1);
    System.out.println("State after cancelling seat reservation 1: " + sr);
    sr.reserveSeat(1);
    sr.reserveSeat(3);
    sr.reserveSeat(4);
    System.out.println("State after reserving all seats: " + sr);
    System.out.println("Are we sold out: " + sr.isSoldOut());
    System.out.println("-----Array Implementation Primitive Testing Finished-----\n");

    System.out.println("-----HashSet Implementation Primitive Testing-----");
    sr = new SetSeatReservations(5);
    System.out.println("Initial State: " + sr);
    System.out.println("Total number of seats: " + sr.getSeatCount());
    sr.reserveSeat(2);
    sr.reserveSeat(0);
    System.out.println("State after reserving seat 2 and 0: " + sr);
    sr.reserveFirstAvailable();
    System.out.println("State after reserving first available seat: " + sr);
    System.out.println("Does the system allow double-reserving seat 2: " + sr.reserveSeat(2));
    System.out.println("Does the system allow cancelling a reservation that DNE: " + sr.cancelReservation(4));
    System.out.println("How many seats remain after 3 reservations, starting with 5 available: " + sr.getAvailableSeatCount());
    sr.cancelReservation(1);
    System.out.println("State after cancelling seat reservation 1: " + sr);
    sr.reserveSeat(1);
    sr.reserveSeat(3);
    sr.reserveSeat(4);
    System.out.println("State after reserving all seats: " + sr);
    System.out.println("Are we sold out: " + sr.isSoldOut());
    System.out.println("-----HashSet Implementation Primitive Testing Finished-----\n");

  }
}
