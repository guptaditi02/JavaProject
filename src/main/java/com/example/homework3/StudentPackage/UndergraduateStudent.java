/**
 * Created by: Aditi Gupta - argupta
 */
package com.example.homework3.StudentPackage;

/**
 * The UndergraduateStudent class extends the Student class and represents an undergraduate student.
 * It adds the additional attribute "major" to the student's information.
 * The class provides constructors, getters, and setters for the major attribute.
 * It also overrides the toString() and csv() methods to provide formatted string representations.
 */
public class UndergraduateStudent extends Student{
    private String major;
    /**
     * Default constructor for the UndergraduateStudent class.
     * Initializes the major attribute as an empty string.
     */
    public UndergraduateStudent() {
        this.major =" ";
    }

    /**
     * Overloaded constructor with the parameters
     */
    public UndergraduateStudent(String lastName, String firstName, String id, String dateOfBirth, String major) {
        super(lastName, firstName, id, dateOfBirth);
        this.major = major;
    }

    /** getMajor:
     *  Parameters: none
     *  Returns: String, the value of major
     *  Standard getter function
     */
    public String getMajor() {
        return major;
    }

    /** setMajor:
     *  Parameters: String major
     *  Returns: void
     *  Standard setter function
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /** csv:
     *  Parameters: none
     *  Returns: String
     *  Method csv returns formatted string containing data fields to be stored in csv format
     */
    public String csv(){
        //edit
        String s="U,"+super.csv()+","+major;
        return s;
    }

    /** toString:
     *  Parameters: none
     *  Returns: String
     *  Method toString  returns formatted string containing data fields
     */
    public String toString() {
        // Code this edit
        String format = String.format("%4s", "U")+super.toString()+String.format("%17s", major);
        return format;
    }
}
