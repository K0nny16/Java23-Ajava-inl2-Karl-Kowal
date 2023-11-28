import javax.swing.*;
import java.awt.*;

public class GUI {

    GUI(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(FileManagement.getarraySize().length, 3));

        for(int i = 0; i < 11;i++){
            for(int j = 0;j < 3;j++){
                JLabel x = new JLabel(FileManagement.getarraySize()[i][j]);
                panel.add(x);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}
