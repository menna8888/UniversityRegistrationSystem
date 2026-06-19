import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University system = new University();
        Stack<Integer> undo = new Stack<>();
        Stack<Integer> redo = new Stack<>();

        int enStudentID = -1, enCourseID = -1, unenStudentID = -1, unenCourseID = -1;

        boolean exit = false;
        while (!exit) {
            System.out.println("\n====== University Registration System ======");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Remove Student");
            System.out.println("4. Remove Course");
            System.out.println("5. Get Last Student Added");
            System.out.println("6. Get Last Course Added");
            System.out.println("7. Enroll Student in a course");
            System.out.println("8. Remove Enrollment (Unenroll student from a course)");
            System.out.println("9. List Courses by Student");
            System.out.println("10. List Students by Course");
            System.out.println("11. Sort Courses by Student ID");
            System.out.println("12. Sort Students by Course ID");
            System.out.println("13. Check if Course is Full");
            System.out.println("14. Check if Student is Normal");
            System.out.println("15. Undo last enrollment");
            System.out.println("16. Redo last enrollment");
            System.out.println("0. Exit the system");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    system.addStudent(studentID);
                    break;

                case 2:
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    system.addCourse(courseID);
                    break;

                case 3:
                    System.out.print("Enter student ID to remove: ");
                    int removeStudentID = scanner.nextInt();
                    system.removeStudent(removeStudentID);
                    break;

                case 4:
                    System.out.print("Enter course ID to remove: ");
                    int removeCourseID = scanner.nextInt();
                    system.removeCourse(removeCourseID);
                    break;

                case 5:
                    System.out.println("Last st added is " + system.GetLaststudentAdded());
                    break;

                case 6:
                    System.out.println("Last course added is " + system.GetLastCourseAdded());

                    break;

                case 7:
                    System.out.println("Enter student ID:");
                    enStudentID = scanner.nextInt();
                    System.out.println("Enter course ID:");
                    enCourseID = scanner.nextInt();
                    system.enrollStudent(enStudentID, enCourseID);

                    undo.push(7);

                    break;

                case 8:
                    System.out.print("Enter student ID to unenroll: ");
                    unenStudentID = scanner.nextInt();
                    System.out.print("Enter course ID to unenroll from: ");
                    unenCourseID = scanner.nextInt();
                    system.removeEnrollment(unenStudentID, unenCourseID);

                    undo.push(8);

                    break;

                case 9:
                    System.out.print("Enter student ID to list courses: ");
                    int listCoursesStudentID = scanner.nextInt();
                    system.listCoursesByStudent(listCoursesStudentID);
                    break;

                case 10:
                    System.out.print("Enter course ID to list students: ");
                    int listStudentsCourseID = scanner.nextInt();
                    system.listStudentsByCourse(listStudentsCourseID);
                    break;

                case 11:
                    System.out.println("Enter Student ID:");
                    int lsStudentID = scanner.nextInt();
                    system.sortCoursesByIDForStudent(lsStudentID);
                    break;

                case 12:
                    System.out.println("Enter Course ID:");
                    int lsCourseID = scanner.nextInt();
                    system.sortStudentsByCourseId(lsCourseID);
                    break;

                case 13:
                    System.out.print("Enter course ID to check if full: ");
                    int checkCourseID = scanner.nextInt();
                    if (system.isFullCourse(checkCourseID)) {
                        System.out.println("Course is full.");
                    } else {
                        System.out.println("Course is not full.");
                    }
                    break;

                case 14:
                    System.out.print("Enter student ID to check if normal: ");
                    int checkStudentID = scanner.nextInt();
                    if (system.isNormalStudent(checkStudentID)) {
                        System.out.println("Student has normal enrollment (between 2 and 7 courses).");
                    } else {
                        System.out.println("Student does NOT have normal enrollment.");
                    }
                    break;

                case 15:
                    if (undo.isEmpty()) {
                        System.out.println("Cannot undo.");
                    } else if (undo.pop() == 7) {
                        system.removeEnrollment(enStudentID, enCourseID);
                    } else {
                        system.enrollStudent(unenStudentID, unenCourseID);
                    }
                    break;
                case 16:
                    if (undo.isEmpty()) {
                        System.out.println("Cannot redo.");
                    } else {
                        redo.push(undo.pop());
                        if (redo.pop() == 7) {
                            system.enrollStudent(enStudentID, enCourseID);
                        } else {
                            system.removeEnrollment(unenStudentID, unenCourseID);
                        }
                    }
                    break;

                case 0:
                    exit = true;
                    System.out.println("See you later...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}