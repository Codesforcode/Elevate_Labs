import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * A Java program to demonstrate basic file operations:
 * 1. Writing to a file
 * 2. Reading from a file
 * 3. Modifying the content of a file
 */
public class FileOperations {

    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        try {
            // 1. Write to file
            writeToFile("Hello, this is the original content.\nThis file will be modified.\n");

            // 2. Read the file
            System.out.println("Original File Content:");
            readFromFile();

            // 3. Modify the file (replacing "original" with "updated")
            modifyFile("original", "updated");

            // 4. Read again after modification
            System.out.println("\nModified File Content:");
            readFromFile();

        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
        }
    }

    /**
     * Writes the given text to the file. Overwrites if file already exists.
     *
     * @param text The content to write.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeToFile(String text) throws IOException {
        Files.write(Paths.get(FILE_PATH), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Reads and prints the content of the file line by line.
     *
     * @throws IOException If an I/O error occurs.
     */
    public static void readFromFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Modifies the content of the file by replacing all occurrences of oldWord with newWord.
     *
     * @param oldWord Word to be replaced.
     * @param newWord New word to insert.
     * @throws IOException If an I/O error occurs.
     */
    public static void modifyFile(String oldWord, String newWord) throws IOException {
        Path path = Paths.get(FILE_PATH);
        String content = new String(Files.readAllBytes(path));
        content = content.replaceAll(oldWord, newWord);
        Files.write(path, content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
