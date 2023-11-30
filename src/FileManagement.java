import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;


public class FileManagement {

    private static List<String[]> filData;
    private static String[] header;
    private static String[][] rows;

    FileManagement(String URL) {
        filData = new ArrayList<>();
        try {
            File file = new File(URL);              //Inputar path stringen till en ny file.
            String[] extension = URL.split("\\.");  //Förslaget regx av intelliJ.
            if(Objects.equals(extension[1],"csv"))
                CSV(file);
            else if(Objects.equals(extension[1],"json"))
                JSON(file);
            else{
               JOptionPane.showMessageDialog(null,"Filformatet du valde stöds inte av programmet!","Error!",JOptionPane.INFORMATION_MESSAGE,null);  //Felmeddelande ifall man försöker något filformat som inte är csv/json.
               System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        header = filData.get(0);                                                //Plockar ut det första värdet i Listan för det kommer vara headern.
        rows = filData.stream().skip(1).toArray(String[][]::new);               //Tar data från List och streamar genom den och skippar det första värdet i den eftersom att det är min header och jag vill inte ha den 2 gånger och gör det till en 2D String array som jag sedan spara i 2D arrayn rows.
    }
    private void JSON(File file) throws Exception {                     //Måste finns en try/catch eller throw för att FileReader ska funka.
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(file);
        Object obj = parser.parse(reader);                                      //Parsar filen och spara den som ett Object (obj)
        JSONArray jsonArray = (JSONArray) obj;

        for (Object json : jsonArray) {                                  //Loopar igenom varje element i jsonArray
            JSONObject jsonObject = (JSONObject) json;                   //Castar om det till ett JsonObjekt.
            List<String> rowData = new ArrayList<>();                    //Lagrar värdena av varje nyckel i jsonObject. Vilket i detta fallet är A, B, C, D, E, F, G, H

            for (Object key : jsonObject.keySet())                       //Loopar igenom varje nyckel i objektet.
                rowData.add(jsonObject.get(key).toString());             //Lägger till värdet av varje nyckel i listan som skapades innan. (Påminner lite om HashMaps)
            filData.add(rowData.toArray(new String[0]));               //Lägger till det i "main" ArrayListen.
        }
        reader.close();                                             //Stänger filreadern med för att undvika memoryleaks.
    }
    private void CSV(File file) throws Exception{
        Scanner scanner = new Scanner(file);                        //Scannar filen
        while(scanner.hasNext()){                                   //Loopar så länge det finns saker att scanna.
            String line = scanner.nextLine();                       //Gör varje line till en string.
            String[] array = line.split(",");                       //Splitar den Stringen vi varje komma och sätter det i en array
            filData.add(array);                                   //Lägger till Arrayn i en ArrayList.
        }
        scanner.close();                                            //Stänger scannern för att undvika memoryleaks.
    }
    static String[] getHeader(){
        return header;
    }
    static String[][] getRows(){
        return rows;
    }
}
