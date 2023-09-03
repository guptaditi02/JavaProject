/**
 * Created by: Aditi Gupta - argupta
 */
package com.example.homework3.StudentPackage;

/**
 * The GraduateStudent class extends the Student class and represents a Graduatestudent.
 * It adds the additional attributes "internship" and "startDate" to the student's information.
 * The class provides constructors, getters, and setters for "internship" and "startDate" attributes.
 * It also overrides the toString() and csv() methods to provide formatted string representations.
 */
public class GraduateStudent extends Student{
    String internship, startDate;

    public GraduateStudent() {
        this.internship="";
        this.startDate="";
    }

    public GraduateStudent(String lastName, String firstName, String id, String dateOfBirth, String internship, String startDate) {
        super(lastName, firstName, id, dateOfBirth);
        this.internship = internship;
        this.startDate = startDate;
    }

    /** getInternship:
     *  Parameters: none
     *  Returns: String, the value of internship
     *  Standard getter function
     */
    public String getInternship() {
        return internship;
    }

    /** setMajor:
     *  Parameters: String internship
     *  Returns: void
     *  Standard setter function
     */
    public void setInternship(String internship) {
        this.internship = internship;
    }

    /** getStartDate:
     *  Parameters: none
     *  Returns: String, the value of startDate
     *  Standard getter function
     */
    public String getStartDate() {
        return startDate;
    }

    /** setStartDate:
     *  Parameters: String startDate
     *  Returns: void
     *  Standard setter function
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /** csv:
     *  Parameters: none
     *  Returns: String
     *  Method csv returns formatted string containing data fields to be stored in csv format
     */
    public String csv(){
        String s="G,"+super.csv()+","+internship+","+startDate;
        return s;
    }

    /** toString:
     *  Parameters: none
     *  Returns: String
     *  Method toString  returns formatted string containing data fields
     */
    public String toString() {
        // Code this
        String format = String.format("%4s", "G") + super.toString()+ String.format("%17s %8s", internship,startDate);
        return format;
    }
}
