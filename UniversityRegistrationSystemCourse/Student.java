public class Student {
    public int studentID;  
    public EnrollmentNode enrolledCoursesHead;   
    public EnrollmentNode enrolledCoursesTail;  
    public Student nextStudent; 

    public Student(int studentID) { 
        this.studentID = studentID; 
        this.enrolledCoursesHead = null; 
        this.enrolledCoursesTail = null;
        this.nextStudent = null; 
    }
}