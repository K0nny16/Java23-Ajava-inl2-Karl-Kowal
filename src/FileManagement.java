import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManagement {

    private static ArrayList<String[]> arraySize;

    FileManagement(String URL) {
        arraySize = new ArrayList<>();
        try {
            File file = new File(URL);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] array = line.split(",");
                arraySize.add(array);
            }
            scanner.close(); // Glöm inte att stänga skannern när du är klar med den.
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    static ArrayList<String[]> getArraySize() {
        return arraySize;
    }
}
