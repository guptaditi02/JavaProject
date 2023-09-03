/**
 * Created by: Aditi Gupta - argupta
 */
package com.example.homework3.SchedulePackage;

import java.io.FileWriter;
import java.io.IOException;

public class ScheduleWriter {
    /** writeRecords:
     *  Parameters: String filename, Schedule schedule
     *  Returns: void
     *  Writes schedule record to a file
     *  create fileWriter object with the filename parameter given
     *  iterate over each course object in the courseList
     *  Create a formatted string and write it to file
     *  Enter a new line character to separate records
     *  close the fileWriter to release resources
     */
    public static void writeRecords(String filename, Schedule schedule){
        FileWriter fileWriter=null;
        String line;
        if (schedule.getCourseList().isEmpty()){
            return;
        }
        else{
            try {
                fileWriter=new FileWriter(filename);
                fileWriter.write(schedule.getSemester());
                fileWriter.write("\n");
                for (Course course : schedule.getCourseList()) {
                    line = course.csv();
                    fileWriter.write(line);
                    fileWriter.write("\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
