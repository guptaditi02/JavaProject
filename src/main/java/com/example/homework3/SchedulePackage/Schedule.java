/**
 * Created by: Aditi Gupta - argupta
 */
package com.example.homework3.SchedulePackage;

import java.util.ArrayList;
/**
 * Class Schedule takes semester and courselist
 * It contains a default constructor and an overloaded constructor
 * Contains getters and setters for all the parameters stated above
 * Method toString returns formatted string containing data fields
 */
public class Schedule {
    private String semester;
    private ArrayList<Course> courseList;

    public Schedule() {
        this.semester = "Fall 2021";
    }

    public Schedule(String semester, ArrayList<Course> courseList) {
        this.semester = semester;
        this.courseList = courseList;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = "Fall 2021";
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    /** addCourse:
     *  Parameters: Course course
     *  Returns: nothing
     *  Method addCourse news up the ArrayList if it is null
     *  Add the parameter course if it is not already present in the ArrayList
     */
    public void addCourse(Course course){
        if (courseList==null){
            courseList= new ArrayList<>();
        }
        if (!courseList.contains(course)){
            courseList.add(course);
        }
    }

    /** findCourse:
     *  Parameters: String id
     *  Returns: Course
     *  Method findCourse returns course object which has the id passed as a parameter
     */
    public Course findCourse(String id){
        for (Course c:courseList){
            if (c.getId().equalsIgnoreCase(id)){
                return c;
            }
        }
        return null;
    }
    /** deleteCourse:
     *  Parameters: String id
     *  Returns: nothing
     *  Method deleteCourse deletes the course object which has the id passed as a parameter
     */
    public void deleteCourse(String id){
        Course courseToDelete=null;
        for (Course c:courseList){
            if (c.getId().equalsIgnoreCase(id)){
                courseToDelete = c;
            }
        }
        courseList.remove(courseToDelete);
    }

    /** numberOfCourses:
     *  Parameters: none
     *  Returns: int
     *  Method numberOfCourses returns size of the courseList
     */

    public int numberOfCourses(){
        return courseList.size();
    }

    /** toString:
     *  Parameters: none
     *  Returns: String
     *  Method toString returns formatted string containing data fields
     */

    @Override
    public String toString() {
        String format = String.format("%10s", semester+"\n");
        format+=String.format("%10s %10s %15s %15s %8s %60s", "ID", "Days", "StartTime", "EndTime", "Units", "Name"+"\n");
        for(Course course: courseList){
            format+=course.toString()+"\n";
        }
        return format;
    }
}
