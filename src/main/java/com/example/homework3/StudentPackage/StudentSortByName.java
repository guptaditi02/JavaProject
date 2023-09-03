package com.example.homework3.StudentPackage;

import java.util.Comparator;
/**
 * Created by: Aditi Gupta - argupta
 */

/**
 * The StudentSortByName class implements the Comparator interface and provides a comparison logic for sorting students by name.
 * It compares students based on their last names first. If the last names are the same, it further compares based on first names.
 * The compare() method has the comparison logic to compare two Student objects.
  */
public class StudentSortByName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getLastName().equals(o2.getLastName())){
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
        else return o1.getLastName().compareTo(o2.getLastName());
    }
}
