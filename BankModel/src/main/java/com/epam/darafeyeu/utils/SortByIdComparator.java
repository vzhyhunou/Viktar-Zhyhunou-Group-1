package com.epam.darafeyeu.utils;

import com.epam.darafeyeu.domain.Person;

import java.util.Comparator;

/**
 * SortByIdComparator class to sort list of persons
 */

public class SortByIdComparator implements Comparator<Person> {

    public int compare(Person p1,Person p2){
        return p1.getId().compareTo(p2.getId());

    }
}

