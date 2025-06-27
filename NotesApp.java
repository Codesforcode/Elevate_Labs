import java.io.*;
import java.util.Scanner;

public class NotesApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String fileName = "notes.txt"; // File to store notes

        while (true) {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Write Note (Append)");
            System.out.println("2. Overwrite Notes");
            System.out.println("3. Read Notes");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();  // Consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your note: ");
                    String note = sc.nextLine();
                    writeNote(fileName, note, true); // Append mode
                    break;

                case 2:
                    System.out.print("Enter your note (Overwrites existing notes): ");
                    String overwriteNote = sc.nextLine();
                    writeNote(fileName, overwriteNote, false); // Overwrite mode
                    break;

                case 3:
                    readNotes(fileName);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Method to write notes
    public static void writeNote(String fileName, String note, boolean append) {
        try (FileWriter fw = new FileWriter(fileName, append)) {  // try-with-resources
            fw.write(note + "\n");
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Method to read notes
    public static void readNotes(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n--- Saved Notes ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("-------------------");
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. File doesn't exist.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
