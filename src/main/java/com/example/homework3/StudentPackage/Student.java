package com.example.homework3.StudentPackage;
import com.example.homework3.SchedulePackage.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
/**
 * Created by: Aditi Gupta - argupta
 */

/**
 * Class Student takes lastName, firstName, id, dateOfBirth and age
 * It contains a default constructor and an overloaded constructor
 * Contains getters and setters for all the parameters stated above except age
 * computeAge method computes age based on DoB and today's date
 * Method toString returns formatted string containing data fields
 */
// One student record
public abstract class Student implements Comparable<Student>{
    protected String lastName;
    protected String firstName;
    protected String id;
    protected String dateOfBirth;
    protected int age;
    protected Schedule schedule = null;
    protected String filename=null+".csv";

    // Default constructor
    public Student( ) {
        this.id = "";
        this.lastName = "";
        this.firstName = "";
        this.dateOfBirth = "";
        this.schedule=null;
    }

    // Overloaded constructor
    public Student(String lastName, String firstName, String id, String dateOfBirth) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        computeAge( );
        String filename = this.id + ".csv";
        schedule = ScheduleReader.readRecords(filename);
    }
    // Getter
    /** getLastName:
     *  Parameters: none
     *  Returns: String, the value of lastName
     *  Standard getter function
     */
    public String getLastName() {
        return lastName;
    }

    // Setter
    /** setLastName:
     *  Parameters: String lastName
     *  Returns: void
     *  Standard setter function
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** getFirstName:
     *  Parameters: none
     *  Returns: String, the value of firstName
     *  Standard getter function
     */
    public String getFirstName() {
        return firstName;
    }

    // Setter
    /** setFirstName:
     *  Parameters: String firstName
     *  Returns: void
     *  Standard setter function
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** getId:
     *  Parameters: none
     *  Returns: String, the value of id
     *  Standard getter function
     */
    public String getId() {
        return id;
    }

    /** setId:
     *  Parameters: String id
     *  Returns: void
     *  Standard setter function
     */
    public void setId(String id) {
        this.id = id;
    }

    /** getDateOfBirth:
     *  Parameters: none
     *  Returns: String, the value of dateOfBirth
     *  Standard getter function
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /** setDateOfBirth:
     *  Parameters: String dateOfBirth
     *  Returns: void
     *  Standard setter function
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        computeAge();
    }
    // Compute age based on DoB and today's date
    /** computeAge:
     *  Parameters: none
     *  Returns: void
     *  Parse the dateOfBirth string into a LocalDate object
     *  get the current date
     *  calculate the period between today startDate and endDate
     *  get the no. of years in the period and assign it to age variable
     */
    private void computeAge( ) {
        // Code this
        LocalDate startDate = LocalDate.parse(dateOfBirth);
        LocalDate endDate = LocalDate.now();
        Period period = Period.between(startDate, endDate);
        this.age= period.getYears();
    }
    /** getAge:
     *  Parameters: none
     *  Returns: int, the value of age
     *  Standard getter function
     */
    public int getAge() {
        return age;
    }

    /** toString:
     *  Parameters: none
     *  Returns: String
     *  Method toString returns formatted string containing data fields
     */

    //@Override
    public String toString() {
        // Code this
        String format = String.format("%15s %15s %8s %15s %6d", lastName, firstName, id, dateOfBirth, age);
        return format;
    }
    /**
     * Retrieves the schedule of the student.
     * Returns: The schedule object representing the student's schedule.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets the schedule of the student.
     * Parameters: schedule - The new schedule object to be assigned to the student.
     * Returns: nothing
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Compares this student's ID with another student's ID.
     * Parameter: The student object to be compared.
     * Return: A negative integer, zero, or a positive integer if this student's ID is less than, equal to, or greater than the other student's ID, respectively.
     */
    @Override
    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }

    /**
     * News up the schedule object if new schedule is created
     * Return: nothing
     */
    public void createNewSchedule(){
        schedule=new Schedule();
    }

    /** csv:
     *  Parameters: none
     *  Returns: String
     *  Method csv returns formatted string containing data fields to be stored in csv format
     */
    public String csv(){
        String s=lastName+","+firstName+","+id+","+dateOfBirth+","+age;
        return s;
    }
}
