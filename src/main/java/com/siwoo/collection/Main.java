package com.siwoo.collection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-11 오후 7:39
 **/


public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian", 8, 12);
        theatre.getSeats();
        assertNotNull(theatre);

        if(theatre.reserveSeat("H11")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
            fail();
        }

        if(theatre.reserveSeat("H11")) {
            System.out.println("Please pay");
            fail();
        } else {
            System.out.println("Sorry, seat is taken");
        }

        //Shallow copy
        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
        assertNotNull(seatCopy);
        printList(seatCopy);

        seatCopy.get(5).reserve();
        if(theatre.reserveSeat("A06")) {
            fail();
        } else {
            System.out.println("Sorry, seat is taken");
        }

        Collections.shuffle(seatCopy);
        printList(seatCopy);
        printList(theatre.seats);
    }

    public static void printList(List<Theatre.Seat> seats) {
        for(Theatre.Seat seat: seats) {
            System.out.print(" " + seat.getSeatNumber());
        }
        System.out.println();
        System.out.println("=====================================================================================================================");
    }
}
