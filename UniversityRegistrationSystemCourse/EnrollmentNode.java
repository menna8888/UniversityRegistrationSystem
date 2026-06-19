public class EnrollmentNode {
    public Student linkedStudent; 
    public Course linkedCourse; 
    public EnrollmentNode nextEnrollment; 

    public EnrollmentNode(Course courseInput) {
        this.linkedCourse = courseInput; 
        this.linkedStudent = null;
        this.nextEnrollment = null;
    }


    public EnrollmentNode(Student studentInput) { 
        this.linkedStudent = studentInput; 
        this.linkedCourse = null;
        this.nextEnrollment = null;
    }
    public EnrollmentNode() {
        this.linkedCourse = null;
        this.linkedStudent = null;
        this.nextEnrollment = null;
    }
}