public class Course {
    public int courseID; 
    public EnrollmentNode enrolledStudentsHead;
    public EnrollmentNode enrolledStudentsTail; 
    public Course nextCourse; 

    public Course(int courseID) {
        this.courseID = courseID;
        this.enrolledStudentsHead = null;
        this.enrolledStudentsTail = null;
        this.nextCourse = null; 
    }
}