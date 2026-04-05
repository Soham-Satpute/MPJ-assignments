import java.io.*;

public class FileException {

    public static void main(String[] args) {

        // =============================================
        // PART 1: Writing to a File
        // =============================================
        System.out.println("=== Writing to File ===");

        try {
            FileWriter fw = new FileWriter("student.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Name: John");
            bw.newLine();
            bw.write("Age: 20");
            bw.newLine();
            bw.write("Course: Computer Science");

            bw.close();
            System.out.println("Data written to file successfully!\n");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // =============================================
        // PART 2: Reading from a File
        // =============================================
        System.out.println("=== Reading from File ===");

        try {
            FileReader fr = new FileReader("student.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found! " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // =============================================
        // PART 3: Reading a File that does NOT exist
        // =============================================
        System.out.println("=== Trying to Read a Non-Existent File ===");

        try {
            FileReader fr = new FileReader("missing.txt");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("Caught Exception: File does not exist!");
            System.out.println("Details: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }

        System.out.println();

        // =============================================
        // PART 4: Arithmetic Exception (Divide by Zero)
        // =============================================
        System.out.println("=== Arithmetic Exception ===");

        try {
            int a = 10;
            int b = 0;
            int result = a / b;  // This will cause an error
            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println("Caught Exception: Cannot divide by zero!");
            System.out.println("Details: " + e.getMessage());
        }

        System.out.println();

        // =============================================
        // PART 5: Array Index Out of Bounds Exception
        // =============================================
        System.out.println("=== ArrayIndexOutOfBounds Exception ===");

        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);  // Index 5 doesn't exist

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught Exception: Array index does not exist!");
            System.out.println("Details: " + e.getMessage());
        }

        System.out.println();

        // =============================================
        // PART 6: Finally Block
        // =============================================
        System.out.println("=== Finally Block Demo ===");

        try {
            int x = 5 / 0;

        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());

        } finally {
            System.out.println("Finally block ALWAYS runs, with or without error!");
        }
    }
}