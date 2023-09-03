package com.example.homework3;
import com.example.homework3.SchedulePackage.*;
import com.example.homework3.StudentPackage.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import java.io.*;
import java.io.File;

/**
 * Created by: Aditi Gupta - argupta
 */

public class Hw3Driver extends Application {
    // Student to be created
    static Student s;
    static Course course;
    public static Map<String, Student> studentMap;
    // Create a StudentReaderWriter object
    private static StudentReaderWriter studentReaderWriter;
    private static StudentRecordProcessor studentRecordProcessor;
    private static Label idLabel,firstName,lastName,DOB, age, type1, opt1, opt2,status;
    private static TextField idText, firstText, lastText, dobText, ageText, typeText, opt1Text, opt2Text,statusText;
    private static Button search, newStudent, enter;
    private static TableView<Course> tableView;

    public static void main(String[] args) {
        launch();
    }

    /** start:
     *  Parameters: Stage
     *  Returns: void
     *  Entry point for the JavaFX application.
     *  Sets up the GUI for application, which allows the user to manage student records and view their schedules.
     *  Initializes various JavaFX UI components, such as menus, labels, text fields, buttons, and a table view.
     *  Defines event handlers for menu items and buttons to perform various actions,
     *  such as opening/saving files, searching for students, adding new students, and entering student data.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        studentMap = new HashMap<>(); // Initialize the studentMap

        // Create instances of StudentRecordProcessor and StudentReaderWriter
        studentRecordProcessor = new StudentRecordProcessor(studentMap);
        studentReaderWriter = new StudentReaderWriter();


        // Set up the JavaFX UI components

        //GridPane used to organize the labels, text fields, and buttons.
        GridPane gridPane = new GridPane();

        //SplitPane used to show details and schedule of the student together
        SplitPane splitPane;


        // Create File menu
        Menu fileMenu = new Menu("File");

        //Create menu items for that File menu
        MenuItem aboutMenuItem = new MenuItem("About");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem quitMenuItem = new MenuItem("Quit");

        // Add File menu items
        fileMenu.getItems().addAll(aboutMenuItem, openMenuItem, saveMenuItem, quitMenuItem);

        // Create Schedule menu
        Menu scheduleMenu = new Menu("Schedule");

        //Create menu item for that Schedule menu
        MenuItem openScheduleMenuItem = new MenuItem("Open Schedule");

        // Add Schedule menu items
        scheduleMenu.getItems().add(openScheduleMenuItem);

        // Create MenuBar and add menus
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, scheduleMenu);

        /** A label is used to represent the field name in the user interface.
         *  Used to indicate where the user should input the corresponding field of a student.
         */

        idLabel = new Label("ID: ");
        firstName= new Label("First Name:");
        lastName = new Label("Last Name:");
        DOB= new Label("DOB:");
        age= new Label("Age:");
        type1= new Label("Type:");
        opt1= new Label("Opt1:");
        opt2= new Label("Opt2:");
        status= new Label("Status:");

