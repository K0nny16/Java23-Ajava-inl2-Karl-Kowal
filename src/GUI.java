import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GUI {

    private JTable table;
    private JPanel panel;
    private static String URL;

    GUI() {
        filePicker();
        FileManagement fil = new FileManagement(URL);   //Instansierar klassen FileManager och skickar med Stringen URL till konstruktorn.
        JFrame frame = new JFrame();
        panel = new JPanel();
        frame.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());

        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        center();
        panel.add(new JScrollPane(table),BorderLayout.CENTER);      //Lägger till JTablet och en JScrollPane så att man kan scrolla i tablet.
        frame.add(panel,BorderLayout.CENTER);                       //Sätter layout center och varför jag skapade en panel till och inte satte det direkt på framen är för att kanske i framtiden vill ha något i south eller norht som att lägga till celler/någon form av save funktion.
        frame.setVisible(true);
    }
    void center() {
        DefaultTableModel model = new DefaultTableModel(FileManagement.getRows(), FileManagement.getHeader());          //Gör en DTM där jag förklarar hur mitt JTabel ska formateras.
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);                                     //Använder mig av ACRS så att användaren själv kan sortera datan hur han/hon vill väl inne i programmet.
    }
    void filePicker(){
        JFileChooser j = new JFileChooser("src");                               //Öppnar JFC i src-mappen.
        int accept = j.showOpenDialog(null);                                    //Sparar värdet av vad användaren trycker på i JFCn.
        if(accept == JFileChooser.APPROVE_OPTION)
            setURL(j.getSelectedFile().getAbsolutePath());                      //Tar Stringen som inehåller pathen till filen och setter den till min URL String.
        else
            System.exit(0);
    }
    void setURL(String s){
        URL = s;
    }
}
