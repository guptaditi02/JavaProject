/**
 * Created by: Aditi Gupta - argupta
 */
package com.example.homework3.SchedulePackage;
/**
 * Class Course takes name, id, days, startTime, endTime
 * It contains a default constructor and an overloaded constructor
 * Contains getters and setters for all the parameters stated above
 * Contains csv() method that returns a string like ones present in 424.csv file
 * Method toString returns formatted string containing data fields
 */
public class Course {
    private String name, id, days, startTime, endTime;
    private int units;
    public Course() {
        this.name="";
        this.id="";
        this.days="";
        this.startTime="";
        this.endTime="";
    }

    public Course(String name, String id, String days, String startTime, String endTime, int units) {
        this.name = name;
        this.id = id;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
    /** csv:
     *  Parameters: none
     *  Returns: String
     *  Method csv returns formatted string containing data fields to be stored in csv format
     */

    public String csv(){
        String s= name+","+id+","+days+","+startTime+","+endTime+","+units;
        return s;
    }

    /** toString:
     *  Parameters: none
     *  Returns: String
     *  Method toString returns formatted string containing data fields
     */

    public String toString() {
        // Code this
        String format = String.format("%10s %10s %15s %15s %8s %60s", id, days, startTime, endTime, units, name);
        return format;
    }
}
