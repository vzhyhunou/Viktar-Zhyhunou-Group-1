package com.epam.darafeyeu;

import java.util.Date;

/**
 * Created by xdar on 17.2.15.
 */
public class MainClass {

    public static void main (String[] args) {

        // Ticket object is hidden inside AntiCorruptionLayer, making "main code"
        // reasonably clean and free of corruption that would be brought
        // there by direct usage of Ticket stuff
        AntiCorruptionLayer antiCorruptionLayer =
             new AntiCorruptionLayer("Terminator");

        if ( antiCorruptionLayer.isAvailable(new Date()) ) {

            antiCorruptionLayer.reserve(2);
            antiCorruptionLayer.buy(2);

        }

    }

}
