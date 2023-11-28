import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUI {

    private JTable table;
    private JPanel panel;
    private static String URL;

    GUI() {
        filePicker();
        FileManagement fil = new FileManagement(URL);
        JFrame frame = new JFrame();
        panel = new JPanel();
        frame.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());

        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        center();
        panel.add(new JScrollPane(table),BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    void center() {
        List<String[]> filData = FileManagement.getArraySize();
        String[] header = filData.get(0);
        String[][] rows = filData.stream().skip(1).toArray(String[][]::new);
        DefaultTableModel model = new DefaultTableModel(rows, header);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
    }
    void filePicker(){
        JFileChooser j = new JFileChooser("src");
        int accept = j.showOpenDialog(null);
        if(accept == JFileChooser.APPROVE_OPTION){
            setURL(j.getSelectedFile().getAbsolutePath());
        }
        else{
            System.exit(0);
        }
    }
    void setURL(String s){
        URL = s;
    }
}
