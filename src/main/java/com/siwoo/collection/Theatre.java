package com.siwoo.collection;

import lombok.ToString;

import java.util.*;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-11 오후 7:42
 **/


public class Theatre {
    private final String name;
    final List<Seat> seats = new ArrayList<>();

    public Theatre(String name, int numPerRow, int numSeatsPerRow) {
        this.name = name;
        int lastRow = 'A' + (numPerRow - 1);
        for(char row='A'; row<=lastRow; row++) {
            for(int seatsPerRow=1;seatsPerRow<=numSeatsPerRow;seatsPerRow++) {
                Seat seat = new Seat(row + String.format("%02d", seatsPerRow));
                seats.add(seat);
            }
        }
    }

    public boolean reserveSeat(String seatNumber) {
//        Optional<Seat> requestedSeat = null;
//
//        for(Seat seat: seats) {
//            if(seatNumber.equals(seat.getSeatNumber())) {
//                requestedSeat = Optional.of(seat);
//                break;
//            }
//        }
//
//        return requestedSeat
//                .map(Seat::reserve)
//                .orElse(false);
//
//        Seat requestedSeat = new Seat(seatNumber);
//        int foundSeat = Collections.binarySearch(seats, requestedSeat);
//        if(foundSeat>=0) {
//            return seats.get(foundSeat).reserve();
//        } else {
//            System.out.println("Sorry, seat does not exists");
//            return false;
//        }
        int low = 0;
        int high = seats.size()-1;
        while (low<=high) {
            System.out.print(".");
            int mid = (low+high) / 2;
            Seat midSeat = seats.get(mid);
            int cmp = seatNumber.compareToIgnoreCase(midSeat.getSeatNumber());
            if(cmp<0) {
                high = mid - 1;
            } else if (cmp>0) {
                low = mid + 1;
            } else {
                return seats.get(mid).reserve();
            }
        }
        System.out.println("Sorry, seat does not exists");
        return false;
    }

    @ToString
    class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private boolean reserved = false;

        Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        boolean cancel() {
            if(this.reserved) {
                System.out.println("Seat " + seatNumber + " cancelled");
                this.reserved = false;
                return true;
            } else {
                return false;
            }
        }

        String getSeatNumber() {
            return this.seatNumber;
        }

        @Override
        public int compareTo(Seat seat) {
            return seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
    }

    public String getName() {
        return name;
    }


    public void getSeats() {
        for(Seat seat: seats) {
            System.out.println(seat.getSeatNumber());
        }
    }
}




















