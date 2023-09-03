package com.example.homework3.StudentPackage;
import java.util.Map;
/**
 * Created by: Aditi Gupta - argupta
 */

/** Class StudentRecordProcessor contains a Map of Student objects.
 * Its constructor sets that map.
 * This class contains methods for searchByID and append.
 */
public class StudentRecordProcessor {
    private static Map<String, Student> stdmap;

    /**StudentRecordProcessor:
     * Overloaded constructor StudentRecordProcessor set a Map of Student objects.
     * Parameters: stdmap- Map containing student records, where key: student IDs and value: Student object.
     */
    public StudentRecordProcessor(Map<String, Student> stdmap) {
        this.stdmap = stdmap;
    }

    /** searchByID:
     *  Parameters: String id
     *  Returns: Student object associated with the provided ID, or null if no student is found with the given ID.
     */
    public static Student searchByID(String id){
        for(String k: stdmap.keySet()){
            if(k.equalsIgnoreCase(id)){
                return stdmap.get(k);
            }
        }
        return null;
    }

    /** append:
     *  Parameters: s-Student object to be appended to the student data map.
     *              studentMap - Map containing student records, where key:student ID and value: Student object.
     *  Returns: void
     *  Appends a new student record to the provided student data map.
     */

    public static void append(Student s, Map<String, Student> studentMap){
        studentMap.put(s.getId(),s);
    }

}
