package cpsc2150.SeatReservation.models;

/**
 * Provides the construction and shared state used by the supplied venue implementations.
 */
public abstract class AbsSeatReservations implements ISeatReservations {

  private final int seatCount; //DO NOT CHANGE THIS TO PROTECTED (or public...)
  protected static final int DEFAULT_SEAT_COUNT = 5;

  /**
   * Initializes the fixed seat count shared by the supplied venue implementations.
   *
   * @pre none
   * @post seatCount = aSeatCount
   */
  protected AbsSeatReservations() {
    this.seatCount = DEFAULT_SEAT_COUNT;
  }

  /**
   * Initializes the fixed seat count shared by the supplied venue implementations.
   *
   * @param aSeatCount the number of seats in the venue
   * @pre seatCount > 0
   * @post seatCount = aSeatCount
   */
  protected AbsSeatReservations(int aSeatCount) {
    this.seatCount = aSeatCount;
  }

  // TODO: Add any method implementations that belong at this layer, should any exist.
  public int getSeatCount()
  {
    return seatCount;
  }
}
