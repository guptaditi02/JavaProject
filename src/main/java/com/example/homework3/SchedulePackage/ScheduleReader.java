/**
 * Created by: Aditi Gupta - argupta
 */
package com.example.homework3.SchedulePackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleReader {
    /** readRecords:
     *  Parameters: String filename
     *  Returns: Schedule object with all details of the schedule of a particular student
     *  Create a new ArrayList to store the course records
     *  Create a File object with the specified filename
     *  Declare a Scanner object for reading the file
     *  If the file is not found, print the stack trace and return an empty ArrayList
     *  Next, read each line of the file
     *  Split on the line onto array of data using comma as the delimiter
     *  create a new course object with the data array items
     *  add the course to arraylist
     *  close the scanner to free up resources
     *  return the new Schedule object with its parameters
     */
    public static Schedule readRecords(String filename) {
        ArrayList<Course> courseList = new ArrayList<>();
        File file = new File(filename);
        Scanner fileScanner=null;
        try{
            fileScanner=new Scanner(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        String semester = fileScanner.nextLine();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split(",");
            String name = data[0];
            String id = data[1];
            String days = data[2];
            String startTime = data[3];
            String endTime = data[4];
            int units = Integer.parseInt(data[5]);

            Course course = new Course(name, id, days, startTime, endTime, units);
            courseList.add(course);
        }

        fileScanner.close();
        return new Schedule(semester, courseList);
    }
}