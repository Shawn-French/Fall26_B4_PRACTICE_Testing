package cpsc2150.SeatReservation.models;

import java.util.Arrays;

/**
 * Stores reservations in a boolean array.
 * <p>
 * Remember a few things:
 *  A) arrays are not resizable
 *  B) when we construct the ArraySeatReservations, that initial aSeatCount is a capacity
 *  C) you can't set primitives to null, and "boolean" is a primitive type. This means an initial ArraySeatReservation
 *  of size 5 will look like {false, false, false, false, false} immediately after the constructor call
 */
public class ArraySeatReservations extends AbsSeatReservations {

  private final boolean[] reservedSeats;

  /**
   * Creates a venue with the specified number of seats, all initially available.
   *
   * @pre none
   * @post getSeatCount() = 5 AND every seat is available.
   */
  public ArraySeatReservations() {
    super();
    reservedSeats = new boolean[DEFAULT_SEAT_COUNT]; //in java, the primitive boolean defaults to false
  }

  /**
   * Creates a venue with the specified number of seats, all initially available.
   *
   * @param aSeatCount the number of seats in the venue
   * @pre none
   * @post if aSeatCount > 0, getSeatCount() = aSeatCount AND every seat is available; otherwise,
   *     an IllegalArgumentException is thrown and construction does not complete
   */
  public ArraySeatReservations(int aSeatCount) {
    super(aSeatCount);
    reservedSeats = new boolean[aSeatCount]; //in java, the primitive boolean defaults to false
  }

  // TODO: Add any method implementations that belong at this layer.




  //ignore this
  public String toString()
  {
    return Arrays.toString(this.reservedSeats);
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
    return reservedSeats[aSeatNumber];
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
    if(reservedSeats[aSeatNumber])
    {
      reservedSeats[aSeatNumber] = false;
      return true;
    }
    else
      return false;
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
    if(!reservedSeats[aSeatNumber])
    {
      reservedSeats[aSeatNumber] = true;
      return true;
    }
    else
      return false;
  }
}
