package GUI;

import InputHolders.ClassInputs;
import lombok.Data;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class SingletonForm {
//    private JTextField classNameInput;
//    private JComboBox fieldDataTypeBox;
//    private JTextField fieldNameInput;
    private JPanel mainPanel;
    private JPanel classDefinerPanel;
    private JLabel singletonLb;
    private JTextField singletonName;
    private JTextField packageName;
    private JPanel addExtraPanel;
    private JLabel pkgLb;
    private JButton addFieldBtn;
    private JButton addMethodBtn;
    private GridBagConstraints con = new GridBagConstraints();

    public SingletonForm(){
        addFieldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fields dialog = new Fields();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        addMethodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Methods dialog = new Methods();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });



//        singletonName.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                ClassInputs.INSTANCE.addClassName(singletonName.getText());
//
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                ClassInputs.INSTANCE.addClassName(singletonName.getText());
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                ClassInputs.INSTANCE.addClassName(singletonName.getText());
//                System.out.println(singletonName.getText());
//            }
//        });

        packageName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageName.getText());
            }
        });

    }
    private void addFieldRow(){


    }

    private void generateMethodRow(){
    }


    public JComponent getContent() {


        return mainPanel;
    }





}
