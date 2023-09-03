package com.example.homework3.StudentPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by: Aditi Gupta - argupta
 */

public class StudentReaderWriter {

    // Not much to do here
    /**
     * Constructs a new instance of the StudentReaderWriter class.
     * This constructor does not require any parameters.
     */
    public StudentReaderWriter( ) {
    }

    /** readRecords:
     *  Parameters: String filename - The name of the CSV file to read from
     *  Returns: Map containing Student objects with student IDs as keys and the student objects as values.
     *  Create a File object with the specified filename
     *  Declare a Scanner object for reading the file
     *  If the file is not found, print the stack trace and return null
     *  Next, read each line of the file
     *  Split on the line onto array of data using comma as the delimiter
     *  create a new UnderGraduate student or Graduate student object depending on the data[0]
     *  add the student to the map and return the map
     */

    public static Map<String, Student> readRecords(String filename){
        Map<String, Student> stdmap=new HashMap<>();
        File file = new File(filename);
        Scanner fileScanner=null;
        try{
            fileScanner=new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        while(fileScanner.hasNextLine()){
            String line=fileScanner.nextLine();
            String[] data = line.split(",");
            Student student= null;
            if (data[0].equalsIgnoreCase("U")){
                student =new UndergraduateStudent( data[1], data[2],data[3],data[4],data[6]);
            }
            else student=new GraduateStudent(data[1], data[2],data[3], data[4],data[6], data[7]);
            stdmap.put(student.getId(), student);
        }
        return stdmap;
    }



    /** writeRecords:
     *  Parameters: String filename, Map<String, Student> stdmap
     *  Returns: void
     *  Writes student records to a file
     *  create fileWriter object with the filename parameter given
     *  iterate over each student object in the stdmap
     *  Create a formatted string and write it to file
     *  Enter a new line character to separate records
     *  close the fileWriter to release resources
     */
    public static void writeRecords(String filename, Map<String, Student> stdmap){
        // Code this
        FileWriter fileWriter=null;
        String line;
        try {
            fileWriter=new FileWriter(filename);
            for (Student student:stdmap.values()){
                if(student instanceof UndergraduateStudent){
                    line=((UndergraduateStudent)student).csv();
                }
                else {
                    line=((GraduateStudent)student).csv();
                }
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
