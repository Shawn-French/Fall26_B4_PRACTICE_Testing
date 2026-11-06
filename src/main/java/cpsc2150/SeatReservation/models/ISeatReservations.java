package cpsc2150.SeatReservation.models;

import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Tracks reservations for the numbered seats in one small venue.
 * <p>
 * Seats are numbered 0 through getSeatCount() - 1. Each seat is either available or
 * reserved. All seats are initially available, and the number of seats never changes.
 * <p>
 * In the contracts below, self means the entire structure, and #self means that state before the method call.
 * <p>
 * Seat numbers start at 0, so a venue of size 5 has positions 0 through 4. The array-backed form begins with five
 * false values, while the set-backed form begins empty.
 */
public interface ISeatReservations extends Iterable<Integer>{

  /**
   * Reports the venue's fixed capacity.
   * @return the fixed number of seats in this venue
   * @pre none
   * @post getSeatCount = the capacity established when the venue was created AND self = #self
   */
  int getSeatCount();

  /**
   * Determines whether the numbered range contains any open seats.
   * @return true exactly when no seats are available
   * @pre none
   * @post isSoldOut is true iff every seat number from 0 through getSeatCount() - 1 is reserved
   *     AND self = #self
   */
  default boolean isSoldOut()
  {
    for(int i = 0; i < getSeatCount(); i++)
    {
      if(!isReserved(i))
        return false;
    }
    return true;
  }

  /**
   * Checks the reservation status associated with the specified seat number.
   * @param aSeatNumber the seat number to check
   * @return true exactly when the specified seat is reserved
   * @pre 0 <= aSeatNumber && aSeatNumber < getSeatCount()
   * @post isReserved = the current status of aSeatNumber, <B>done without modifying
   * the state of self at all, even transitively</B> AND self = #self
   */
  boolean isReserved(int aSeatNumber);

  /**
   * Removes the specified seat's reservation, if one is present.
   * @param aSeatNumber the seat number whose reservation should be canceled
   * @return true if this call canceled a reservation; false if the seat was already available
   * @pre 0 <= aSeatNumber && aSeatNumber < getSeatCount()
   * @post aSeatNumber is available AND all other seats are unchanged AND cancelReservation is true
   *     exactly when aSeatNumber was reserved before the call
   */
  boolean cancelReservation(int aSeatNumber);

  /**
   * Tallies the seats whose current status leaves them open.
   * @return the number of available seats
   * @pre none
   * @post getAvailableSeatCount = the number of seat numbers from 0 through getSeatCount() - 1
   *     for which isReserved is false AND self = #self
   */
  default int getAvailableSeatCount()
  {
    int count = 0;
    for(int i = 0; i < getSeatCount(); i++)
    {
      if(!isReserved(i))
        count++;
    }
    return count;
  }

  /**
   * Records a reservation for the specified seat when it is not already reserved.
   * @param aSeatNumber the seat number to reserve
   * @return true if this call created a reservation; false if the seat was already reserved
   * @pre 0 <= aSeatNumber && aSeatNumber < getSeatCount()
   * @post aSeatNumber is reserved AND all other seats are unchanged AND reserveSeat is true
   *     exactly when aSeatNumber was available before the call
   */
  boolean reserveSeat(int aSeatNumber);

  /**
   * Prioritizes the venue's earliest remaining opening.
   * @return the number of the newly reserved seat, or -1 if no opening remains
   * @pre none
   * @post if any seat was available before the call, reserveFirstAvailable = the least available
   *     seat number AND that seat is now reserved AND all other seats are unchanged; otherwise,
   *     reserveFirstAvailable = -1 AND self = #self
   */
  default int reserveFirstAvailable()
  {
    int seatNum = -1;
    for(int i = 0; i < getSeatCount(); i++)
    {
      if (!isReserved(i))
      {
        seatNum = i;
        reserveSeat(i);
        break;
      }
    }
    return seatNum;
  }

  //--------------------------------------------------------------------------------------------------------

  //Ignore this method. If you know what it's for, then you can use it as you see fit. If you have no clue what this
  //is, ignore it!
  /**
   * An override of Iterable's primary iterator method. Normally used on a generic collection, since this isn't generic
   * we'll instead force augment it with an Integer, forcing our iterator to be the stream of ints (turned Integers)
   * between 0 and the number of seats in this SeatReservation.
   * <P>
   * This is needed to use for-each loops on this interface
   *
   * @return an iterator over this ISeatReservations
   */
  @Override
  default Iterator<Integer> iterator() {
    return IntStream.range(0, getSeatCount())
            .boxed()
            .iterator();
  }
}