        // Adding labels to the gridPane
        gridPane.add(menuBar, 0, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(lastName, 0, 2);
        gridPane.add(firstName, 0, 3);
        gridPane.add(DOB, 0, 4);
        gridPane.add(age, 0, 5);
        gridPane.add(type1, 0, 6);
        gridPane.add(opt1, 0, 7);
        gridPane.add(opt2, 0, 8);
        gridPane.add(status, 0, 9);

        /** Description: A text field used for entering different fields of a student.
         Usage: The user can input the corresponding input of a student in this field.
         */

        idText=new TextField();
        firstText=new TextField();
        lastText=new TextField();
        dobText=new TextField();
        ageText=new TextField();
        typeText=new TextField();
        opt1Text=new TextField();
        opt2Text=new TextField();
        statusText=new TextField();

        // Adding TextFields to the gridPane
        gridPane.add(idText, 1, 1);
        gridPane.add(lastText, 1, 2);
        gridPane.add(firstText, 1, 3);
        gridPane.add(dobText, 1, 4);
        gridPane.add(ageText, 1, 5);
        gridPane.add(typeText, 1, 6);
        gridPane.add(opt1Text, 1, 7);
        gridPane.add(opt2Text, 1, 8);
        gridPane.add(statusText, 1, 9);

        //Creating buttons for different functionality
        search=new Button("Search by ID");
        newStudent=new Button("New Student");
        enter=new Button("Enter Data");
        enter.setDisable(true);

        //Adding the buttons to the gridPane
        gridPane.add(search, 2, 1);
        gridPane.add(newStudent, 2, 3);
        gridPane.add(enter, 2, 5);

        //The TableView is used to display information about the courses of a specific student in tabular form.
        tableView=new TableView<>();

        // Create TableColumns and associate them with the corresponding properties of the Course class
        TableColumn<Course, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Course, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Course, String> daysColumn = new TableColumn<>("Days");
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));

        TableColumn<Course, String> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn<Course, String> endTimeColumn = new TableColumn<>("End Time");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        TableColumn<Course, String> unitsColumn = new TableColumn<>("Units");
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("units"));

        // Add the TableColumns to the TableView
        tableView.getColumns().addAll(nameColumn,idColumn, daysColumn, startTimeColumn, endTimeColumn, unitsColumn);

        // Check if there are courses for the student and set a placeholder if none exist
        if(course==null) {
            tableView.setPlaceholder(new Label("No courses for this student"));
        }else {
            // Add a Course object to the TableView if at least one course exists
            course=new Course();
            tableView.getItems().add(course);
        }

        // Event handlers for menu items
        aboutMenuItem.setOnAction(e -> aboutFile(primaryStage));
        openMenuItem.setOnAction(e -> openFile(primaryStage));
        saveMenuItem.setOnAction(e -> saveFile(primaryStage));
        quitMenuItem.setOnAction(e -> primaryStage.close());
        openScheduleMenuItem.setOnAction(e -> openSchedule(primaryStage));

        // Event handler for "Search by ID" button
        search.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                // Search for the student with the entered ID in the studentMap
                Student std= StudentRecordProcessor.searchByID(idText.getText());

                // Clear the data from the tableView
                ObservableList<Course> dataList = tableView.getItems();
                dataList.clear();

                // If the student is found in the studentMap: retrieve the student's schedule
                if(std!=null){
                    Schedule studentSchedule = std.getSchedule();

                    // If the student has a schedule, populate the tableView with the student's courses
                    if(studentSchedule!= null){
                        for ( Course course:studentSchedule.getCourseList()){
                            tableView.getItems().add(course);
                        }
                    }

                    // Fill the text fields with the student's details
                    idText.setText(std.getId());
                    lastText.setText(std.getLastName());
                    firstText.setText(std.getFirstName());
                    dobText.setText(std.getDateOfBirth());
                    ageText.setText(Integer.toString(std.getAge()));
                    statusText.setText("STUDENT FOUND");

                    // Check the student type and set the appropriate details in the optional text fields
                    if(std instanceof UndergraduateStudent){
                        typeText.setText("Undergraduate");
                        opt1Text.setText(((UndergraduateStudent) std).getMajor());
                        opt2Text.setText(null);
                    }
                    else{
                        typeText.setText("Graduate");
                        opt1Text.setText(((GraduateStudent)std).getInternship());
                        opt2Text.setText(((GraduateStudent)std).getStartDate());
                    }
                }
                else{
                    // If the student is not found, clear the input fields and show a message
                    clearFields();
                    statusText.setText("NOT FOUND");

                    // Set a placeholder in the tableView to display a message
                    tableView.setPlaceholder(new Label("No courses for this student"));
                }
            }
        });

        // Event handler for "New Student" button
        newStudent.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                // Clear the data from the tableView
                ObservableList<Course> dataList = tableView.getItems();
                dataList.clear();

                // Clear all input fields
                clearFields();

                // Set the status text to indicate data entry
                statusText.setText("ENTER DATA");

                // Disable the "Search by ID" and "New Student" buttons to prevent conflicts during data entry
                search.setDisable(true);
                newStudent.setDisable(true);

                // Enable the "Enter Data" button to allow the user to input new student information
                enter.setDisable(false);
            }
        });

        // Event handler for "Enter Data" button
        enter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                // Clear the data from the tableView
                ObservableList<Course> dataList = tableView.getItems();
                dataList.clear();

                // Retrieve the entered data from the input fields
                String idEntered=idText.getText();
                String lastnameEntered=lastText.getText();
                String firstnameEntered=firstText.getText();
                String dobEntered =dobText.getText();
                String typeEntered=typeText.getText();
                String opt1Entered=opt1Text.getText();
                String opt2Entered= opt2Text.getText();

                // Set the status text to indicate data entry
                statusText.setText("ENTER DATA");

                // If any required field is blank, clear all fields and show an error message
                if (idEntered.isBlank() || lastnameEntered.isBlank() || firstnameEntered.isBlank() || dobEntered.isBlank() || typeEntered.isBlank() || opt1Entered.isBlank()){
                    clearFields();
                    statusText.setText("BAD DATA");
                }
                // If the data is valid, create a new student object and add it to the studentMap
                else{
                    // If the student type is "Undergraduate", create an UndergraduateStudent object
                    if (typeEntered.equalsIgnoreCase("Undergraduate")){
                        s=new UndergraduateStudent();
                        ((UndergraduateStudent)s).setMajor(opt1Entered);

                        // Set the properties of the new student
                        s.setId(idEntered);
                        s.setLastName(lastnameEntered);
                        s.setFirstName(firstnameEntered);
                        s.setDateOfBirth(dobEntered);

                        // Add the new student to the studentMap using StudentRecordProcessor.append()
                        StudentRecordProcessor.append(s, studentMap);
                        statusText.setText("NEW STUDENT ENTERED");
                    }
                    // If the student type is "Graduate", create a GraduateStudent object
                    else if (typeEntered.equalsIgnoreCase("Graduate")){
                        s=new GraduateStudent();
                        if(opt2Entered.isBlank()){
                            clearFields();
                            statusText.setText("BAD DATA");
                            return;
                        }
                        else{

                            // Set the properties of the new student
                            s.setId(idEntered);
                            s.setLastName(lastnameEntered);
                            s.setFirstName(firstnameEntered);
                            s.setDateOfBirth(dobEntered);

                            ((GraduateStudent)s).setInternship(opt1Entered);
                            ((GraduateStudent)s).setStartDate(opt2Entered);

                            // Add the new student to the studentMap using StudentRecordProcessor.append()
                            StudentRecordProcessor.append(s, studentMap);
                            statusText.setText("NEW STUDENT ENTERED");
                        }

                    }
                    else{
                        clearFields();
                        statusText.setText("BAD DATA");
                    }

                }

                // After data entry is completed, disable "Enter Data" button and enable "Search by ID" and "New Student" buttons
                enter.setDisable(true);
                search.setDisable(false);
                newStudent.setDisable(false);
            }
        });

        // Add gridPane and tableView to the splitPane and set the orientation of splitPane
        splitPane=new SplitPane(gridPane,tableView);
        splitPane.setOrientation(Orientation.VERTICAL);

        // Add splitPane to the scene
        // Set up the primary stage
        Scene scene1 = new Scene(splitPane, 480, 600);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Homework 3");
        primaryStage.show();

    }

    /** clearFields:
     *  Parameters: none
     *  Returns: void
     *  Clearing the input fields on the user interface.
     *  Clears the text content of various JavaFX TextFields used to input data related to a student record.
     */
    private void clearFields() {
        idText.clear();
        lastText.clear();
        firstText.clear();
        dobText.clear();
        ageText.clear();
        typeText.clear();
        opt1Text.clear();
        opt2Text.clear();
        statusText.clear();
    }

    /** aboutFile:
     *  Parameters: Stage - The stage (window) to which the dialog will be attached.
     *  Returns: void
     *  This method displays an "About" dialog box providing information about the application.
     *  Creates a dialog window with a message and an "Okay" button for the user to acknowledge the information.
     */
    private void aboutFile(Stage stage) {
        ButtonType okButton = new ButtonType("Okay");
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().setContentText("Homework 3 by Aditi Gupta");
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.showAndWait();
    }

    /** openFile:
     *  Parameters: Stage -The stage (window) to which the FileChooser dialog will be attached.
     *  Returns: void
     *  Allows the user to open a student records file.
     *  Uses a FileChooser dialog to prompt the user to select a file.
     *  After selecting a file, reads the contents and populates the studentMap with the student records in the file.
     */
    private void openFile(Stage stage) {
        clearFields();
        Map<String, Student> newStudentMap;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Student Records File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    newStudentMap = studentReaderWriter.readRecords(file.getAbsolutePath());
                    if(newStudentMap == null || newStudentMap.size()==0)
                    {
                        statusText.setText("FILE NOT LOADED");
                        return;
                    }
                    studentMap.putAll(newStudentMap);
                    studentRecordProcessor = new StudentRecordProcessor(studentMap);
                }
                statusText.setPromptText(studentMap.size() + " RECORDS LOADED");


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error occurred while reading the file.");
            }
        }

    }

    /** saveFile:
     *  Parameters: Stage -The stage (window) to which the FileChooser dialog will be attached.
     *  Returns: void
     *  Allows the user to save the current student records data to a file.
     *  Displays a FileChooser dialog to prompt the user to choose the location and name for the file.
     *  Writes the studentMap data to the selected file using the studentReaderWriter object.
     *  */
    private void saveFile(Stage stage) {
        if (studentMap != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Student Records File");
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                studentReaderWriter.writeRecords(file.getAbsolutePath(), studentMap);
            }
        }
    }

    /** openSchedule:
     *  Parameters: Stage
     *  Returns: void
     *  Allows the user to view the schedule for a specific student.
     *  Displays a new window containing a TextArea with the schedule information for the student,
     *  whose ID is entered in the "idText" TextField.
     *  */
    private void openSchedule(Stage stage) {
        Stage scheduleStage = new Stage();
        scheduleStage.initModality(Modality.APPLICATION_MODAL);
        scheduleStage.initOwner(stage);
        TextArea scheduleTextArea = new TextArea();
        scheduleTextArea.setEditable(false);
        Student student = studentRecordProcessor.searchByID(idText.getText());
        if(student!=null){
            Schedule schedule = student.getSchedule();
            if(schedule!= null){
                scheduleTextArea.setText(schedule.toString());
            }
            else{
                scheduleTextArea.setText("No schedule found for given student");
            }
            scheduleTextArea.setStyle("-fx-alignment: TOP-LEFT");

        }
        else{
            scheduleTextArea.setText("No student and schedule found");
        }
        Scene scheduleScene = new Scene(scheduleTextArea, 400, 300);
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();

    }
}
