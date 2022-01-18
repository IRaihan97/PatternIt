package GUI;

import javax.swing.*;
import java.awt.*;

public class SingletonForm {
    private JTextField classNameField;
    private JLabel className;
    private JComboBox ModifierBox;
    private JComboBox DataType;
    private JTextField textField1;
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JButton addMethodButton;
    private JButton addFieldButton;

    public JComponent getContent() {
        //mainPanel.setPreferredSize(new Dimension(500,100));
        return mainPanel;
    }
}
