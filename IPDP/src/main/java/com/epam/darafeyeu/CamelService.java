package com.epam.darafeyeu;

/**
 * Created by xdar on 23.2.15.
 */

public interface CamelService {

    public void send(String message);

    public String receive();
}
