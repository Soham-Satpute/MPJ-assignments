package Training.Training;
import java.util.*;

class Student {

    private String name;
    private int rollNo;
    private int marks[];
    private double percentage;

    Student(String name, int rollNo, int marks[]) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public void calculatePercentage() {
        int total = 0;
        for (int i = 0; i < marks.length; i++) {
            total += marks[i];
        }
        percentage = total / (double) marks.length;
    }

    public double getPercentage() {
        return percentage;
    }

    public void displayResult() {
        System.out.println("\n------------------------------");
        System.out.println("Name       : " + name);
        System.out.println("Roll No    : " + rollNo);

        System.out.print("Marks      : ");
        for (int m : marks) {
            System.out.print(m + " ");
        }

        System.out.println("\nPercentage : " + percentage);

        if (percentage >= 75)
            System.out.println("Grade      : A");
        else if (percentage >= 60)
            System.out.println("Grade      : B");
        else
            System.out.println("Grade      : C");
    }
}

public class Assi1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        Student[] students = new Student[n];

        double totalClassPercentage = 0;
        int totalMarksAllStudents = 0;

        int[] subjectTotals = new int[5]; // To store total marks of each subject

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details of Student " + (i + 1));

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Roll No: ");
            int rollNo = sc.nextInt();

            int[] marks = new int[5];
            int studentTotal = 0;

            System.out.println("Enter marks of 5 subjects:");
            for (int j = 0; j < 5; j++) {
                System.out.print("Subject " + (j + 1) + ": ");
                marks[j] = sc.nextInt();

                studentTotal += marks[j];      // Student total
                subjectTotals[j] += marks[j];  // Subject total
            }
            sc.nextLine();

            totalMarksAllStudents += studentTotal;

            students[i] = new Student(name, rollNo, marks);
            students[i].calculatePercentage();
            totalClassPercentage += students[i].getPercentage();
        }

        // Display Results
        System.out.println("\nSTUDENT RESULTS");
        for (Student s : students) {
            s.displayResult();
        }

        // Class Average Percentage
        double classAverage = totalClassPercentage / n;
        System.out.println("\nClass Average Percentage = " + classAverage);

        // 🔹 Average Total Marks of All Students
        double averageTotalMarks = totalMarksAllStudents / (double) n;
        System.out.println("Average Total Marks of Students = " + averageTotalMarks);

        // 🔹 Subject-wise Average
        System.out.println("\nSubject-wise Average Marks:");
        for (int i = 0; i < 5; i++) {
            double subjectAverage = subjectTotals[i] / (double) n;
            System.out.println("Subject " + (i + 1) + " Average = " + subjectAverage);
        }

        sc.close();
    }

}