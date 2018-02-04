package directedstudy.georgiacentral.Objects;

public class Course {

    private int courseID;
    private String courseNumber;

    public Course(int courseID, String courseNumber) {
        this.courseID = courseID;
        this.courseNumber = courseNumber;
    }//Course

    public int getCourseID() {
        return courseID;
    }//getCourseID

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }//setCourseID

    public String getCourseNumber() {
        return courseNumber;
    }//getCourseNumber

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }//setCourseNumber

}//class
