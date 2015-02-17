package com.epam.darafeyeu;

import java.util.Date;

/**
 * Created by xdar on 17.2.15.
 */
public class Ticket {


  void reserve (String filmName, int count) {
     // reserve code here
  }

  public void buy (String filmName, int count) {
     // buy code here
  }

  public Boolean isAvailable (String filmName, Date date) {

    if ( !filmName.equalsIgnoreCase( "Terminator" ) ) {
       return false;
    }

    if ( date.after(new Date()) ) {
       return false;
    } else {
       return true;
    }
  }

}
