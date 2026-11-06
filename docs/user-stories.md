# Seat Reservations: User Stories and Acceptance Criteria

The seat-reservation component tracks reservations for numbered seats in one
venue. Seats are numbered from 0 through the venue's seat count minus 1. Each
seat is either available or reserved, and the number of seats never changes
after construction.

The component has two implementations: `ArraySeatReservations` and
`SetSeatReservations`. Unless a criterion identifies a particular implementation,
the required behavior applies to both.

Seat-specific operations require a valid seat number. The stories do not define
behavior for seat numbers outside the documented range. Successful construction
is described for positive seat counts; US-08 separately states the rejection
behavior documented for the array-backed constructor.

## US-01: Create a venue

As a developer, I can create a reservation component with a specified number of
seats so that it can represent venues of different sizes.

- **US-01-AC-01:** Given a positive seat count, when a reservation component is
  created, then its reported seat count equals the supplied count.
- **US-01-AC-02:** Given a newly created reservation component, when each seat's
  status is inspected, then every seat is available.
- **US-01-AC-03:** Given a seat count of 1, when a reservation component is
  created, then it contains exactly one available seat, numbered 0.

## US-02: Inspect a seat

As a venue attendant, I can check whether a particular seat is reserved so that
I can determine whether it is available for a guest.

- **US-02-AC-01:** Given an available seat with a valid number, when its
  reservation status is requested, then `isReserved` returns false.
- **US-02-AC-02:** Given a reserved seat with a valid number, when its reservation
  status is requested, then `isReserved` returns true.
- **US-02-AC-03:** Given any valid seat, when its reservation status is requested,
  then every seat's reservation status and the venue's seat count are unchanged.

## US-03: Reserve a specific seat

As a venue attendant, I can reserve a particular available seat so that a guest
can receive the seat they requested.

- **US-03-AC-01:** Given an available seat with a valid number, when
  `reserveSeat` is called for it, then the seat becomes reserved and the method
  returns true.
- **US-03-AC-02:** Given an already-reserved seat with a valid number, when
  `reserveSeat` is called for it again, then it remains reserved and the method
  returns false.
- **US-03-AC-03:** Given a valid seat, when `reserveSeat` is called for it, then
  all other seats retain their previous reservation statuses and the venue's
  seat count is unchanged.

## US-04: Cancel a reservation

As a venue attendant, I can cancel a seat's reservation so that the seat can
be offered to another guest.

- **US-04-AC-01:** Given a reserved seat with a valid number, when
  `cancelReservation` is called for it, then the seat becomes available and the
  method returns true.
- **US-04-AC-02:** Given an already-available seat with a valid number, when
  `cancelReservation` is called for it, then it remains available and the method
  returns false.
- **US-04-AC-03:** Given a valid seat, when `cancelReservation` is called for it,
  then all other seats retain their previous reservation statuses and the
  venue's seat count is unchanged.

## US-05: Count available seats

As a venue attendant, I can find out how many seats remain available so that
I can tell guests how many openings remain.

- **US-05-AC-01:** Given any combination of available and reserved seats, when
  `getAvailableSeatCount` is called, then it returns the number of available
  seats.
- **US-05-AC-02:** Given a venue with every seat available, when
  `getAvailableSeatCount` is called, then it returns the venue's seat count.
- **US-05-AC-03:** Given a venue with every seat reserved, when
  `getAvailableSeatCount` is called, then it returns 0.
- **US-05-AC-04:** Given any reservation state, when `getAvailableSeatCount` is
  called, then all reservation statuses and the venue's seat count are unchanged.

## US-06: Reserve the first available seat

As a venue attendant, I can automatically reserve the lowest-numbered available
seat so that I can assign a seat to a guest who has no preference.

- **US-06-AC-01:** Given at least one available seat, when
  `reserveFirstAvailable` is called, then it returns the lowest available seat
  number and that seat becomes reserved.
- **US-06-AC-02:** Given at least one available seat, when
  `reserveFirstAvailable` is called, then every other seat retains its previous
  reservation status and the venue's seat count is unchanged.
- **US-06-AC-03:** Given that every seat is reserved, when
  `reserveFirstAvailable` is called, then it returns -1 and all reservation
  statuses and the venue's seat count are unchanged.

## US-07: Check whether the venue is sold out

As a venue attendant, I can check whether the venue is sold out so that I know
whether any additional reservations can be accepted.

- **US-07-AC-01:** Given that every seat is reserved, when `isSoldOut` is called,
  then it returns true.
- **US-07-AC-02:** Given at least one available seat, when `isSoldOut` is called,
  then it returns false.
- **US-07-AC-03:** Given any reservation state, when `isSoldOut` is called, then
  all reservation statuses and the venue's seat count are unchanged.

## US-08: Reject unsupported array-backed venue sizes

As a developer using `ArraySeatReservations`, I can have nonpositive seat counts
rejected so that I do not receive a venue with an unsupported capacity.

- **US-08-AC-01:** Given a seat count of 0, when an `ArraySeatReservations` is
  constructed, then an `IllegalArgumentException` is thrown and construction
  does not complete.
- **US-08-AC-02:** Given a negative seat count, when an `ArraySeatReservations`
  is constructed, then an `IllegalArgumentException` is thrown and construction
  does not complete.
