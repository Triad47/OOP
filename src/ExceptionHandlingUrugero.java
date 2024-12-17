import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation demonstrates exception handling in Java through practical scenarios.
 * Approach:
 * - Each method simulates a specific exception in a realistic context
 * - Proper resource management using try-catch-finally blocks where appropriate
 * - Detailed error messages for debugging and understanding
 * - Structured handling of both checked and unchecked exceptions
 * - Focus on common real-world scenarios developers encounter
 */
public class ExceptionHandlingUrugero {

    public static void main(String[] args) {
        ExceptionHandlingUrugero urugero = new ExceptionHandlingUrugero();

        urugero.handleIOException();
        urugero.handleFileNotFoundException();
        urugero.handleEOFException();
        urugero.handleSQLException();
        urugero.handleClassNotFoundException();
        urugero.handleArithmeticException();
        urugero.handleNullPointerException();
        urugero.handleArrayIndexOutOfBounds();
        urugero.handleClassCastException();
        urugero.handleIllegalArgumentException();
        urugero.handleNumberFormatException();
    }

    private void handleIOException() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("temp.txt"));
            writer.write("Some text");
            writer.close();
            writer.write("More text");
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing writer: " + e.getMessage());
            }
        }
    }

    private void handleFileNotFoundException() {
        FileReader reader = null;
        try {
            reader = new FileReader("nonexistent_file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException: File does not exist - " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }

    private void handleEOFException() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(new byte[0]));
            dis.readInt();
        } catch (EOFException e) {
            System.out.println("Caught EOFException: Reached end of file - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing stream: " + e.getMessage());
            }
        }
    }

    private void handleSQLException() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nonexistent_db",
                    "user", "pass"
            );
        } catch (SQLException e) {
            System.out.println("Caught SQLException: Database connection failed - " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    private void handleClassNotFoundException() {
        try {
            Class.forName("com.nonexistent.Class");
        } catch (ClassNotFoundException e) {
            System.out.println("Caught ClassNotFoundException: Class not found in classpath - " + e.getMessage());
        }
    }

    private void handleArithmeticException() {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: Division by zero - " + e.getMessage());
        }
    }

    private void handleNullPointerException() {
        try {
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: Attempted to call method on null object - " + e.getMessage());
        }
    }

    private void handleArrayIndexOutOfBounds() {
        try {
            int[] arr = new int[5];
            arr[10] = 25;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5 - " + e.getMessage());
        }
    }

    private void handleClassCastException() {
        try {
            Object obj = Integer.valueOf(123);  // Using valueOf instead of deprecated constructor
            String str = (String) obj;
        } catch (ClassCastException e) {
            System.out.println("Caught ClassCastException: Cannot cast Integer to String - " + e.getMessage());
        }
    }

    private void handleIllegalArgumentException() {
        try {
            List<String> list = new ArrayList<>();
            list.add(null);
            list.sort(String::compareTo);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: Cannot sort list containing null elements - " + e.getMessage());
        }
    }

    private void handleNumberFormatException() {
        try {
            int number = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: String 'abc' cannot be parsed to integer - " + e.getMessage());
        }
    }
}