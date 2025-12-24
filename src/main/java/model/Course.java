package model;

public class Course {
    private int id;
    private String title;
    private String description;
    private String instructorName;

    public Course(int id, String title, String description, String instructorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructorName = instructorName;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getInstructorName() { return instructorName; }
}