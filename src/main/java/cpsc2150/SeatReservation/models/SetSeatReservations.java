package cpsc2150.SeatReservation.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores the numbers of reserved seats in a set.
 *
 * If you've never heard of a set before, it's just a linear, unordered, collection that cannot store duplicate values.
 *
 * At least Java's HashSet implementation doesn't have index-based removal or addition, since they're not ordered. This
 * one is a generic augmented with an Integer, so just be aware that the int/Integer we're working with is the value
 * within the set, not the index any given value lives at (meaning we're not "starting at 0" in most cases here).
 *
 */
public class SetSeatReservations extends AbsSeatReservations {

  private final Set<Integer> reservedSeatNumbers;

  /**
   * Creates a venue with the specified number of seats, all initially available.
   *
   * @pre none
   * @post this.seatCount = 5 AND reservedSeatNumbers = new HashSet, representing that all seats are available
   * given that none are reserved
   */
  public SetSeatReservations() {
    super();
    reservedSeatNumbers = new HashSet<>();
  }

  /**
   * Creates a venue with the specified number of seats, all initially available.
   *
   * @param aSeatCount the number of seats in the venue
   * @pre none
   * @post this.seatCount = aSeatCount AND reservedSeatNumbers = new HashSet, representing that all seats are available
   * given that none are reserved
   */
  public SetSeatReservations(int aSeatCount) {
    super(aSeatCount);
    reservedSeatNumbers = new HashSet<>();
  }

  // TODO: Add any method implementations that belong at this layer.




  // ignore this
  public String toString()
  {
    return Arrays.toString(this.reservedSeatNumbers.toArray());
  }

  /**
   * Checks the reservation status associated with the specified seat number.
   *
   * @param aSeatNumber the seat number to check
   * @return true exactly when the specified seat is reserved
   * @pre 0 <= aSeatNumber && aSeatNumber < getSeatCount()
   * @post isReserved = the current status of aSeatNumber, <B>done without modifying
   * the state of self at all, even transitively</B> AND self = #self
   */
  @Override
  public boolean isReserved(int aSeatNumber) {
    return reservedSeatNumbers.contains(aSeatNumber);
  }

  /**
   * Removes the specified seat's reservation, if one is present.
   *
   * @param aSeatNumber the seat number whose reservation should be canceled
   * @return true if this call canceled a reservation; false if the seat was already available
   * @pre 0 <= aSeatNumber && aSeatNumber < getSeatCount()
   * @post aSeatNumber is available AND all other seats are unchanged AND cancelReservation is true
   * exactly when aSeatNumber was reserved before the call
   */
  @Override
  public boolean cancelReservation(int aSeatNumber) {
    return reservedSeatNumbers.remove(aSeatNumber);
  }

  /**
   * Records a reservation for the specified seat when it is not already reserved.
   *
   * @param aSeatNumber the seat number to reserve
   * @return true if this call created a reservation; false if the seat was already reserved
   * @pre 0 <= aSeatNumber && aSeatNumber < getSeatCount()
   * @post aSeatNumber is reserved AND all other seats are unchanged AND reserveSeat is true
   * exactly when aSeatNumber was available before the call
   */
  @Override
  public boolean reserveSeat(int aSeatNumber) {
    return reservedSeatNumbers.add(aSeatNumber);
  }
}
