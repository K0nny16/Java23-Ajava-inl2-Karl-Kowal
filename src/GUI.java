import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI {

    JTable table;

    GUI() {
        FileManagement fil = new FileManagement();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table();

        frame.add(panel);
        panel.add(table);
        panel.add(scrollPane);
        frame.setVisible(true);
    }

    void table(){
        List<String[]> data = FileManagement.getArraySize();

        if (!data.isEmpty()) {
            String[] columnNames = data.get(0);
            table = new JTable(data.stream().skip(1).toArray(String[][]::new), columnNames);
            table.setAutoCreateRowSorter(true);
        } else
            JOptionPane.showMessageDialog(null, "Ingen fil valdes!");
    }
}
