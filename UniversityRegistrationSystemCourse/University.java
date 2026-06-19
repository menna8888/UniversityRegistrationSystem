public class University {

    Student studentsHead; 
    Student studentsTail; 
    Course coursesHead; 
    Course coursesTail; 

    public University() { 
        this.studentsHead = null;
        this.studentsTail = null;
        this.coursesHead = null;
        this.coursesTail = null;
    }

    // ===== fun 1 add Student =====
    public void addStudent(int studentID) {  
        if (studentIsExist(studentID) != null) { 
            System.out.println("Error: this student already exists.");
            return;
        }
        
        Student newStudent = new Student(studentID); 
        if (studentsHead == null && studentsTail == null) {
            studentsHead = newStudent;
            studentsTail = newStudent;
        } else { 
            studentsTail.nextStudent = newStudent; 
            studentsTail = newStudent;
        }
        System.out.println("Student with id: "+studentID+" added successfully!" );
    }

    // ===== fun 2 add Course =====              
    public void addCourse(int courseID) {
        if (courseIsExist(courseID) != null) {
            System.out.println("Error: this course already exists.");
            return;
        }
        Course newCourse = new Course(courseID);
        if (coursesTail == null&& coursesHead==null) {
            coursesHead = newCourse;
            coursesTail = newCourse;
        } else {
            coursesTail.nextCourse = newCourse;
            coursesTail = newCourse;
        }
        System.out.println("Course with id:"+courseID+"added successfully." );
    }

    // ===== Fun 3 remove Student =====
    public void removeStudent(int studentID) {



        if (studentsHead == null) {
            System.out.println("Student list is empty.");
            return;
        }
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        EnrollmentNode e = student.enrolledCoursesHead;
        while (e != null) {
            int cid = e.linkedCourse.courseID;
            e = e.nextEnrollment;
            removeEnrollment(studentID, cid);
        }

        if (studentsHead == studentsTail && studentsHead.studentID == studentID) {
            studentsHead = studentsTail = null;
        } else if (studentsHead.studentID == studentID) {
            studentsHead = studentsHead.nextStudent;
        } else {
            Student pred = studentsHead, tmp = studentsHead.nextStudent;
            while (tmp != null && tmp.studentID != studentID) {
                pred = tmp; tmp = tmp.nextStudent;
            }
            if (tmp == null) {
                System.out.println("Student not found.");
                return;
            }
            pred.nextStudent = tmp.nextStudent;
            if (tmp == studentsTail) studentsTail = pred;
        }
        System.out.println("Student removed: " + studentID);
    }

    // ===== Fun 4 remove Course =====
    public void removeCourse(int courseID) {
        if (coursesHead == null) {
            System.out.println("Course list is empty.");
            return;
        }
        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        EnrollmentNode e = course.enrolledStudentsHead;
        while (e != null) {
            int sid = e.linkedStudent.studentID;
            e = e.nextEnrollment;
            removeEnrollment(sid, courseID);
        }

        if (coursesHead == coursesTail && coursesHead.courseID == courseID) {
            coursesHead = coursesTail = null;
        } else if (coursesHead.courseID == courseID) {
            coursesHead = coursesHead.nextCourse;
        } else {
            Course pred = coursesHead, tmp = coursesHead.nextCourse;
            while (tmp != null && tmp.courseID != courseID) {
                pred = tmp; tmp = tmp.nextCourse;
            }
            if (tmp == null) {
                System.out.println("Course not found.");
                return;
            }
            pred.nextCourse = tmp.nextCourse;
            if (tmp == coursesTail) coursesTail = pred;
        }
        System.out.println("Course removed: " + courseID);
    }

    // ===== Helper Methods =====
    public Student studentIsExist(int studentID) {
        Student current = studentsHead;
        while (current != null) {
            if (current.studentID == studentID) return current;
            current = current.nextStudent;
        }
        return null;
    }

    public Course courseIsExist(int courseID) {
        Course current = coursesHead;
        while (current != null) {
            if (current.courseID == courseID) return current;
            current = current.nextCourse;
        }
        return null;
    }

    public boolean isNormalStudent(int studentID) {
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }
        int count = 0;
        EnrollmentNode ptr = student.enrolledCoursesHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }
        return (count >= 2 && count <= 7);
    }

    public boolean isFullCourse(int courseID) {
        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }
        int count = 0;
        EnrollmentNode ptr = course.enrolledStudentsHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }
        return (count >= 30);
    }

        // ===== Fun 5 remove Enrollment =====
    public void removeEnrollment(int studentID, int courseID) {
        Student student = studentIsExist(studentID);
        Course course = courseIsExist(courseID);

        if (student == null || course == null) {
            System.out.println("Invalid student or course.");
            return;
        }
        EnrollmentNode prev = null; 
        EnrollmentNode current = student.enrolledCoursesHead; 

        while (current != null) {
            if (current.linkedCourse == course) {
                break; 
            }
            prev = current;
            current = current.nextEnrollment;
        }
        if (current == null) {
            System.out.println("The student did not enroll in this course.");
            return;
        }
        if (student.enrolledCoursesHead == current && student.enrolledCoursesTail == current) {
            student.enrolledCoursesHead = null;
            student.enrolledCoursesTail = null;
        }
        else if (student.enrolledCoursesHead == current) {
            student.enrolledCoursesHead = current.nextEnrollment;
        }
        else if (student.enrolledCoursesTail == current) {
            student.enrolledCoursesTail = prev; 
        }
        else {
            prev.nextEnrollment = current.nextEnrollment; 
        }
        System.out.println("The course with ID " + course.courseID + " has been successfully removed from the enrolled courses of student with ID " + student.studentID);

        EnrollmentNode prev2 = null;
        EnrollmentNode current2 = course.enrolledStudentsHead; 

        while (current2 != null) {
            if (current2.linkedStudent == student) {
                break;
            }
            prev2 = current2;
            current2 = current2.nextEnrollment;
        }
        if (current2 == null) {
            return;
        }
        if (course.enrolledStudentsHead == current2 && course.enrolledStudentsTail == current2) {
            course.enrolledStudentsHead = null;
            course.enrolledStudentsTail = null;
        }
        else if (course.enrolledStudentsHead == current2) {
            course.enrolledStudentsHead = current2.nextEnrollment; 
        }
        else if (course.enrolledStudentsTail == current2) {
            course.enrolledStudentsTail = prev2; 
            prev2.nextEnrollment = null; 
        }
        else {
            prev2.nextEnrollment = current2.nextEnrollment;
        }
    }
    // ===== Fun 6 enrollStudent =====
    public void enrollStudent(int studentID, int courseID) {
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("The student does not exist.");
            return;
        }
        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        EnrollmentNode currentCourse = student.enrolledCoursesHead;
        while (currentCourse != null) {
            if (currentCourse.linkedCourse.courseID == courseID) {
                System.out.println("Student is already enrolled in this course.");
                return;
            }
            currentCourse = currentCourse.nextEnrollment;
        }
        EnrollmentNode currentStudent = course.enrolledStudentsHead;
        while (currentStudent != null) {
            if (currentStudent.linkedStudent.studentID == studentID) {
                System.out.println("This course already contains this student.");
                return;
            }
            currentStudent = currentStudent.nextEnrollment;
        }
        int studentCoursesCount = 0;
        EnrollmentNode tempCourse = student.enrolledCoursesHead;
        while (tempCourse != null) {
            studentCoursesCount++;
            tempCourse = tempCourse.nextEnrollment;
        }
        if (studentCoursesCount >= 7) {
            System.out.println("Student can only enroll in a maximum of 7 courses.");
            return;
        }

        int courseStudentsCount = 0;
        EnrollmentNode tempStudent = course.enrolledStudentsHead;
        while (tempStudent != null) {
            courseStudentsCount++;
            tempStudent = tempStudent.nextEnrollment;
        }
        if (courseStudentsCount >= 30) {
            System.out.println("This course is already full.");
            return;
        }

        // ---- Link course to student's course list ----
        EnrollmentNode courseEnrollment = new EnrollmentNode(course);
        if (student.enrolledCoursesHead == null) {
            student.enrolledCoursesHead = courseEnrollment;
            student.enrolledCoursesTail = courseEnrollment;
        } else {
            student.enrolledCoursesTail.nextEnrollment = courseEnrollment;
            student.enrolledCoursesTail = courseEnrollment;
        }
        // ---- Link student to course's student list ----
        EnrollmentNode studentEnrollment = new EnrollmentNode(student);
        if (course.enrolledStudentsHead == null) {
            course.enrolledStudentsHead = studentEnrollment;
            course.enrolledStudentsTail = studentEnrollment;
        } else {
            course.enrolledStudentsTail.nextEnrollment = studentEnrollment;
            course.enrolledStudentsTail = studentEnrollment;
        }
        System.out.println("Enrollment successful: Student " + studentID + " enrolled in Course " + courseID);
    }
    // ===== Fun sortStudentsByCourseId =====
    public void sortStudentsByCourseId(int courseID) {
        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        int count = 0;
        EnrollmentNode ptr = course.enrolledStudentsHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }
        if (count == 0) {
            System.out.println("No students enrolled in this course.");
            return;
        }
        if (count == 1) {
            System.out.println("Only one student enrolled. No need to sort.");
            printStudents(course.enrolledStudentsHead);
            return;
        }
        bubbleSortEnrollments(course.enrolledStudentsHead);
        course.enrolledStudentsTail = getEnrollmentTail(course.enrolledStudentsHead);
        System.out.println("Students sorted by ID for Course ID: " + courseID);
        printStudents(course.enrolledStudentsHead);
    }
    private void printStudents(EnrollmentNode head) {
        EnrollmentNode ptr = head;
        if (ptr == null) {
            System.out.println("No students enrolled in this course.");
            return;
        }
        while (ptr != null) {
            System.out.println("Student ID: " + ptr.linkedStudent.studentID);
            ptr = ptr.nextEnrollment;
        }
    }
    private void bubbleSortEnrollments(EnrollmentNode head) {
        boolean swapped;
        do {
            swapped = false;
            EnrollmentNode curr = head;
            while (curr.nextEnrollment != null) {
                if (curr.linkedStudent.studentID > curr.nextEnrollment.linkedStudent.studentID) {
                    Student tmp = curr.linkedStudent;
                    curr.linkedStudent = curr.nextEnrollment.linkedStudent;
                    curr.nextEnrollment.linkedStudent = tmp;
                    swapped = true;
                }
                curr = curr.nextEnrollment;
            }
        } while (swapped);
    }
    private EnrollmentNode getEnrollmentTail(EnrollmentNode head) {
        EnrollmentNode curr = head;
        while (curr.nextEnrollment != null) {
            curr = curr.nextEnrollment;
        }
        return curr;
    }
    // ===== Fun sortCoursesByIDForStudent =====
    public void sortCoursesByIDForStudent(int studentID) {
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        int count = 0;
        EnrollmentNode ptr = student.enrolledCoursesHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }
        if (count == 0) {
            System.out.println("Course not found.");
            return;
        }
        if (count == 1) {
            EnrollmentNode onlyEnrollment = student.enrolledCoursesHead;
            int onlyCourseId = onlyEnrollment.linkedCourse.courseID;
            System.out.println("Only one course enrolled. \nCourse ID: " + onlyCourseId);
            return;
        }
        student.enrolledCoursesHead = sortCourseListByID(student.enrolledCoursesHead);
        student.enrolledCoursesTail = getTail(student.enrolledCoursesHead);

        System.out.println("Courses sorted successfully for student ID: " + studentID);
        System.out.println("Displaying sorted courses for student ID: " + studentID);
        EnrollmentNode currentEnrollment = student.enrolledCoursesHead;
        while (currentEnrollment != null) {
            System.out.println("- Course ID: " + currentEnrollment.linkedCourse.courseID);
            currentEnrollment = currentEnrollment.nextEnrollment;
        }
    }
    private EnrollmentNode sortCourseListByID(EnrollmentNode head) {
        EnrollmentNode dummy = new EnrollmentNode();
        EnrollmentNode current = head;

        while (current != null) {
            EnrollmentNode next = current.nextEnrollment;

            EnrollmentNode prev = dummy;
            while (prev.nextEnrollment != null
                    && prev.nextEnrollment.linkedCourse.courseID
                    < current.linkedCourse.courseID) {
                prev = prev.nextEnrollment;
            }
            current.nextEnrollment = prev.nextEnrollment;
            prev.nextEnrollment = current;

            current = next;
        }
        return dummy.nextEnrollment;
    }

    private EnrollmentNode getTail(EnrollmentNode head) {
        if (head == null) return null;
        EnrollmentNode curr = head;
        while (curr.nextEnrollment != null) {
            curr = curr.nextEnrollment;
        }
        return curr;
    }
    // ===== Fun listStudentsByCourse =====
    public void listStudentsByCourse(int courseID) {
        Course currentCourse = coursesHead;
        while (currentCourse != null) {
            if (currentCourse.courseID == courseID) {
                break;
            }
            currentCourse = currentCourse.nextCourse;
        }

        if (currentCourse == null) {
            System.out.println("Course with id " + courseID + " not found.");
            return;
        }

        if (currentCourse.enrolledStudentsHead == null) {
            System.out.println("No students enrolled in course " + courseID);
            return;
        }

        System.out.print("Students for course " + courseID + ": ");
        EnrollmentNode currentEnrollment = currentCourse.enrolledStudentsHead;
        while (currentEnrollment != null) {

            if (currentEnrollment.linkedStudent != null) {
                System.out.print(currentEnrollment.linkedStudent.studentID + " ");
            }
            currentEnrollment = currentEnrollment.nextEnrollment;
        }
        System.out.println();
    }
    // ===== Fun listCoursesByStudent =====
    public void listCoursesByStudent(int studentID) {

        Student currentStudent = studentsHead;
        while (currentStudent != null) {
            if (currentStudent.studentID == studentID) {
                break;
            }
            currentStudent = currentStudent.nextStudent;
        }

        if (currentStudent == null) {
            System.out.println("Student with id " + studentID + " not found.");
            return;
        }

        if (currentStudent.enrolledCoursesHead == null) {
            System.out.println("Student with id " + studentID + " is not enrolled in any courses.");
            return;
        }

        System.out.print("Courses for student " + studentID + ": ");
        EnrollmentNode currentEnrollment = currentStudent.enrolledCoursesHead;
        while (currentEnrollment != null) {

            if (currentEnrollment.linkedCourse != null) {
                System.out.print(currentEnrollment.linkedCourse.courseID + " ");
            }
            currentEnrollment = currentEnrollment.nextEnrollment;
        }
        System.out.println();
    }
    // ===== Fun GetLaststudentAdded =====
    public int GetLaststudentAdded() {
        if (studentsHead == null) {
            System.out.println("No students in the system.");
            return 0;
        }
        Student currentStudent = studentsHead;
        Student lastStudentWithEnrollment = null;
        while (currentStudent != null) {
            if (currentStudent.enrolledCoursesTail != null) {
                lastStudentWithEnrollment = currentStudent;
            }
            currentStudent = currentStudent.nextStudent;
        }
        if (lastStudentWithEnrollment != null) {
            return lastStudentWithEnrollment.studentID;
        } else {
            System.out.println("No Student has enrolled in any course.");
            return 0; 
        }
    }
        // ===== Fun GetLastCourseAdded =====
    public int GetLastCourseAdded(){
        Course currentCourse =coursesHead;
        Course LastCourse= null;

        while(currentCourse != null){
            if(currentCourse.enrolledStudentsTail != null){  
                LastCourse =currentCourse;
            }
            currentCourse = currentCourse.nextCourse;
        }
        if(LastCourse !=null){
            return LastCourse.courseID;}
        else{
            System.out.println("No Course has any enrollments.");
            return 0;
        }
    }
}