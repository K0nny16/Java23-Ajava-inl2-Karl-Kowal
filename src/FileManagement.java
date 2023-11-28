import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileManagement {

    private static String[][] arraySize;

    FileManagement(){
        arraySize = new String[3][0];
        try{
            File file = new File("Materiallista.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] array = line.split(",",3);
                arraySize= Arrays.copyOf(arraySize,arraySize.length+1);
                arraySize[arraySize.length-1]=array;
            }
        }catch(Exception e){
            System.out.println("Error:"+e.toString());
        }
    }
    static String[][] getarraySize(){
        return arraySize;
    }
}
