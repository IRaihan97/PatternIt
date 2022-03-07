package GUI.ClassGenerators;

import InputHolders.GeneratedClass;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassGenerator {
    private JPanel mainPanel;
    private JLabel classNameLabel;
    private JTextField classNameTextField;
    private JLabel hasPsvmLabel;
    private JPanel classNamePanel;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JPanel psvmPanel;
    public ClassGenerator() {
        yesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratedClass.INSTANCE.setHasPsvm(true);
            }
        });
        noRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratedClass.INSTANCE.setHasPsvm(false);
            }
        });
        classNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                GeneratedClass.INSTANCE.setClassName(classNameTextField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                GeneratedClass.INSTANCE.setClassName(classNameTextField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                GeneratedClass.INSTANCE.setClassName(classNameTextField.getText());
            }
        });
    }

    public JComponent getContent() {
        return mainPanel;
    }
}
