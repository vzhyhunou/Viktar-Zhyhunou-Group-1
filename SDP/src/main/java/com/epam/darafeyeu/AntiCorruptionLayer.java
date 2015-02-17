package com.epam.darafeyeu;

import java.util.Date;

/**
 * Created by xdar on 17.2.15.
 */
public class AntiCorruptionLayer {

    private String filmName;
    private Ticket ticket;

    public AntiCorruptionLayer (String filmName){
        this.filmName = filmName;
        this.ticket = new Ticket();
    }

    public void reserve (int count) {
        ticket.reserve(filmName, count);
    }

    public void buy (int count) {
        ticket.buy (filmName, count);
    }

    public boolean isAvailable(Date date) {
        return ticket.isAvailable(filmName, date);
    }

}
